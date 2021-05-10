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

package com.disbots.spark.util.embeds;

/**
 * All emojis we use in the bot.
 *
 * @author Game Glide
 * @since 0.1
 * @version 0.3
 */
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
