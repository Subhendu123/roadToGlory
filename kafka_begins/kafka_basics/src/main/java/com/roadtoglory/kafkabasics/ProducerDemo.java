package com.roadtoglory.kafkabasics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class ProducerDemo
{
    private static final Logger LOG = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main (String[] args)
    {
        LOG.info("Welcome to Producer demo!");
        LOG.debug("DEBUG");
        LOG.warn("WARNING!");
        LOG.error("ERROR");

    }

}
