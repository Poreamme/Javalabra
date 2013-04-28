package ircbot;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class IrcClientBotApp {

    private SavedProfiles profiles = new SavedProfiles();
    private Scanner scanner = new Scanner(System.in);

    /*
     * Constructor of IrcClientBotApp
     */
    
    public IrcClientBotApp() throws InterruptedException, IOException {
        clearConsole();
        initialize();
    }

    
    /*
     * Method to pick either normal mode or debug mode
     * @return true if debug mode, false if normal.
     */
    public boolean ChooseRunningMode() throws InterruptedException {

        boolean DebugMode = false;
        System.out.println("Select Running Mode: \n"
                + " 1 = Normal\n"
                + " 2 = Debug");
        int mode = 0;
        try{
             mode = Integer.parseInt(scanner.nextLine());
        }catch(Exception e){
            System.out.println("Invalid running mode \n"
                    + "Restarting...");
            Thread.sleep(1000);
            clearConsole();
            ChooseRunningMode();
        }

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

    /*
     * method to pick a Profile or create a new one
     * @return Profile to be used.
     */
    
    public Profile ChooseProfile() throws InterruptedException {
        System.out.println("New Profile or Load Existing Profile: \n"
                + " 1 = Create New Profile \n"
                + " 2 = Load Existing Profile \n"
                + " 3 = Load Previous Profile");

        int profile = 0;
        try{
             profile = Integer.parseInt(scanner.nextLine());
        }catch(Exception e){
            System.out.println("Invalid Option \n"
                    + "Restarting...");
            Thread.sleep(1000);
            clearConsole();
            ChooseProfile();
        }
        
        clearConsole();
        if (profile == 1) {
            return createNewProfile();
        } else if (profile == 2) {
            return loadExistingProfile();
        }else if (profile == 3){
            Profile lastProfile = getPreviousProfile();
            if(lastProfile == null){
                System.out.println("There's no previous profile, "
                        + "please load an existing profile or create a new one \n");
                Thread.sleep(1000);
                clearConsole();
                ChooseProfile();
            }else{
                return lastProfile;
            }
         } else {
            System.out.println("There's no such option \n");
            Thread.sleep(1000);
            clearConsole();
            ChooseProfile();

        }
        return null;
    }

    /*
     * Gets the previously used profile from database.
     * @return Profile which was previously used.
     */
    
    public Profile getPreviousProfile() {

        if (new File("PreviousProfile.txt").exists()) {
            try {
                FileInputStream fs = new FileInputStream("PreviousProfile.txt");
                DataInputStream in = new DataInputStream(fs);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String str;
                String[] profileParts = new String[4];
                int i = 0;

                while ((str = br.readLine()) != null) {
                    profileParts[i++] = str;
                }

                in.close();

                System.out.println(i);
                if(i == 0){
                    return null;
                }else{
                    return new Profile(profileParts[0], profileParts[1], profileParts[2], profileParts[3]);
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                return null;
            }
            
        }
        
        else{
            return null;
        }
   
    }


    /*
     * Method to load a profile from file
     * @return Profile loaded from file.
     */

    public Profile loadExistingProfile() throws InterruptedException {
        HashMap<String, Profile> loadedProfiles = profiles.loadProfiles();
        while (true) {
            for (Profile temprofile : loadedProfiles.values()) {
                System.out.println(temprofile);
            }

            System.out.println("\nChoose your Profile by typing its server:\nHINT: type EXIT to quit \n-Press enter to go back-");
            String desiredProfile = scanner.nextLine();
            if (desiredProfile.equals("EXIT")) {
                System.exit(0);
            } else if(desiredProfile.equals("")){
                Thread.sleep(1000);
                clearConsole();
                ChooseProfile();
            }else if (loadedProfiles.containsKey(desiredProfile)) {
                Thread.sleep(1000);
                clearConsole();
                return loadedProfiles.get(desiredProfile);
            } else {
                System.out.println("There's no such profile saved \n");
                Thread.sleep(1000);
                clearConsole();
            }
        }
    }


    /*
     * Method to create a new Profile
     * @return Profile created.
     */
    

    public Profile createNewProfile() throws InterruptedException {
        Profile newProfile = gatherProfileData();
        while (true) {
            System.out.println("Do you want to save this profile? 1 / 0");
            int temp = Integer.parseInt(scanner.nextLine());
            if (temp == 1) {
                return createAndSave(newProfile);
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
    }


    /*
     * Method to save a new Profile to database
     * @param newProfile Profile to be saved.
     * @return Profile saved.
     */

    public Profile createAndSave(Profile newProfile) throws InterruptedException {
        HashMap<String, Profile> checkIfExists = profiles.loadProfiles();

        if (checkIfExists.containsKey(newProfile.getServer())) {
            if (checkIfExists.get(newProfile.getServer()).equals(newProfile)) {
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
    }


    /*
     * Method to collect needed data for creating a new profile
     * @return Profile which was made from gathered data.
     */
    

    public Profile gatherProfileData() {
        System.out.println("Creating new profile: ");
        System.out.print("Server: ");
        String server = scanner.nextLine();
        System.out.print("Owner: ");
        String owner = scanner.nextLine();
        System.out.print("Nick: ");
        String nick = scanner.nextLine();
        System.out.print("Channel: ");
        String channel = scanner.nextLine();

        return new Profile(server, owner, nick, channel);
    }


    /*
     * Method to gather needed information from the user to launch the bot.
     */

    public void initialize() throws InterruptedException, IOException {
        boolean DebugMode = ChooseRunningMode();
        Profile profile = ChooseProfile();
        profiles.setPreviousProfile(profile);
        if (DebugMode) {
            IrcBotDebug ircbot = new IrcBotDebug(profile);
        } else {
            IrcBot ircbot = new IrcBot(profile);
        }
    }

    /*
     * Method to visually clear the console
     */
    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
}
