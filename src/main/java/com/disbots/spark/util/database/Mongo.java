package com.disbots.spark.util.database;

import com.disbots.spark.util.logging.Logger;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static com.disbots.spark.core.Main.dotenv;

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
