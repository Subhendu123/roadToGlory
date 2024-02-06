package com.road2glory.splitwiseexpensetrackingapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SplitwiseService
{
    private static Logger LOG = LogManager.getLogger(SplitwiseService.class);
//    private static final SplitwiseLogger LOG = SplitwiseLogger.getLogger();

//    public ExpenseDetails setExpenseDetails()

    public Map<Integer, String> getExpenseMap(String responseBody)
    {
        Map<Integer, String> groups = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            JsonNode responseNode = objectMapper.readTree(responseBody);
            if (responseNode.has("groups") && responseNode.get("groups").isArray())
            {
                groups = new HashMap<>();

                JsonNode gropupsArr = responseNode.get("groups");

                int index = 0;
                while (gropupsArr.hasNonNull(index))
                {
                    JsonNode node = gropupsArr.get(index);
                    LOG.info("The Map object info: id " + node.get("id"));
                    LOG.info("The Map object info: name " + node.get("name"));

                    groups.put(node.get("id").asInt(), node.get("name").asText());
                    index++;
                }


//
//                MappingIterator<ArrayList> list = objectMapper.readValues(responseNode.get("groups").traverse(),
//                        JsonNode.class);

//                while (list.hasNext())
//                {
//                    System.out.println("The lst " + list.re);
//                }

            }
        } catch (JsonProcessingException e)
        {
            LOG.error("Error occurred during processing of JSON: " + e);
            throw new RuntimeException(e);
        }

        return groups;
    }
}
