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

package com.disbots.spark.core.models;

import org.bson.types.ObjectId;

/**
 * The POJO for the servers
 *
 * @author Game Glide
 * @since 0.2
 * @version 0.2
 */
public class Server
{
    private ObjectId id;
    private String ServerName;
    private String Prefix;

    public Server(String serverName, String prefix)
    {
        ServerName = serverName;
        Prefix = prefix;
    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public String getServerName()
    {
        return ServerName;
    }

    public void setServerName(String serverName)
    {
        ServerName = serverName;
    }

    public String getPrefix()
    {
        return Prefix;
    }

    public void setPrefix(String prefix)
    {
        Prefix = prefix;
    }
}
