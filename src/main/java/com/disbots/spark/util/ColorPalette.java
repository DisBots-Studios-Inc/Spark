package com.disbots.spark.util;

import java.awt.*;

public enum ColorPalette
{
    /* Embed Color Pallet for the bot. */

    SUCCESS(new Color(242, 110, 34)),
    NEUTRAL(new Color(215, 140, 0)),
    WARNING(new Color(241, 196, 15)),
    ERROR(new Color(231, 76, 60));

    private final Color code;

    ColorPalette(Color code)
    {
        this.code = code;
    }

    public Color getCode()
    {
        return code;
    }
}
