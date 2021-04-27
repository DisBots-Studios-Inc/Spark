package com.disbots.spark.commands.settings;

import com.disbots.spark.core.Main;
import com.disbots.spark.util.ColorPallet;
import com.disbots.spark.util.Emojis;
import com.disbots.spark.util.ServerCommand;
import com.disbots.spark.util.logging.Logger;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class setPrefix extends ServerCommand
{
    private final static Logger logger = new Logger();

    public setPrefix()
    {
        super("setPrefix");
    }

    @Override
    protected void runCommand(MessageCreateEvent message, Server server, ServerTextChannel channel, User user, String[] args)
    {
        try
        {
            Main.Prefix = args[1];
            ServerCommand.prefix = args[1];
            EmbedBuilder successEmbed = new EmbedBuilder()
                    .setTitle(Emojis.GREEN_TICK.getCode() + " The prefix was set successfully!")
                    .setDescription("The prefix of this server is now: " + Main.Prefix)
                    .setColor(ColorPallet.SUCCESS.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());

            message.getChannel().sendMessage(successEmbed);
        }
        catch(IndexOutOfBoundsException e)
        {
            logger.LogCommandError("Error no prefix was provided! ", e);

            EmbedBuilder errorEmbed = new EmbedBuilder()
                    .setTitle(Emojis.RED_TICK.getCode() + " Error: You didn't provide a prefix!")
                    .setDescription("Set the prefix by doing: `" + Main.Prefix+ "setPrefix <prefix>`")
                    .setColor(ColorPallet.ERROR.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());

            message.getChannel().sendMessage(errorEmbed);
        }
    }
}
