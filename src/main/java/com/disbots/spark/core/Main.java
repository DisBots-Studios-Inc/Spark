/*
 * Copyright (C) 2021 Aktindo & Game Glide
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

package com.disbots.spark.core;

import com.disbots.spark.commands.help.Help;
import com.disbots.spark.commands.settings.SetPrefix;
import com.disbots.spark.commands.system.Ping;
import com.disbots.spark.util.database.Mongo;
import com.disbots.spark.util.logging.Logger;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import java.util.Arrays;

/**
 * Entry point
 *
 * @author Aktindo & Game Glide
 * @since 0.1
 * @version 0.2
 * @implNote Please insure that env is set correctly.
 */

public class Main
{
    public static String Prefix = "s/";
    public final static Dotenv dotenv = Dotenv.load();

    private final static Logger logger = new Logger();
    private final static String TOKEN = dotenv.get("TOKEN");
    private final static Mongo mongoUtil = new Mongo();

    static DiscordApi client;
    static CommandHandler commandHandler;

    public static void main(String[] args)
    {
        logger.info("Loading resources...", "client");
        client = new DiscordApiBuilder().setToken(TOKEN).login().join();
        commandHandler = new JavacordHandler(client);

        commandHandler.setDefaultPrefix(Prefix);

        logger.info("Initialising the client...", "client");
        init();

        logger.info("Loaded the resources!", "client");
        logger.info("Initialized the client. Client is now logged in as " + client.getYourself().getName() + "#" + client.getYourself().getDiscriminator() + "!", "client");
    }

    private static void init()
    {
        //Set status
        logger.info("Setting the client activity...", "client");
        client.updateActivity(ActivityType.LISTENING, "to your messages!");
        logger.info("Successfully set status!", "client");

        //Register commands
        logger.info("Registering commands...", "client");
        commandHandler.registerCommand(new Ping());
        commandHandler.registerCommand(new SetPrefix());
        commandHandler.registerCommand(new Help(commandHandler));
        logger.info("Registered a total of " + Arrays.stream(client.getListeners().keySet().toArray()).count() + " commands!", "client");

        //Connect to db
        mongoUtil.connect();
    }
}