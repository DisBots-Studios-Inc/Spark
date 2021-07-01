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

package com.disbots.spark.commands.system;

import com.disbots.spark.util.embeds.EmbedMaker;
import com.disbots.spark.util.logging.Logger;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.concurrent.TimeUnit;

/**
 * Terminates the bot process.
 *
 * @author Game Glide
 * @since 0.3
 * @version 0.3
 * @implNote Works only with Game Glide.
 */
public class Kill implements CommandExecutor
{
    @Command(aliases = {"Kill", "Stop"}, description = "Terminates the bot", usage = "Kill", showInHelpPage = false)
    public void onCommand(User user, MessageCreateEvent message)
    {
        Logger logger = new Logger();

        if (!user.getDiscriminatedName().equals("Game Glide#4212"))
        {
            message.getChannel().sendMessage(new EmbedMaker().error("This command can only be run by Game Glide or Aktindo!", message.getMessage()).setTitle("DevOnly command!"));
        }
        else
        {
            EmbedBuilder initialMessage = new EmbedMaker().loading("The Bot is shutting down", message.getMessage()).setTitle("Bot Shutting Down...");
            EmbedBuilder shutDownMessage = new EmbedMaker().success("The bot is now offline.", message.getMessage()).setTitle("**Bot offline!**");

            message.getChannel().sendMessage(initialMessage).thenAccept(MessageToBeEdited -> MessageToBeEdited.getApi().getThreadPool().getScheduler().schedule(() -> {
                MessageToBeEdited.edit(shutDownMessage);
            }, 5, TimeUnit.SECONDS));

            try
            {
                Thread.sleep(6000);
            }
            catch (InterruptedException e)
            {
                logger.error("Failed to sleep thread in kill command: " + e.toString(), "Command");
            }
            System.exit(0);
        }
    }
}
