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
            // WARN! readLine() may return null
            message = input.readLine();
            String nro = slice(message, " ", 1);

            // Check for RPL_WELCOME
            if (nro.equals("001")) {
                send("MODE " + this.nick + " +B\r\n" + "JOIN " + this.channel + "\r\n");
            }
            if (message.startsWith("PING")) {
                Ping();
            }else if(message.startsWith(":")){
                if(message.split("!")[0].substring(1).equals(this.nick) || message.split(" ")[1].startsWith("00")){

                }
                else{
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
        // WARN! substring() exceptions if string is empty
        String sender = slice(message, "!", 0).substring(1);
        String channel = slice(message, " ", 2);
        String msg = slice(message, ":", 2);
        System.out.println("<"+sender+">" + " PRIVMSG " + channel + " " + "["+msg+"]");

        this.cc.readMessage(sender, channel, msg);
    }

    /*
     * Sends the desired message.
     * @param string String which contains the message to be sent.
     */
    public void send(String string) {
        System.out.println(string);
        output.print(string);
        output.flush();
    }

    public void Ping() {
        System.out.println("PONG");
        output.print("PONG");
        output.flush();
    }

    /**
     * Return a safe slice of given string.
     *
     * @param str   Original string.
     * @param delim Delimeter.
     * @param index Index of the slice.
     *
     * @return A slice of a string or an empty string.
     */
    private String slice(String str, String delim, int index) {
        if (str == null) return "";
        String[] arr = str.split(delim);

        try {
            return arr[index];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}
