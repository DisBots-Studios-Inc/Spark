package com.disbots.spark.util.logging;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.ansi;

public class Logger implements ILogger
{
    @Override
    public void LogClientInfo(String info)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(CYAN).a("CLIENT").fg(WHITE).a("]").fg(CYAN).a(info));
    }

    @Override
    public void LogCommandInfo(String info)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(GREEN).a("COMMANDS").fg(WHITE).a("]").fg(GREEN).a(info));
    }

    @Override
    public void LogDatabaseInfo(String info)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(MAGENTA).a("DATABASE").fg(WHITE).a("]").fg(MAGENTA).a(info));
    }

    @Override
    public void LogClientWarning(String warning)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(YELLOW).a("CLIENT_WARNING").fg(WHITE).a("]").fg(YELLOW).a(warning));
    }

    @Override
    public void LogCommandWarning(String warning)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(YELLOW).a("COMMANDS_WARNING").fg(WHITE).a("]").fg(YELLOW).a(warning));
    }

    @Override
    public void LogDatabaseWarning(String warning)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(YELLOW).a("DATABASE_WARNING").fg(WHITE).a("]").fg(YELLOW).a(warning));
    }

    @Override
    public void LogClientError(String error, Exception e)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(RED).a("CLIENT_ERROR").fg(WHITE).a("]").fg(RED).a(error).fg(RED).a(" error: " + e.toString()));
    }

    @Override
    public void LogClientError(String error)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(RED).a("CLIENT_ERROR").fg(WHITE).a("]").fg(RED).a(error));
    }

    @Override
    public void LogCommandError(String error, Exception e)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(RED).a("COMMANDS_ERROR").fg(WHITE).a("]").fg(RED).a(error).fg(RED).a(" error: " + e.toString()));
    }

    @Override
    public void LogCommandError(String error)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(RED).a("COMMANDS_ERROR").fg(WHITE).a("]").fg(RED).a(error));
    }

    @Override
    public void LogDatabaseError(String error, Exception e)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(RED).a("DATABASE_ERROR").fg(WHITE).a("]").fg(RED).a(error).fg(RED).a(" error: " + e.toString()));
    }

    @Override
    public void LogDatabaseError(String error)
    {
        System.out.println(ansi().eraseScreen().fg(WHITE).a("[").fg(RED).a("DATABASE_ERROR").fg(WHITE).a("]").fg(RED).a(error));
    }
}
