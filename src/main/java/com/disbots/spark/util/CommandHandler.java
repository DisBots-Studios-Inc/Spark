package com.disbots.spark.util;

import com.disbots.spark.core.Main;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public abstract class CommandHandler implements MessageCreateListener
{

    /*
    This class only exists to quicken everything up,
    rather than copy pasting the same stuff all over again.
     */

    private String command;
    public static String prefix = Main.Prefix;

    protected CommandHandler(String command){
        this.command = command;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        // These first two ifs are useless, but let's just leave it there for reasons. (it's useless since there is already ifPresent(...) below.
        if(!event.isServerMessage())
            return;

        if(!event.getMessageAuthor().isRegularUser())
            return;

        if(!event.getMessageContent().startsWith(prefix+command))
            return;

        // Runs everything.
        event.getServer().ifPresent(server -> event.getMessageAuthor().asUser().ifPresent(user ->
                event.getServerTextChannel().ifPresent(serverTextChannel -> runCommand(event, server, serverTextChannel, user, event.getMessageContent().toLowerCase().split(" ")))));
    }

    protected abstract void runCommand(MessageCreateEvent message, Server server, ServerTextChannel channel, User user, String[] args);
}