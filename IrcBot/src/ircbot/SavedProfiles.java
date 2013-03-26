
package ircbot;

import java.io.*;
import java.util.HashMap;

public class SavedProfiles {

    public SavedProfiles(){
        
    }
    public boolean saveProfile(Profile profile){
        try
        {
            FileWriter fs = new FileWriter("SavedProfiles.txt", true);
            BufferedWriter out = new BufferedWriter(fs);
            out.write(profile.toString());
            out.close();
            return true;
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            return false;
        }
        
        
    }
    public HashMap<String, Profile> loadProfiles(){
        HashMap<String, Profile> ProfileList = new HashMap<String, Profile>();
        try
        {
            FileInputStream fs = new FileInputStream("SavedProfiles.txt");
            DataInputStream in = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            
            String[] temp = new String[4];
            int counter = 0;
            while((str = br.readLine()) != null){
                temp[counter] = str;
                if(counter == 3){
                    ProfileList.put(temp[0], new Profile(temp[0], temp[1], temp[2], temp[3]));
                    counter = 0;
                }else{
                    counter++;
                }
            }

            in.close();
            
        } catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return ProfileList;
    }
}
