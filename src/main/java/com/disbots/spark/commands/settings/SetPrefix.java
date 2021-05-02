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

package com.disbots.spark.commands.settings;

import com.disbots.spark.core.Main;
import com.disbots.spark.util.CommandHandler;
import com.disbots.spark.util.embeds.EmbedMaker;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

/**
 * sets the prefix for one specific server.
 *
 * @author Game Glide
 * @since 0.1
 * @version 0.2
 * @implNote Not working with db yet.
 */
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
            // TODO: use mongodb to store servers instead.
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
