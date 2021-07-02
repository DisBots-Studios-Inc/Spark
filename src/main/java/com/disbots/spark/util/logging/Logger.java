/*
 * Copyright (C) 2021 Aktindo
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

package com.disbots.spark.util.logging;

import java.time.LocalDateTime;

/**
 * Logging methods for the bot.
 *
 * @author Aktindo
 * @since 0.1
 * @version 0.3
 */
// TODO make log to file.
public class Logger
{
    /**
     * Logs information to the console with source.
     *
     * @param message message to be displayed
     * @param src the source of the message. for example: Client or Command.
     */
    public void info(String message, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.GREEN + "INFO" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + " " + message);
    }

    /**
     * Logs warnings to the console with source.
     *
     * @param message message to be displayed
     * @param src the source of the message. for example: Client or Command.
     */
    public void warning(String message, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.YELLOW + "WARNING" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + " " + message);
    }

    /**
     * Logs errors to the console with source.
     *
     * @param error error to be displayed
     * @param src the source of the message. for example: Client or Command.
     */
    public void error(String error, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.RED + "ERROR" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + "\n" + "> " + error);
    }

    public void debug(String message, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.CYAN + "DEBUG" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + message);
    }

    /**
     * just a check to ensure that the src is always present.
     *
     * @param src the source of the log.
     * @return the source of the log.
     */
    private String getSrc(String src) {
        if (src.isEmpty()) {
            return "OTHER";
        }

        return src.toUpperCase();
    }

    /**
     * helper method to get current time for logging.
     *
     * @return current time
     */
    private String toHHMMSS() {
        LocalDateTime timeNow = LocalDateTime.now();

        int hours = timeNow.getHour();
        int minutes = timeNow.getMinute();
        int seconds = timeNow.getSecond();

        return "[" + hours + ":" + minutes + ":" + seconds + "]";
    }
}
