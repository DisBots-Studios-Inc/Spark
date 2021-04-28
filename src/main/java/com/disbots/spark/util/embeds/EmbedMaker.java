package com.disbots.spark.util.embeds;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class EmbedMaker {
    public EmbedBuilder neutral(String message, Message messageInstance) {
        EmbedBuilder neutralEmbed;
        neutralEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(message)
                .setColor(EmbedColorPalette.NEUTRAL.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return neutralEmbed;
    }

    public EmbedBuilder warning(String message, Message messageInstance) {
        EmbedBuilder warningEmbed;
        warningEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(message)
                .setColor(EmbedColorPalette.WARNING.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return warningEmbed;
    }

    public EmbedBuilder loading(String message, Message messageInstance) {
        EmbedBuilder loadingEmbed;
        loadingEmbed = new EmbedBuilder()
                .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
                .setDescription(EmbedEmojis.LOADING.getCode() + " " + message)
                .setColor(EmbedColorPalette.NEUTRAL.getCode())
                .setFooter("Designed by DisBots Studios Inc.", "");

        return loadingEmbed;
    }

    public EmbedBuilder success(String message, Message messageInstance) {
        EmbedBuilder successEmbed;
        successEmbed = new EmbedBuilder()
            .setAuthor(messageInstance.getAuthor().getName(), "", messageInstance.getAuthor().getAvatar())
            .setDescription(EmbedEmojis.GREEN_TICK.getCode() + " " + message)
            .setColor(EmbedColorPalette.SUCCESS.getCode())
            .setFooter("Designed by DisBots Studios Inc.", "");

        return successEmbed;
    }

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
