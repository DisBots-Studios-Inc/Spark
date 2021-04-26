package com.disbots.spark.core;
import com.disbots.spark.commands.Ping;
import com.disbots.spark.util.logging.Logger;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.fusesource.jansi.AnsiConsole;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;
import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Main
{
    public final static String Prefix = "s/";
    private final static Dotenv dotenv = Dotenv.load();
    private final static String MongoUrl = dotenv.get("MONGO_URI");
    private final static String Token = dotenv.get("TOKEN");
    public static MongoClient mongoClient;
    private final static Logger logger = new Logger();


    public static void main(String[] args)
    {
        DiscordApi client = new DiscordApiBuilder().setToken(Token).login().join();
        logger.LogClientInfo("Starting client...");
        init(client);
        logger.LogClientInfo("client is ready and logged on as: " + client.getYourself().getName() + "!");
        AnsiConsole.systemInstall();
    }

    private static void init(@NotNull DiscordApi client)
    {
        //Set status
        logger.LogClientInfo("Setting status...");
        client.updateActivity(ActivityType.LISTENING, "to your messages!");
        client.updateStatus(UserStatus.DO_NOT_DISTURB);
        logger.LogClientInfo("Successfully set status!");

        //Register commands
        logger.LogCommandInfo("Registering Listeners...");
        client.addListener(new Ping());
        logger.LogCommandInfo("Registered listeners: " + Arrays.stream(client.getListeners().keySet().toArray()).count() + "!");

        //Connect to db
        mongoClient = MongoClients.create(MongoUrl);
        logger.LogDatabaseInfo("Connected to mongodb!");

        Document testDocument = new Document();
        testDocument.append("name", "game glide");
        testDocument.append("age", 13);
        MongoDatabase database = mongoClient.getDatabase("main");
        MongoCollection<Document> toys = database.getCollection("toys");
        toys.insertOne(testDocument);
        logger.LogDatabaseInfo("added document to db!");
    }
}