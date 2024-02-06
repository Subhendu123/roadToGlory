package com.road2glory.splitwiseexpensetrackingapp.loggers;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.message.LocalizedMessageFactory;
import org.apache.logging.log4j.message.MessageFactory;


public class SplitwiseLogger extends Logger
{


    private static SplitwiseLogger LOG = null;

    protected SplitwiseLogger(LoggerContext context, String name, MessageFactory messageFactory)
    {
        super(context, name, messageFactory);
    }

    public static SplitwiseLogger getLogger()
    {
        if (LOG == null)
        {
            LoggerContext loggerContext = LoggerContext.getContext(false);
            loggerContext.setName("SPLITWISE-DEMO");
            LocalizedMessageFactory localizedMessageFactory = new LocalizedMessageFactory("SPLITWISE-DEMO");
//            LOG = (SplitwiseLogger) LogManager.getLogger("SPLITWISE");
            LOG = new SplitwiseLogger(loggerContext, "SPLITWISE-DEMO", localizedMessageFactory);
        }
        return LOG;
    }
}
