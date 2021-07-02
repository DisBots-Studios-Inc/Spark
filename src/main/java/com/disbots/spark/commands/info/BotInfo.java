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

package com.disbots.spark.commands.info;

import com.disbots.spark.commands.system.Uptime;
import com.disbots.spark.util.embeds.EmbedColorPalette;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class BotInfo implements CommandExecutor
{
    @Command(aliases = {"BotInfo", "BotI", "BIonfo"}, description = "Shows information about the bot!", usage = "BotInfo")
    public void OnBotInfo(MessageCreateEvent message)
    {
        SendEmbed(message);
    }

    private void SendEmbed(MessageCreateEvent message)
    {
        EmbedBuilder BotInfoEmbed = new EmbedBuilder()
                .setTitle("Bot Information")
                .setAuthor(message.getMessage().getAuthor().getName(), "", message.getMessage().getAuthor().getAvatar())
                .addField("Users", "Serving " + "**"+ message.getApi().getCachedUsers().size() +"**" + " users!", true)
                .addField("Channels", "Helping people in  " + "**"+ message.getApi().getChannels().size() +"**" + " channels!", true)
                .addField("Servers", "Listening to commands on " + "**"+ message.getApi().getServers().size() +"**" + " servers!", true)
                .addField("Support", "If you want to join the support server, click [here](https://discord.gg/6g297Usrsn)")
                .addField("Invite", "If you want to add me to your server, click [here](" + message.getApi().createBotInvite() + ")!")
                .addField("Uptime", Uptime.formatUptime(), false)
                .addField("The crew", "**Developers**\nAktindo (<@683879319558291539>)\nGame Glide (<@518382491338539017>)")
                .setColor(EmbedColorPalette.NEUTRAL.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        message.getChannel().sendMessage(BotInfoEmbed);
    }
}
