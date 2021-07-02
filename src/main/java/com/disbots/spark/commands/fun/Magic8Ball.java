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

package com.disbots.spark.commands.fun;

import com.disbots.spark.core.Main;
import com.disbots.spark.util.embeds.EmbedMaker;
import com.disbots.spark.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <h1>ALL GLORY TO THE HYPNOTOAD!</h1>
 * A magic 8ball command that tells the answers to your problems.
 *
 * @author Game Glide
 * @since 0.2
 * @version 0.3
 */
public class Magic8Ball implements CommandExecutor
{
    private static HttpURLConnection con;
    private static final Logger logger = new Logger();

    @Command(aliases = {"8Ball", "Ball", "Toss"}, description = "Displays the answer to your questions!", usage = "8Ball <question>")
    public void OnCommand(MessageCreateEvent message, String[] args)
    {
        if (args.length == 0)  // s/8Ball
        {
            message.getChannel().sendMessage(new EmbedMaker().error("Incorrect usage! Run `" + Main.Prefix + "help 8Ball" + "`", message.getMessage()).setTitle("Syntax error!"));
        }
        else
        {
            List<String> ArgsList = convertArrayToList(args);
            // remove the command
            if (args[0].equals("s/8Ball"))
            {
                ArgsList.remove("s/8Ball");   
            }
            else if (args[0].equals("s/Ball"))
            {
                ArgsList.remove("s/Ball");
            }
            else if (args[0].equals("s/Toss"))
            {
                ArgsList.remove("s/Toss");
            }

            CompletableFuture<Void> RequestResponse = message.getChannel().sendMessage(new EmbedMaker().loading("The almighty 8Ball is thinking!", message.getMessage()).setTitle("Loading...")).thenAccept(SentMessage -> {
                String response;
                String ActualAnswer = null;

                try
                {
                    String question = String.join(" ", ArgsList);
                    logger.debug("A new question for Magic8Ball: " + question, "Command");

                    response = makeRequest(question);
                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode node = objectMapper.readValue(response, JsonNode.class);
                    JsonNode magicArray = node.get("magic");
                    JsonNode answer = magicArray.get("answer");
                    ActualAnswer = answer.asText();
                }
                catch (IOException e)
                {
                    message.getChannel().sendMessage(new EmbedMaker().error("The request failed with `IOEXCEPTION`. Please contact the developers!\nAnd if you are the developer then good luck fixing this!", message.getMessage()).setTitle("An error has occurred!"));
                    new Logger().error("An IOException occured while parsing json response in command 8Ball", "Commands", e);
                    e.printStackTrace();
                }

                SentMessage.edit(new EmbedMaker().success("8Ball's answer: " + ActualAnswer, message.getMessage()).setTitle("THE ALMIGHTY 8BALL HAS RESPONDED!"));
            });
        }
    }

    private String makeRequest(String question) throws IOException
    {
        String url = Main.MAGICBALLURI + question;

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())))
            {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null)
                {

                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString();
        }
        finally
        {

            con.disconnect();
        }
    }

    public static <T> List<T> convertArrayToList(T[] array)
    {
        List<T> list = new ArrayList<>();

        Collections.addAll(list, array);

        return list;
    }
}
