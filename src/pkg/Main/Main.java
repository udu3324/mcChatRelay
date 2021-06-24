//
// minecraftChatRelay is by udu3324/_._#3324
//
// minecraftChatRelay is a java application that relays messages sent in minecraft to a discord server.
//
// To use minecraftChatRelay, you need to set 6 values.
// The six values are mcLogLocation, token, status, prefix,
// staffRoleID, and minecraftChatChannel.
// You can set the values in pkg.Values.EditValuesHere.java
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
import pkg.Values.EditValuesHere;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    // Remember to change the values in EditValuesHere.java (located in pkg.Values)

    // It is not recommended to change any code or value below unless if you know what you're doing.
    // Do not change the value of mcChatRelayChannel, session, or latestLogLine.
    public TextChannel mcChatRelayChannel;
    public String latestLogLine;
    public Integer sessionToggle = 1; //1 false | 0 true

    public static void main(String[] args) throws LoginException {
        JDABuilder
                .createLight(EditValuesHere.token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing(EditValuesHere.status))
                .build();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Member author = event.getMember();

        Message session = event.getMessage();
        if (session.getContentRaw().startsWith(EditValuesHere.prefix + "session")) {
            MessageChannel channel = event.getChannel();
            assert author != null;
            String userRoles = String.valueOf(author.getRoles());
            if (userRoles.contains(EditValuesHere.staffRoleID)) {
                mcChatRelayChannel = event.getGuild().getTextChannelById(EditValuesHere.minecraftChatChannelID);
                channel.sendMessage("A new session has been created. <#"+EditValuesHere.minecraftChatChannelID+">").queue();
                sessionToggle = 0;
                System.out.println(mcChatRelayChannel);
            } else {
                channel.sendMessage("You do not have access to this command.").queue();
            }
        }
    }
    {
        TailerListener listener = new MyTailListener();
        Tailer tailer = new Tailer(EditValuesHere.mcLogLocation, listener, 0);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
    }
    public class MyTailListener extends TailerListenerAdapter {
        @Override
        public void handle(String line) {
            if (line.contains("[CHAT]")) {
                latestLogLine = line;
                if (sessionToggle == 0) {
                    /* Chat MSG Replacer (stops people trying to ping and do other stuff) */
                    String latestLogLineSend = latestLogLine.replaceAll("```", "").substring(31);

                    /* Chat MSG Sender */
                    System.out.println(latestLogLine);
                    mcChatRelayChannel.sendMessage("```"+latestLogLineSend+"```").queue();
                }   else {
                    System.out.println(" <!> Create a session! Do \""+EditValuesHere.prefix+"session\"");
                }
            }
        }
    }
}
