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

package com.disbots.spark.commands.info;

import com.disbots.spark.util.embeds.EmbedMaker;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

/**
 * Displays the support server!
 *
 * @author Game Glide
 * @since 0.3
 * @version 0.3
 */
public class Support implements CommandExecutor
{
    @Command(aliases = {"Support"}, description = "Displays the support server message.", usage = "Support")
    public void onCommand(MessageCreateEvent message)
    {
        String supportMessage = "Here is the link to our **support server**: https://discord.gg/Fjrvtq4mZc\n" +
                                "\n" +
                                "We would be glad to help you!\n" +
                                "\n" +
                                "Also if there is an issue with the bot, along with reporting it there please file a github on: https://github.com/DisBots-Studios-Inc/Spark/issues";

        message.getChannel().sendMessage(new EmbedMaker().neutral(supportMessage, message.getMessage()).setTitle("**Support Server:**"));
    }
}
