package com.r2g.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/*
*
*
   This is created by Subhendu (2025) for the project: BatchDemo
        
   @Package name com.r2g.config
   @Author Subhendu
   @Date 23-Jan-2025 07:39
*
*
*/
@Component
public class CustomIntegerProcessor implements ItemProcessor<Integer,Long>
{


    private Logger LOG = LogManager.getLogger(CustomIntegerProcessor.class);


    /**
     * Process the provided item, returning a potentially modified or new item for
     * continued processing. If the returned result is {@code null}, it is assumed that
     * processing of the item should not continue.
     * <p>
     * A {@code null} item will never reach this method because the only possible sources
     * are:
     * <ul>
     * <li>an {@link ItemReader} (which indicates no more items)</li>
     * <li>a previous {@link ItemProcessor} in a composite processor (which indicates a
     * filtered item)</li>
     * </ul>
     *
     * @param item to be processed, never {@code null}.
     *
     * @return potentially modified or new item for continued processing, {@code null} if
     * processing of the provided item should not continue.
     * @throws Exception thrown if exception occurs during processing.
     */
    @Override
    public Long process (Integer item) throws Exception
    {
        LOG.info("Inside the process() of the Item Processor for the item "+item);
        if(item != null || item != 0)
        return Long.valueOf(item) + 100;

        return null;
    }


}
