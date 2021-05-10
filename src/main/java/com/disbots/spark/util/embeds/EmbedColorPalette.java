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

import java.awt.*;

/**
 * Color palette for the bot.
 *
 * @author Game Glide
 * @since 0.1
 * @version 0.3
 */
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
