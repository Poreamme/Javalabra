
    //Because I can. That's why!
package ircbot;

public class CommandCenter {
    private IrcBot ircBot;
    public CommandCenter(IrcBot ircBot){
        this.ircBot = ircBot;
    }
    public void readMessage(String sender, String channel, String message){
        if(sender.equals(this.ircBot.owner)
                && message.equals("GTFO")){
            System.exit(0);
        }
    }
}
