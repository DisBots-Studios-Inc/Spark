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

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;

/**
 * Help command.
 *
 * @author Game Glide
 * @since 0.2
 * @version 0.2
 */
public class Help implements CommandExecutor
{

    private final CommandHandler commandHandler;

    public Help(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    // TODO: implement embed in help menu.
    @Command(aliases = {"help", "h"}, description = "Shows the help menu")
    public String OnHelp()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("```xml"); // a xml code block looks fancy
        for (CommandHandler.SimpleCommand simpleCommand : commandHandler.getCommands())
        {
            if (!simpleCommand.getCommandAnnotation().showInHelpPage())
            {
                continue; // skip command
            }
            builder.append("\n");
            if (!simpleCommand.getCommandAnnotation().requiresMention())
            {
                // the default prefix only works if the command does not require a mention
                builder.append(commandHandler.getDefaultPrefix());
            }
            String usage = simpleCommand.getCommandAnnotation().usage();
            if (usage.isEmpty())
            { // no usage provided, using the first alias
                usage = simpleCommand.getCommandAnnotation().aliases()[0];
            }
            builder.append(usage);
            String description = simpleCommand.getCommandAnnotation().description();
            if (!description.equals("none"))
            {
                builder.append(" | ").append(description);
            }
        }
        builder.append("\n```"); // end of xml code block
        return builder.toString();
    }
}
