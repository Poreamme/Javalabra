
    //Because I can. That's why!
package ircbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommandCenter {
    private IrcBot ircBot;
    private Calendar cal = Calendar.getInstance();
    private List<String> greetings = new ArrayList<String>();
    public CommandCenter(IrcBot ircBot){
        this.ircBot = ircBot;
        getGreetings();
    }
    /*
     * Reacts to a possible command, if message doesn't include one, then it ignores the message.
     * @param sender String which contains the sender of the message
     * @param channel String which contains the channel of the message
     * @param message String which contains the message
     */
    public void readMessage(String sender, String channel, String message){
        saveChatLog(sender, channel, message);
        
        if(sender.equals(this.ircBot.owner)
                && message.equalsIgnoreCase("GTFO")){
            System.exit(0);
        }
        
        //greetings.contains() metodia ei käytetä, koska sen kanssa ei voi käyttää ignoreCasea :/
        for(int i=0; i<greetings.size(); i++){
                if(greetings.get(i).equalsIgnoreCase(message)){
                    ircBot.send(channel, greetings.get(i));
                }
            }

        if(sender.equals(this.ircBot.owner)
                && message.startsWith("!addGreeting")){
            for(int i=0; i<greetings.size(); i++){
                if(greetings.get(i).equalsIgnoreCase(message)){
                    ircBot.send(channel, "I already know that greeting!");
                }else{
                    addGreeting(message.substring(13)+"\r\n");
                }
            }
        }
        
        if(message.equals("showGreetings")){
            for(int i=0; i<greetings.size(); i++){
                System.out.println(greetings.get(i));
            }
        }
    }
    /*
     * Saves the chatlog to a file
     * @param sender String which contains the sender of the message
     * @param channel String which contains the channel of the message
     * @param message String which contains the message
     */
    public void saveChatLog(String sender, String channel, String message){
        File file = new File("ChatLog/"+cal.getTime().getDay()+"."+cal.getTime().getMonth()+"."+cal.getTime().getYear()+".txt");
        String string = "<"+sender+">" + " PRIVMSG " + channel + " " + "["+message+"]\r\n";
        writeToFile(file, string);
    }
    
    public void addGreeting(String greeting){
        this.greetings.add(greeting);
        File file = new File("Database/tervehdykset.txt");
        writeToFile(file, greeting);
    }
    
    public void getGreetings(){
        try
        {
            FileInputStream fs = new FileInputStream("Database/tervehdykset.txt");
            DataInputStream in = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;

            while((str = br.readLine()) != null){
                greetings.add(str);
            }

            in.close();
            
        } catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void writeToFile(File file, String string){
        try
        {
            FileWriter fs = new FileWriter(file, true);
            BufferedWriter out = new BufferedWriter(fs);
            out.write(string);
            out.close();
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
