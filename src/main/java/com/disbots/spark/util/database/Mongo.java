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

import com.disbots.spark.core.Main;
import com.disbots.spark.core.models.Server;
import com.disbots.spark.util.logging.Logger;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.event.CommandListener;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.disbots.spark.core.Main.dotenv;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Mongodb utility
 *
 * @author Aktindo & Game Glide
 * @since 0.1
 * @version 0.2
 * @implNote Please ensure that you have provided a Mongo URI.
 */
public class Mongo
{
    private final Logger logger = new Logger();
    private final static String MONGO_URI = dotenv.get("MONGO_URI");

    public static MongoClient mongoClient;
    MongoCollection<Server> serverCollection;

    public void connect()
    {
        // if we use this, we can use POJOS.
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
            logger.error("Error while connecting to MongoDB." + error.toString(), "database");
        }
        logger.info("Connected to MongoDB!", "database");

        // drop all the data in it
        serverCollection.drop();

        // adding servers to db
        Server devlabo = new Server("devlabo", "s/");
        serverCollection.insertOne(devlabo);

        logger.info("inserted servers!", "database");
    }
}
