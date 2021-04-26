package com.disbots.spark.core;
import com.disbots.spark.commands.Ping;
import com.disbots.spark.util.logging.Logger;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.fusesource.jansi.AnsiConsole;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;
import io.github.cdimascio.dotenv.Dotenv;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;

import java.util.Arrays;

public class Main
{
    public final static String Prefix = "s/";
    private final static Dotenv dotenv = Dotenv.load();
    private final static String Token = dotenv.get("TOKEN");
    private final static String MongoUrl = dotenv.get("MONGO_URI");
    private static MongoClient mongoClient = MongoClients.create(MongoUrl);
    private final static Logger logger = new Logger();

    public static void main(String[] args)
    {
        DiscordApi client = new DiscordApiBuilder().setToken(Token).login().join();
        logger.LogClientInfo("Starting client...");
        init(client);
        logger.LogClientInfo("client is ready and logged on as: " + client.getYourself().getName() + "!");
        AnsiConsole.systemInstall();
    }

    private static void init(DiscordApi client)
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
    }
}
