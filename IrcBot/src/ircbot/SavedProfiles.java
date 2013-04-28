package ircbot;

import java.io.*;
import java.util.HashMap;

public class SavedProfiles {

    public SavedProfiles() {
    }
    /*
     * Saves a profile to a file
     * @param profile Profile to be saved.
     * @return true if the profile was saved, otherwise false.
     */

    public boolean saveProfile(Profile profile) {
        try {
            FileWriter fs = new FileWriter("SavedProfiles.txt", true);
            BufferedWriter out = new BufferedWriter(fs);
            out.write(profile.toString());
            out.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
    /*
     * Gets the list of profiles from file
     * @return HashMap<String, Profile> which contains all the saved profiles.
     */

    public HashMap<String, Profile> loadProfiles() {
        HashMap<String, Profile> ProfileList = new HashMap<String, Profile>();
        try {
            FileInputStream fs = new FileInputStream("SavedProfiles.txt");
            DataInputStream in = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;

            String[] temp = new String[4];
            int counter = 0;
            while ((str = br.readLine()) != null) {
                temp[counter] = str;
                if (counter == 3) {
                    ProfileList.put(temp[0], new Profile(temp[0], temp[1], temp[2], temp[3]));
                    counter = 0;
                } else {
                    counter++;
                }
            }

            in.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return ProfileList;
    }

<<<<<<< HEAD
    /*
     * Saves the previous Profile to file
     * @param profile Profile to be saved.
     */
=======
>>>>>>> 6a54f731c133ebc57fc088b156d68f338c14b089
    public void setPreviousProfile(Profile profile) {


        try {
<<<<<<< HEAD
            FileWriter fs = new FileWriter("PreviousProfile.txt");
=======
            FileWriter fs = new FileWriter("PreviousProfile.txt", true);
>>>>>>> 6a54f731c133ebc57fc088b156d68f338c14b089
            BufferedWriter out = new BufferedWriter(fs);
            out.write(profile.toString());
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
