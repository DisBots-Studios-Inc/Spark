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

package com.disbots.spark.commands.system;

import com.disbots.spark.util.CommandHandler;
import com.disbots.spark.util.embeds.EmbedColorPalette;
import com.disbots.spark.util.embeds.EmbedMaker;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Ping command, says the current latency of the bot
 *
 * @author Game Glide
 * @since 0.1
 * @version 0.2
 */
public class Ping extends CommandHandler
{
    public Ping()
    {
        super("ping");
    }

    private EmbedMaker embedMaker = new EmbedMaker();

    /**
     * Runs the ping command
     *
     * @param message message event
     * @param server server of the message event
     * @param channel channel of the message event
     * @param user user that sent the message
     * @param args arguments of the message
     */
    @Override
    protected void runCommand(MessageCreateEvent message, Server server, ServerTextChannel channel, User user, String[] args)
    {
        /* Sending The embed and checking for errors in calculating the latency. */
        try
        {
            SendLatencyEmbed(message);
        }
        catch (InterruptedException e)
        {
            EmbedBuilder ErrorEmbed = new EmbedBuilder()
                    .setDescription("There was an error evaluating the latency! Please contact DisBots Inc.")
                    .setFooter("", message.getMessageAuthor().getAvatar())
                    .setColor(EmbedColorPalette.ERROR.getCode());
            message.getChannel().sendMessage(ErrorEmbed);
        }
    }

    private void SendLatencyEmbed(MessageCreateEvent message) throws InterruptedException
    {
        /* Sending and editing the embed. */

        long GatewayLatency = message.getApi().getLatestGatewayLatency().toMillis();
        CompletableFuture<Void> RESTLatency = message.getApi().measureRestLatency().thenAccept(Time -> {
             EmbedBuilder InitialPing = embedMaker.loading("Testing ping...", message.getMessage());

            EmbedBuilder PingEmbed;
            PingEmbed = embedMaker.neutral(
                        "Bot Latency: " + "**"+GatewayLatency+"**" + "ms\n" +
                        "Rest latency: " + "**"+Time.toMillis()+"**" + "ms\n",
                         message.getMessage())
                    .setTitle(":ping_pong: Pong!");

            EmbedBuilder finalPingEmbed = PingEmbed;
            message.getChannel().sendMessage(InitialPing).thenAccept(MessageToBeEdited -> MessageToBeEdited.getApi().getThreadPool().getScheduler().schedule(() -> {
                MessageToBeEdited.edit(finalPingEmbed);
            }, 1, TimeUnit.SECONDS));
        });
    }
}
