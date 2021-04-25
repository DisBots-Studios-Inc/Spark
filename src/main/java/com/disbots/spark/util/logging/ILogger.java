package com.disbots.spark.util.logging;

public interface ILogger
{
    void LogClientInfo(String info);
    void LogCommandInfo(String info);
    void LogDatabaseInfo(String info);
    void LogClientWarning(String warning);
    void LogCommandWarning(String warning);
    void LogDatabaseWarning(String warning);
    void LogClientError(String error, Error e);
    void LogClientError(String error);
    void LogCommandError(String error, Error e);
    void LogCommandError(String error);
    void LogDatabaseError(String error, Error e);
    void LogDatabaseError(String error);
}
