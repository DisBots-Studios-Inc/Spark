package com.disbots.spark.commands.system;

import com.disbots.spark.util.embeds.EmbedColorPalette;
import com.disbots.spark.util.CommandHandler;
import com.disbots.spark.util.embeds.EmbedMaker;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Ping extends CommandHandler
{
    public Ping()
    {
        super("ping");
    }

    private EmbedMaker embedMaker = new EmbedMaker();

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
