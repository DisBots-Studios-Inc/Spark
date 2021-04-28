package com.disbots.spark.commands.settings;

import com.disbots.spark.core.Main;
import com.disbots.spark.util.embeds.EmbedColorPalette;
import com.disbots.spark.util.embeds.EmbedEmojis;
import com.disbots.spark.util.CommandHandler;
import com.disbots.spark.util.embeds.EmbedMaker;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class SetPrefix extends CommandHandler {
    public SetPrefix()
    {
        super("setprefix");
    }

    private final EmbedMaker embedMaker = new EmbedMaker();

    @Override
    protected void runCommand(MessageCreateEvent message, Server server, ServerTextChannel channel, User user, String[] args)
    {
        try
        {
            Main.Prefix = args[1];
            CommandHandler.prefix = args[1];
            EmbedBuilder successEmbed = embedMaker.success("The prefix was successfully changed to " + "**" + prefix + "**" + ".", message.getMessage());

            message.getChannel().sendMessage(successEmbed);
        }
        catch(IndexOutOfBoundsException e)
        {
            EmbedBuilder errorEmbed = embedMaker.error("Run `[prefix]help setPrefix` to refer the command usage.", message.getMessage())
                    .setTitle("Syntax error!");

            message.getChannel().sendMessage(errorEmbed);
        }
    }
}
