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

package com.disbots.spark.util;

import com.disbots.spark.core.Main;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * Temporary command handler
 *
 * @see de.btobastian.sdcf4j
 * @see de.btobastian.sdcf4j.handler.JavacordHandler;
 * @deprecated use Sdcf4j instead
 * @author Game Glide
 * @since 0.1
 * @version 0.2
 */
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