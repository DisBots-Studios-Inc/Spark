package com.disbots.spark.core;
import com.disbots.spark.commands.settings.setPrefix;
import com.disbots.spark.commands.system.Ping;
import com.disbots.spark.util.database.Mongo;
import com.disbots.spark.util.logging.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Main
{
    public static String Prefix = "s/";
    public final static Dotenv dotenv = Dotenv.load();

    private final static Logger logger = new Logger();
    private final static String TOKEN = dotenv.get("TOKEN");
    private final static Mongo mongoUtil = new Mongo();


    public static void main(String[] args)
    {
        logger.info("Loading resources...", "client");
        DiscordApi client = new DiscordApiBuilder().setToken(TOKEN).login().join();

        logger.info("Initialising the client...", "client");
        init(client);

        logger.info("Loaded the resources!", "client");
        logger.info("Initialized the client. Client is now logged in as " + client.getYourself().getName() + "#" + client.getYourself().getDiscriminator() + "!", "client");
    }

    private static void init(@NotNull DiscordApi client)
    {
        //Set status
        logger.info("Setting the client activity...", "client");
        client.updateActivity(ActivityType.LISTENING, "to your messages!");
        logger.info("Successfully set status!", "client");

        //Register commands
        logger.info("Registering listeners...", "client");
        client.addListener(new Ping());
        client.addListener(new setPrefix());
        logger.info("Registered a total of " + Arrays.stream(client.getListeners().keySet().toArray()).count() + " listeners!", "client");

        //Connect to db
        mongoUtil.connect();
    }
}