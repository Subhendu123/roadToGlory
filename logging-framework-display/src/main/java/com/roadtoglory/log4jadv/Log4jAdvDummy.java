package com.roadtoglory.log4jadv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/*
*
*
   This is created by Subhendu (2024) for the project: logging-framework-display
        
   @Package name com.roadtoglory.log4jadv
   @Author Subhendu
   @Date 03-Oct-2024 07:59
*
*
*/
public class Log4jAdvDummy
{
    public static final Logger agentLogger = LogManager.getLogger(Log4jAdvDummy.class);


    public String returnAString(int i){
        agentLogger.info("AGENT LOGS- info logger!");
        agentLogger.debug("AGENT LOGS- debug logger!");
        agentLogger.warn("AGENT LOGS- warning logger!");
        agentLogger.error("AGENT LOGS- Error logger!");
        return new String("test "+i);
    }


}
