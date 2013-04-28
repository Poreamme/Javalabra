
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
    private IrcBotDebug ircBotDebug;
    private Calendar cal = Calendar.getInstance();
    private List<String> greetings = new ArrayList<String>();
<<<<<<< HEAD
    
    /*
     * Constructor of CommandCenter
     * @param ircBot IrcBot which delivers the messages for CommandCenter to be handled
     */
    public CommandCenter(IrcBot ircBot){
        this.ircBot = ircBot;
        getGreetings();
    }
    
     /*
     * Constructor of CommandCenter
     * @param ircBot IrcBotDebug which delivers the messages for CommandCenter to be handled
     */
    public CommandCenter(IrcBotDebug ircBot){
        this.ircBotDebug = ircBot;
        getGreetings();
=======
    public CommandCenter(IrcBot ircBot){
        this.ircBot = ircBot;
        getGreetings();
>>>>>>> 6a54f731c133ebc57fc088b156d68f338c14b089
    }
    /*
     * Reacts to a possible command, if message doesn't include one, then it ignores the message.
     * @param sender String which contains the sender of the message
     * @param channel String which contains the channel of the message
     * @param message String which contains the message
     */
    
    /*
     * Tämän metodin sisään tulee kaikkien komentojen tunnistus.
     * Alla muutamia esimerkkejä Komentojen tunnistamisesta ja 
     * niihin vastaamisesta.
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

<<<<<<< HEAD
        
        //Lisää tervehdyksen tietokantaan jos sitä ei tiedetä jo valmiiksi.
        if(sender.equals(this.ircBot.owner)
                && message.startsWith("!addGreeting")){
            boolean iDunnoThatYet = true;
            for(int i=0; i<greetings.size(); i++){
                if(greetings.get(i).equalsIgnoreCase(message)){
                    ircBot.send(channel, "I already know that greeting!");
                    iDunnoThatYet = false;
                    break;
                }
            }
            if(iDunnoThatYet){
                addGreeting(message.substring(13)+"\r\n");
                this.greetings.add(message.substring(13));
            }
        }
        
        //sysottaa kaikki tuntemansa tervehdykset konsoliin.
        if(message.equals("showGreetings")){
            for(int i=0; i<greetings.size(); i++){
                System.out.println(greetings.get(i));
            }
        }
    }
    
    public void readMessageDebug(String sender, String channel, String message){
        saveChatLog(sender, channel, message);
        
        if(sender.equals(this.ircBotDebug.owner)
                && message.equalsIgnoreCase("GTFO")){
            System.exit(0);
        }
        
        //greetings.contains() metodia ei käytetä, koska sen kanssa ei voi käyttää ignoreCasea :/
        for(int i=0; i<greetings.size(); i++){
                if(greetings.get(i).equalsIgnoreCase(message)){
                    ircBotDebug.send(channel, greetings.get(i));
                }
            }

        
        //Lisää tervehdyksen tietokantaan jos sitä ei tiedetä jo valmiiksi.
        if(sender.equals(this.ircBotDebug.owner)
                && message.startsWith("!addGreeting")){
            boolean iDunnoThatYet = true;
            for(int i=0; i<greetings.size(); i++){
                if(greetings.get(i).equalsIgnoreCase(message)){
                    ircBotDebug.send(channel, "I already know that greeting!");
                    iDunnoThatYet = false;
                    break;
                }
            }
            if(iDunnoThatYet){
                addGreeting(message.substring(13)+"\r\n");
                this.greetings.add(message.substring(13));
            }
        }
        
        //sysottaa kaikki tuntemansa tervehdykset konsoliin.
=======
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
        
>>>>>>> 6a54f731c133ebc57fc088b156d68f338c14b089
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
    
<<<<<<< HEAD
    /*
     * Adds a new greeting to the database
     * @param greeting String which contains the greeting to be added.
     */
    public void addGreeting(String greeting){
        this.greetings.add(greeting);
        File file = new File("tervehdykset.txt");
        writeToFile(file, greeting);
    }
    
    /*
     * Gets greetings from the database to be ready for use.
     */
=======
    public void addGreeting(String greeting){
        this.greetings.add(greeting);
        File file = new File("Database/tervehdykset.txt");
        writeToFile(file, greeting);
    }
    
>>>>>>> 6a54f731c133ebc57fc088b156d68f338c14b089
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
    
<<<<<<< HEAD
    /*
     * Writes desired text to desired file.
     * @param file File that contains the path to the file to write to
     * @param string String which contains the line to be written to the File
     */
    
=======
>>>>>>> 6a54f731c133ebc57fc088b156d68f338c14b089
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
