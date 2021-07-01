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
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.lang.management.ManagementFactory;

public class Uptime implements CommandExecutor
{
    @Command(aliases = {"uptime", "up"}, description = "Shows how long the bot has been up for.", usage = "uptime")
    public void OnUptime(MessageCreateEvent message)
    {
        message.getChannel().sendMessage(new EmbedMaker().neutral("The bot has been online for " + formatUptime(), message.getMessage()).setTitle("**Uptime:**"));
    }

    public static String formatUptime()
    {
        //Taken from Almighty Alpaca
        //https://github.com/Java-Discord-Bot-System/Plugin-Uptime/blob/master/src/main/java/com/almightyalpaca/discord/bot/plugin/uptime/UptimePlugin.java#L28-L42

        /* Formating the uptime in the dd:hh:mm:ss format. */
        final long duration = ManagementFactory.getRuntimeMXBean().getUptime();

        final long years = duration / 31104000000L;
        final long months = duration / 2592000000L % 12;
        final long days = duration / 86400000L % 30;
        final long hours = duration / 3600000L % 24;
        final long minutes = duration / 60000L % 60;
        final long seconds = duration / 1000L % 60;
        // final long milliseconds = duration % 1000;

        String uptime = (years == 0 ? "" : "**" + years + "** year(s), ") + (months == 0 ? "" : "**" + months + "** month(s), ") + (days == 0 ? "" : "**" + days + "** day(s), ") + (hours == 0 ? "" : "**" + hours + "** Hours, ")
                + (minutes == 0 ? "" : "**" + minutes + "** minute(s), ") + (seconds == 0 ? "" : "**" + seconds + "** second(s), ") /* + (milliseconds == 0 ? "" : milliseconds + " Milliseconds, ") */;

        uptime = replaceLast(uptime, ", ", "");
        uptime = replaceLast(uptime, ",", " and");
        return uptime;
    }

    //Taken from Almighty Alpaca
    //https://github.com/Java-Discord-Bot-System/Core/blob/master/src/main/java/com/almightyalpaca/discord/bot/system/util/StringUtils.java#L15-L17
    private static String replaceLast(final String text, final String regex, final String replacement)
    {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
