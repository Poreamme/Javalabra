
package ircbot;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class IrcClientBotApp {

    private SavedProfiles profiles = new SavedProfiles();
    private Scanner scanner = new Scanner(System.in);

    public IrcClientBotApp() throws InterruptedException, IOException {
        clearConsole();
        initialize();
    }

    public boolean ChooseRunningMode() throws InterruptedException {

        boolean DebugMode = false;
        System.out.println("Select Running Mode: \n"
                + " 1 = Normal\n"
                + " 2 = Debug");
        int mode = Integer.parseInt(scanner.nextLine());

        if (mode == 1) {
            DebugMode = false;
        } else if (mode == 2) {
            DebugMode = true;
        } else {
            System.out.println("Invalid running mode \n"
                    + "Restarting...");
            Thread.sleep(1000);
            clearConsole();
            ChooseRunningMode();
        }

        clearConsole();
        return DebugMode;
    }

    public Profile ChooseProfile() throws InterruptedException {
        System.out.println("New Profile or Load Existing Profile: \n"
                + " 1 = Create New Profile \n"
                + " 2 = Load Existing Profile");

        int profile = Integer.parseInt(scanner.nextLine());

        clearConsole();
        if (profile == 1) {
            System.out.println("Creating new profile: ");
            System.out.print("Server: ");
            String server = scanner.nextLine();
            System.out.print("Owner: ");
            String owner = scanner.nextLine();
            System.out.print("Nick: ");
            String nick = scanner.nextLine();
            System.out.print("Channel: ");
            String channel = scanner.nextLine();

            while (true) {
                System.out.println("Do you want to save this profile? 1 / 0");
                Profile newProfile = new Profile(server, owner, nick, channel);
                int temp = Integer.parseInt(scanner.nextLine());
                if (temp == 1) {
                    HashMap<String, Profile> checkIfExists = profiles.loadProfiles();

                    if (checkIfExists.containsKey(server)) {
                        if (checkIfExists.get(server).equals(newProfile)) {
                            System.out.println("This profile already exists");
                        }
                    } else {
                        boolean didItWork = profiles.saveProfile(newProfile);
                        if (didItWork) {
                            System.out.println("Profile saved successfully!");
                        } else {
                            System.out.println("Failed to save profile.");
                        }
                    }
                    Thread.sleep(1000);
                    clearConsole();
                    return newProfile;
                } else if (temp == 0) {
                    clearConsole();
                    return newProfile;
                } else {
                    System.out.println("There's no such option \n");
                    Thread.sleep(1000);
                    clearConsole();
                    System.out.println(newProfile.toString());
                }
            }


        } else if (profile == 2) {
            HashMap<String, Profile> loadedProfiles = profiles.loadProfiles();
            while (true) {
                for (Profile temprofile : loadedProfiles.values()) {
                    System.out.println(temprofile);
                }
                System.out.println("\nChoose your Profile by typing its server:\nHINT: type EXIT to quit");
                String desiredProfile = scanner.nextLine();
                if (desiredProfile.equals("EXIT")) {
                    System.exit(0);
                } else if (loadedProfiles.containsKey(desiredProfile)) {
                    Thread.sleep(1000);
                    clearConsole();
                    return loadedProfiles.get(desiredProfile);
                } else {
                    System.out.println("There's no such profile saved \n");
                    Thread.sleep(1000);
                    clearConsole();
                }
            }
        } else {
            System.out.println("There's no such option \n");
            Thread.sleep(1000);
            clearConsole();
            ChooseProfile();

        }
        return null;
    }

    public void initialize() throws InterruptedException, IOException {
        boolean DebugMode = ChooseRunningMode();
        Profile profile = ChooseProfile();
        if(DebugMode){
            IrcBotDebug ircbot = new IrcBotDebug(profile);
        }else{
            IrcBot ircbot = new IrcBot(profile);
        }
    }

    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
}
