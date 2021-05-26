//
// minecraftChatRelay is by udu3324/_._#3324
//
// minecraftChatRelay is a java application that relays messages sent in minecraft to a discord server.
//
// To use minecraftChatRelay, you need to set 6 values.
// The six values are mcLogLocation, token, status, prefix,
// staffRoleID, and minecraftChatChannel.
//
// The next thing is to build the bot and run it. You need to
// do the session command either in the bots dms or a channel
// to make the bot run.
//
// If you find any issues, report them using github issues.
//
package pkg.Main;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;
import javax.security.auth.login.LoginException;
import java.io.File;

public class Main extends ListenerAdapter {

    // mcLogLocation should be the system file location where latest.log is stored on your machine.
    // For example, "C:\Users\(enter-pc-username-here)\AppData\Roaming\.minecraft\logs\latest.log"
    static public File mcLogLocation = new File("");

    // token is your bot token set in your discord developer applications.
    // For example, "ODQ3MjM1MDY2OTMzMjgwNzk5.YK7HJA.KAstmxm9MjkaauPnaJ_Ku2dyOpU"
    static public String token = "";

    // status is the status shown on the bots bio.
    // For example, "Hello, World!"
    static public String status = "";

    // prefix is the prefix used before the session command.
    // For example, ">"
    static public String prefix = "";

    // staffRoleID is the role id that is whitelisted to use the session command.
    // For example, "836821240504254485"
    static public String staffRoleID = "";

    // minecraftChatChannel is the channel that will be relayed with messages sent in the server.
    // for example, "838473566500421693"
    static public String minecraftChatChannelID = "";

    // It is not recommended to change any code or value below unless if you know what you're doing.
    // Do not change the value of mcChatRelayChannel, session, or latestLogLine.
    public TextChannel mcChatRelayChannel;
    public String latestLogLine;
    public Integer sessionToggle = 1; //1 false | 0 true
    
    public static void main(String[] args) throws LoginException {
        if (token.isEmpty()) {
            System.out.println("You have to provide a token!");
            System.exit(1);
        }
        if (status.isEmpty()) {
            System.out.println("You have to provide a status!");
            System.exit(1);
        }
        if (prefix.isEmpty()) {
            System.out.println("You have to provide a prefix!");
            System.exit(1);
        }
        if (staffRoleID.isEmpty()) {
            System.out.println("You have to provide a staffRoleID!");
            System.exit(1);
        }
        if (minecraftChatChannelID.isEmpty()) {
            System.out.println("You have to provide a minecraftChatChannel!");
            System.exit(1);
        }
        JDABuilder
                .createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing(status))
                .build();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Member author = event.getMember();

        Message session = event.getMessage();
        if (session.getContentRaw().startsWith(prefix + "session")) {
            MessageChannel channel = event.getChannel();
            assert author != null;
            String userRoles = String.valueOf(author.getRoles());
            if (userRoles.contains(staffRoleID)) {
                mcChatRelayChannel = event.getGuild().getTextChannelById(minecraftChatChannelID);
                channel.sendMessage("A new session has been created. <#"+minecraftChatChannelID+">").queue();
                sessionToggle = 0;
            } else {
                channel.sendMessage("You do not have access to this command.").queue();
            }
        }
    }
    {
        TailerListener listener = new MyTailListener();
        Tailer tailer = new Tailer(mcLogLocation, listener, 0);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
    }
    public class MyTailListener extends TailerListenerAdapter {
        @Override
        public void handle(String line) {
            latestLogLine = line;
            if (sessionToggle == 0) {
                /* Chat MSG Replacer */
                latestLogLine = latestLogLine.replaceAll("```", "");
                /* Chat MSG Sender */
                System.out.println(latestLogLine);
                if (latestLogLine.contains("[CHAT]")) {
                    String latestLogLineSend = latestLogLine.substring(31);
                    mcChatRelayChannel.sendMessage("```"+latestLogLineSend+"```").queue();
                }
            }   else {
                System.out.println(" <!> Create a session! Do \""+prefix+"session\"");
            }
        }
    }
}
