package com.disbots.spark.commands.settings;

import com.disbots.spark.core.Main;
import com.disbots.spark.util.ColorPalette;
import com.disbots.spark.util.Emojis;
import com.disbots.spark.util.CommandHandler;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class setPrefix extends CommandHandler {
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
            CommandHandler.prefix = args[1];
            EmbedBuilder successEmbed = new EmbedBuilder()
                    .setTitle(Emojis.GREEN_TICK.getCode() + " The prefix was set successfully!")
                    .setDescription("The prefix of this server is now: " + Main.Prefix)
                    .setColor(ColorPalette.SUCCESS.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());

            message.getChannel().sendMessage(successEmbed);
        }
        catch(IndexOutOfBoundsException e)
        {
            EmbedBuilder errorEmbed = new EmbedBuilder()
                    .setTitle(Emojis.RED_TICK.getCode() + " Error: You didn't provide a prefix!")
                    .setDescription("Set the prefix by doing: `" + Main.Prefix+ "setPrefix <prefix>`")
                    .setColor(ColorPalette.ERROR.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());

            message.getChannel().sendMessage(errorEmbed);
        }
    }
}
