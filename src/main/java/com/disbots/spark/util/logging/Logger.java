package com.disbots.spark.util.logging;

import java.time.LocalDateTime;

public class Logger
{
    public void info(String message, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.GREEN + "INFO" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + " " + message);
    }

    public void warning(String message, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.YELLOW + "WARNING" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + " " + message);
    }

    public void error(String error, String src)
    {
        System.out.println(this.toHHMMSS() + " "  + LoggerColors.RED + "Error" + LoggerColors.RESET + " " + "[" + this.getSrc(src) + "]" + "\n" + "> " + error);
    }

    private String getSrc(String src) {
        if (src.isEmpty()) {
            return "OTHER";
        }

        return src.toUpperCase();
    }

    private String toHHMMSS() {
        LocalDateTime timeNow = LocalDateTime.now();

        int hours = timeNow.getHour();
        int minutes = timeNow.getMinute();
        int seconds = timeNow.getSecond();

        return "[" + hours + ":" + minutes + ":" + seconds + "]";
    }
}
