
    //Because I can. That's why!
package ircbot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;

public class CommandCenter {
    private IrcBot ircBot;
    private Calendar cal = Calendar.getInstance();
    public CommandCenter(IrcBot ircBot){
        this.ircBot = ircBot;
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
    }
    /*
     * Saves the chatlog to a file
     * @param sender String which contains the sender of the message
     * @param channel String which contains the channel of the message
     * @param message String which contains the message
     */
    public void saveChatLog(String sender, String channel, String message){
        try
        {
            FileWriter fs = new FileWriter("ChatLog/"+cal.getTime().getDay()+"."+cal.getTime().getMonth()+"."+cal.getTime().getYear()+".txt", true);
            BufferedWriter out = new BufferedWriter(fs);
            out.write("<"+sender+">" + " PRIVMSG " + channel + " " + "["+message+"]\r\n");
            out.close();
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
