//
// minecraftChatRelay is by udu3324/_._#3324
//
// minecraftChatRelay is a java application that relays messages sent in minecraft to a discord server.
//
// To use minecraftChatRelay, you need to set 6 values.
// The six values are mcLogLocation, token, status, prefix,
// staffRoleID, and minecraftChatChannel.
// You can set the values in THIS class.
//
// The next thing is to build the bot and run it. You need to
// do the session command either in the bots dms or a channel
// to make the bot run.
//
// If you find any issues, report them using github issues.
//
package pkg.Values;

import java.io.File;

public class EditValuesHere {
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

    // ignore this stuff below as it's checks for if you filled the stuff above
    static {
        if (token.isEmpty()) {
            System.out.println("You have to provide a token!");
            System.exit(-1);
        }
        if (status.isEmpty()) {
            System.out.println("You have to provide a status!");
            System.exit(-1);
        }
        if (prefix.isEmpty()) {
            System.out.println("You have to provide a prefix!");
            System.exit(-1);
        }
        if (staffRoleID.isEmpty()) {
            System.out.println("You have to provide a staffRoleID!");
            System.exit(-1);
        }
        if (minecraftChatChannelID.isEmpty()) {
            System.out.println("You have to provide a minecraftChatChannel!");
            System.exit(-1);
        }
    }
}
