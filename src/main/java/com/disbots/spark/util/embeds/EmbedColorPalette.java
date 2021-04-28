package com.disbots.spark.util.embeds;

import java.awt.*;

public enum EmbedColorPalette
{
    /* Embed Color Pallet for the bot. */

    SUCCESS(new Color(46, 204, 113)),
    NEUTRAL(new Color(215, 140, 0)),
    WARNING(new Color(241, 196, 15)),
    ERROR(new Color(231, 76, 60));

    private final Color code;

    EmbedColorPalette(Color code)
    {
        this.code = code;
    }

    public Color getCode()
    {
        return code;
    }
}
