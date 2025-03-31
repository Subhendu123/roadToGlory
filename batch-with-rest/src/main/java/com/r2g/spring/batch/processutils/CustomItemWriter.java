package com.r2g.spring.batch.processutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


/*
*
*
   This is created by Subhendu (2025) for the project: BatchDemo
        
   @Package name com.r2g.config
   @Author Subhendu
   @Date 23-Jan-2025 21:19
*
*
*/
@Component
public class CustomItemWriter implements ItemWriter<Long>
{
    private Logger LOG = LogManager.getLogger(CustomItemWriter.class);



    /**
     * Process the supplied data element. Will not be called with any null items in normal
     * operation.
     *
     * @param chunk of items to be written. Must not be {@code null}.
     *
     * @throws Exception if there are errors. The framework will catch the exception and
     *                   convert or rethrow it as appropriate.
     */
    @Override
    public void write (Chunk<? extends Long> chunk) throws Exception
    {
        LOG.info("Trying to write data....");
        LOG.info("Size is = "+chunk.getItems().size());

        chunk.getItems().stream().forEach(
                (item) -> {
                   LOG.info("The item is "+item);
                });
    }


}
