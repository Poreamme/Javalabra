
package ircbot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class IrcClientBotApp {
    private SavedProfiles profiles = new SavedProfiles();
    private Scanner scanner = new Scanner(System.in);
    
    public IrcClientBotApp() throws InterruptedException, IOException{
        clearConsole();
        initialize();
    }
    
    public boolean ChooseRunningMode() throws InterruptedException{
        
        boolean DebugMode = false;   
        System.out.println("Select Running Mode: \n"
                + " 1 = Normal\n"
                + " 2 = Debug");
        int mode = Integer.parseInt(scanner.nextLine());
        
        if(mode == 1){
            DebugMode = false;
        }else if(mode == 2){
            DebugMode = true;
        }else{
            System.out.println("Invalid running mode \n"
                    + "Restarting...");
            Thread.sleep(1000);
            clearConsole();
            ChooseRunningMode();
        }
        
        clearConsole();
        return DebugMode;
    }
    
    public Profile ChooseProfile() throws InterruptedException{
        System.out.println("New Profile or Load Existing Profile: \n"
                + " 1 = Create New Profile \n"
                + " 2 = Load Existing Profile");
        
        int profile = Integer.parseInt(scanner.nextLine());
        
        clearConsole(); 
        if(profile == 1){
            System.out.println("Creating new profile: ");
            System.out.print("Server: ");
            String server = scanner.nextLine();
            System.out.print("Owner: ");
            String owner = scanner.nextLine();
            System.out.print("Nick: ");
            String nick = scanner.nextLine();
            System.out.print("Channel: ");
            String channel = scanner.nextLine();
            
            System.out.println("Do you want to save this profile? 1 / 0");
            int temp = Integer.parseInt(scanner.nextLine());
            if(temp == 1){
                Profile newProfile = new Profile(server, owner, nick, channel);
                HashMap<String, Profile> checkIfExists = profiles.loadProfiles();

                if(checkIfExists.containsKey(server)){
                    if(checkIfExists.get(server).equals(newProfile)){
                        System.out.println("This profile already exists");
                    }
                }else{
                    profiles.saveProfile(newProfile);
                }
            }
            
            
            
        }else if(profile == 2){
            HashMap<String, Profile> loadedProfiles = profiles.loadProfiles();
            int temp = 0;
            for (Profile temprofile : loadedProfiles.values()) {
                System.out.println(temprofile + "\n");
            }
        }
        
        
        
        else{
            System.out.println("There's no such option \n"
                    + "");
            Thread.sleep(1000);
            clearConsole();
            ChooseProfile();
        }
        return new Profile(null, null, null, null);
    }
    
    public void initialize() throws InterruptedException, IOException{
        boolean DebugMode = ChooseRunningMode();
        Profile profile = ChooseProfile();
        Start();
    }
    
    private void Start() throws IOException{
        IrcBot ircbot = new IrcBot("irc.inet.fi", "Poreamme", "PoraBot", "#Porebottaus");
    }
    
    private void clearConsole(){
        for(int i=0; i<50; i++){
            System.out.println("");
        }
    }
    
}
