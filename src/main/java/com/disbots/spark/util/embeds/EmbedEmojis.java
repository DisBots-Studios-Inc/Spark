package com.disbots.spark.util.embeds;

public enum EmbedEmojis
{
    LOADING(new String("<a:loading:781376656937713676>")),
    GREEN_TICK(new String("<a:green_tick:781083389280911370>")),
    RED_TICK(new String("<a:red_tick:781083386881507328>"));

    private final String Emoji;

    EmbedEmojis(String emoji)
    {
        this.Emoji = emoji;
    }

    public String getCode()
    {
        return Emoji;
    }
}
