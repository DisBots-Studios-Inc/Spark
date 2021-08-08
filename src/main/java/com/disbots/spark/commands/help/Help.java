/*
 * Copyright (C) 2021 Game Glide
 *
 * This file is part of Spark.
 *
 * Spark is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser general Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 *  Spark is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.disbots.spark.commands.help;

import com.disbots.spark.util.embeds.EmbedColorPalette;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.Arrays;

/**
 * Help command.
 *
 * @author Game Glide
 * @since 0.2
 * @version 0.3
 */
public class Help implements CommandExecutor
{

    private final CommandHandler commandHandler;

    public Help(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Command(aliases = {"help", "h"}, description = "Shows the help menu", category = "help")
    public void OnHelp(Message message)
    {
        EmbedBuilder embed = new EmbedBuilder().setAuthor(message.getAuthor().getName(), "", message.getAuthor().getAvatar())
                .setTitle("**Help Menu**")
                .setThumbnail(getClass().getClassLoader().getResourceAsStream("Logo.png"), "png")
                .setDescription("Here are all my commands!")
                .setColor(EmbedColorPalette.NEUTRAL.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands()) {
            if (!simpleCommand.getCommandAnnotation().showInHelpPage()) {
                continue; // skip command
            }
            String usage = simpleCommand.getCommandAnnotation().usage();
            if (usage.isEmpty()) { // no usage provided, using the first alias
                usage = simpleCommand.getCommandAnnotation().aliases()[0];
            }
            String description = simpleCommand.getCommandAnnotation().description();
            embed.addField("**"+ simpleCommand.getCommandAnnotation().category().toUpperCase() + ":** " + "" + usage + "", description, true);
        }

        message.getChannel().sendMessage(embed).join();
    }
}
