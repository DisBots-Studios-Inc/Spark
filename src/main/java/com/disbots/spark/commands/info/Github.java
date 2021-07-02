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
 * Displays the github repo of the bot!
 *
 * @author Game Glide
 * @since 0.3
 * @version 0.3
 */
public class Github implements CommandExecutor
{
    @Command(aliases = {"Github", "Repo"}, description = "Displays the github repo of the bot..", usage = "Github")
    public void OnCommand(MessageCreateEvent message)
    {
        String GithubRepo = "The following is are the important github and other links:\n" +
                            "\n" +
                            "Main Repo: https://github.com/DisBots-Studios-Inc/Spark\n" +
                            "Issues: https://github.com/DisBots-Studios-Inc/Spark/issues\n" +
                            "Pull Requests: https://github.com/DisBots-Studios-Inc/Spark/pulls\n" +
                            "Projects: https://github.com/DisBots-Studios-Inc/Spark/projects\n" +
                            "Travis CI: https://travis-ci.com/github/DisBots-Studios-Inc/Spark";

        message.getChannel().sendMessage(new EmbedMaker().neutral(GithubRepo, message.getMessage()).setTitle("**Github Repo:**"));
    }
}
