package com.disbots.spark.util.logging;

public interface ILogger
{
    void LogClientInfo(String info);
    void LogCommandInfo(String info);
    void LogDatabaseInfo(String info);
    void LogClientWarning(String warning);
    void LogCommandWarning(String warning);
    void LogDatabaseWarning(String warning);
    void LogClientError(String error, Exception e);
    void LogClientError(String error);
    void LogCommandError(String error, Exception e);
    void LogCommandError(String error);
    void LogDatabaseError(String error, Exception e);
    void LogDatabaseError(String error);
}
