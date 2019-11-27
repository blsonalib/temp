package com.moodanalyse;

public class MoodAnalysisException extends Exception
{
    enum ExceptionType
    {
        ENTERED_NULL,ENTERED_EMPTY,NO_SUCH_CLASS, NO_SUCH_METHOD,METHOD_INVOCATION_ISSUE,No_SUCH_FIELD,No_ACCESS,OBJECT_CREATION_ISSUE;
    }
    ExceptionType type;
    public MoodAnalysisException(ExceptionType type,String message)
    {
        super(message);
        this.type=type;
    }
    public MoodAnalysisException(ExceptionType type,Throwable cause)
    {
        super(cause);
        this.type=type;
    }
    public MoodAnalysisException(ExceptionType type,String message,Throwable cause)
    {
        super(message, cause);
        this.type=type;
    }

}
