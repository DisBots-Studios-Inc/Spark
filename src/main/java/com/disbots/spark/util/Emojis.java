package com.disbots.spark.util;

import java.awt.*;

public enum Emojis
{
    LOADING(new String("")),
    GREEN_TICK(new String("")),
    RED_TICK(new String(""));

    private final String Emoji;

    Emojis(String emoji)
    {
        this.Emoji = emoji;
    }

    public String getCode()
    {
        return Emoji;
    }
}
