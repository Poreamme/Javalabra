
package ircbot;

public class Profile {
    
    private String server;
    private String owner;
    private String nick;
    private String channel;
    
    public Profile(String server, String owner, String nick, String channel){
        this.server = server;
        this.owner = owner;
        this.nick = nick;
        this.channel = channel;
    }
    
    public String getServer(){
        return this.server;
    }
    public String getOwner(){
        return this.owner;
    }
    public String getNick(){
        return this.nick;
    }
    public String getChannel(){
        return this.channel;
    }
    
    public String toString(){
        String temp = this.server + "\n" + this.owner + "\n" + this.nick + "\n" + this.channel;
        return temp;
    }
    
    public boolean equals(Profile profile){
        if(this.server.equals(profile.server) && this.owner.equals(profile.owner)
                && this.nick.equals(profile.nick) && this.channel.equals(profile.channel)){
            return true;
        }else{
            return false;
        }
    }
}
