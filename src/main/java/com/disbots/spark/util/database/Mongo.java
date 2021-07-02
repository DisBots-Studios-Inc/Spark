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

package com.disbots.spark.util.database;

import com.disbots.spark.core.models.Server;
import com.disbots.spark.util.logging.Logger;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.javacord.api.DiscordApi;

import java.util.Collection;

import static com.disbots.spark.core.Main.dotenv;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Mongodb utility
 *
 * @author Aktindo & Game Glide
 * @since 0.1
 * @version 0.3
 * @implNote Please ensure that you have provided a Mongo URI.
 */
public class Mongo
{
    private final Logger logger = new Logger();
    private static String MONGO_URI = dotenv.get("MONGO_URI");

    private static DiscordApi bot;
    public static MongoClient mongoClient;
    public static MongoCollection<Server> serverCollection;

    public Mongo(DiscordApi api)
    {
        bot = api;
    }

    public void connect()
    {
        //POJO codec registry.
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        logger.info("Connecting to MongoDB...", "database");
        try
        {
            mongoClient = MongoClients.create(MONGO_URI);
            MongoDatabase settingsDB = mongoClient.getDatabase("settings").withCodecRegistry(pojoCodecRegistry);
            serverCollection = settingsDB.getCollection("serverSettings", Server.class);
        }
        catch (Exception error)
        {
            logger.error("Error while connecting to MongoDB", "database", error);
        }
        logger.info("Connected to MongoDB!", "database");

        // drop all the data in it
        serverCollection.drop();

        logger.info("Registering guilds...", "database");
        RegisterGuilds();
    }

    private void RegisterGuilds()
    {
        Collection<org.javacord.api.entity.server.Server> servers = bot.getServers();

        servers.forEach(server -> {
            logger.debug("Bot is in: ", "database");
            logger.debug(server.getName(), "database");

            Server ServerObject = new Server(server.getId(), "s/");
            serverCollection.insertOne(ServerObject);
        });

        logger.info("Registered Guilds successfully!", "database");
    }
}
