

package ircbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class IrcBot {

    public String server;
    public String owner;
    public String nick;
    public String channel;
    public PrintWriter output;
    public CommandCenter cc;
    
    /*
     * Does the botting.
     * @param profile Contains the server,owner,nick and channel for the bot
     */
    public IrcBot(Profile profile) throws IOException {
        this.server = profile.getServer();
        this.owner = profile.getOwner();
        this.nick = profile.getNick();
        this.channel = profile.getChannel();
        Start();
    }

    private void Start() throws IOException {
        String message;
        Socket sock = new Socket(this.server, 6667);
        BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        output = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));

        send("USER " + this.nick + " 0 * :" + this.owner + "\r\n" + "NICK " + this.nick + "\r\n");

        this.cc = new CommandCenter(this);

        while (sock.isConnected()) {
            message = input.readLine();
            if (message.split(" ")[1].equals("001")) {
                send("MODE " + this.nick + " +B\r\n" + "JOIN " + this.channel + "\r\n");
            }
            if (message.startsWith("PING")) {
                Ping();
            }
            if(message.split(" ", 2)[1].substring(0, 4+this.nick.length()).matches("[0-9]{3} "+this.nick)){
            }else{
                if(message.split(" ", 2)[1].substring(0, 7+this.nick.length()).equalsIgnoreCase("NOTICE "+this.nick)){
                }else{
                    parseMessage(message);
                }
            }
        }
        sock.close();
    }

    /*
     * Reforges the message to look less confusing for the user.
     * @param message String which will be reforged.
     */
    public void parseMessage(String message){
                
        String sender = message.split("!")[0].substring(1);
        String channel = message.split(" ")[2];
        String msg = message.split(":", 3)[2];
        System.out.println("<"+sender+">" + " PRIVMSG " + channel + " " + "["+msg+"]");
        
        this.cc.readMessage(sender, channel, msg);
    }
    /*
     * Sends the desired message.
     * @param string String which contains the message to be sent.
     */
    public void send(String string) {
        System.out.print(string);
        output.print(string);
        output.flush();
    }

    public void Ping() {
        output.print("PONG");
        output.flush();
    }
}
