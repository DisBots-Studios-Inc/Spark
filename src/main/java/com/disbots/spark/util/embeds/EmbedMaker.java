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

package com.disbots.spark.util.embeds;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

/**
 * Embed maker to quicken everything.
 *
 * @author Aktindo
 * @since 0.1
 * @version 0.3
 */
public class EmbedMaker {

    /**
     * sends a neutral embed to the given message event instance
     *
     * @param message message to be displayed
     * @param messageInstance the message event
     * @return EmbedBuilder
     */
    public EmbedBuilder neutral(String message, Message messageInstance) {
        EmbedBuilder neutralEmbed;
        neutralEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(message)
                .setColor(EmbedColorPalette.NEUTRAL.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return neutralEmbed;
    }

    /**
     * returns a warning embed
     *
     * @param message message to be displayed
     * @param messageInstance the message event
     * @return EmbedBuilder
     */
    public EmbedBuilder warning(String message, Message messageInstance) {
        EmbedBuilder warningEmbed;
        warningEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(message)
                .setColor(EmbedColorPalette.WARNING.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return warningEmbed;
    }

    /**
     * returns a loading embed
     *
     * @param message message to be displayed
     * @param messageInstance the message event
     * @return EmbedBuilder
     */
    public EmbedBuilder loading(String message, Message messageInstance) {
        EmbedBuilder loadingEmbed;
        loadingEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(EmbedEmojis.LOADING.getCode() + " " + message)
                .setColor(EmbedColorPalette.NEUTRAL.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return loadingEmbed;
    }

    /**
     * returns a success embed
     *
     * @param message message to be displayed
     * @param messageInstance the message event
     * @return EmbedBuilder
     */
    public EmbedBuilder success(String message, Message messageInstance) {
        EmbedBuilder successEmbed;
        successEmbed = new EmbedBuilder()
            .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
            .setDescription(EmbedEmojis.GREEN_TICK.getCode() + " " + message)
            .setColor(EmbedColorPalette.SUCCESS.getCode())
            .setFooter("Designed by DisBots Studios Inc.", "");

        return successEmbed;
    }

    /**
     * returns a error embed
     *
     * @param message message to be displayed
     * @param messageInstance the message event
     * @return EmbedBuilder
     */
    public EmbedBuilder error(String message, Message messageInstance) {
        EmbedBuilder errorEmbed;
        errorEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(EmbedEmojis.RED_TICK.getCode() + " " + message)
                .setColor(EmbedColorPalette.ERROR.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return errorEmbed;
    }
}
