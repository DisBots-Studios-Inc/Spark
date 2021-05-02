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

package com.disbots.spark.util.database;

import com.disbots.spark.util.logging.Logger;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static com.disbots.spark.core.Main.dotenv;

/**
 * Mongodb utility
 *
 * @author Aktindo
 * @since 0.1
 * @version 0.2
 */
public class Mongo {
    private final Logger logger = new Logger();
    private final static String MONGO_URI = dotenv.get("MONGO_URI");

    public static MongoClient mongoClient;

    public void connect() {
        logger.info("Connecting to MongoDB...", "database");
        try {
            mongoClient = MongoClients.create(MONGO_URI);
        } catch (Exception error) {
            logger.error("Error while connecting to MongoDB." + error.toString(), "database");
        }
        logger.info("Connected to MongoDB!", "database");
    }
}
