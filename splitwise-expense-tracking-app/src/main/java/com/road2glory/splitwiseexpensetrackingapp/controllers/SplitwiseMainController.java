package com.road2glory.splitwiseexpensetrackingapp.controllers;

import com.road2glory.splitwiseexpensetrackingapp.SplitwiseService;
import com.road2glory.splitwiseexpensetrackingapp.constants.DateFormatPattern;
import com.road2glory.splitwiseexpensetrackingapp.exceptions.DateTimeConvertionException;
import com.road2glory.splitwiseexpensetrackingapp.loggers.SplitwiseLogger;
import com.road2glory.splitwiseexpensetrackingapp.models.ExpenseDetails;
import com.road2glory.splitwiseexpensetrackingapp.models.User;
import com.road2glory.splitwiseexpensetrackingapp.services.ExcelUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class SplitwiseMainController
{


    // private static final Logger LOG = LogManager.getLogger(SplitwiseMainController.class);
    private static final SplitwiseLogger LOG = SplitwiseLogger.getLogger();

    public static Map<Integer, String> groupsInMap = null;
    //    @Value("${application.url}")
    private static String URI = "https://secure.splitwise.com/api/v3.0";
    //    @Value("${application.userToken}")
    private static String BEARER_TOKEN = "4wsrEpR8VuHZswSmVQcFLFaE4bHDRORTemDM02P9";
    @Autowired
    private ExcelUtilityService excelUtilityService;

    @Autowired
    private SplitwiseService splitwiseService;

    @GetMapping(value = "/export-splitwise-to-excel")
    public ResponseEntity<Resource> exportSplitwiseExpenses (@RequestParam("dated_after") String datedAfter, @RequestParam("dated_before") String datedBefore, @RequestParam("group_name") String groupName)

    {
        // call splitwise ctrl for collecting splitwise data
        // Call the api to list all the groups

        LOG.info("Entry to the method exportSplitwiseExpenses");
        ResponseEntity<List<User>> responseEntityUser = null;
        List<User> userDetailsList = null;
        if (groupsInMap == null)
        {
            LOG.info("Calling the group entity as the group details are not available");
            ResponseEntity<Map<Integer, String>> responseEntity = this.fetchAllGroupsForCurrentUser();
            groupsInMap = responseEntity.getBody();
            LOG.debug("The group map is " + groupsInMap);
        }
        // GroupMap is having details
        Integer groupId = groupsInMap.size() > 0 && !groupsInMap.isEmpty() ? groupsInMap.entrySet()
                                                                                        .stream()
                                                                                        .filter(entry -> entry.getValue()
                                                                                                              .indexOf(groupName) > -1)
                                                                                        .findFirst()
                                                                                        .get()
                                                                                        .getKey() : 0;
        LOG.info("The group Id fetched is " + groupId);

        try
        {
            responseEntityUser = this.getExpensesForCurrentUser(groupId, datedAfter, datedBefore, 500);

            userDetailsList = responseEntityUser.getBody();
            LOG.info("The user list size " + userDetailsList.size());

        }
        catch (JSONException e)
        {

            LOG.error("The exception is occurred due to " + e);
            //            return ResponseEntity.badRequest().body(new ResponseMessage("Error while fetching data from Splitwise or Gmail API. Contact System Administrator.", HttpStatus.BAD_REQUEST.value()));
        }

        ByteArrayResource workbook = null;
        try
        {
            LOG.info("Calling exportToExcel...");
            workbook = excelUtilityService.exportToExcel(null, userDetailsList);

        }
        catch (IOException e)
        {
            LOG.error("The export to excel is failed with error " + e);
            e.printStackTrace();
            //            return ResponseEntity.internalServerError().body(new ResponseMessage("Error occured during process of Export to Excel.", HttpStatus.FORBIDDEN.value()));
            //row new RuntimeException(e);
        }
        LOG.info("The export work completed");
        String fileName = groupName + "_" + datedBefore + ".xlsx";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return ResponseEntity.ok()
                             .headers(headers)
                             .contentLength(workbook.contentLength())
                             .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                             .body(workbook);
        //        return workbook;
        //        return ResponseEntity.ok(new ResponseMessage("Successfully Written", HttpStatus.OK.value()));
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getHello ()
    {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping(value = "/list-all-groups")
    public ResponseEntity<Map<Integer, String>> fetchAllGroupsForCurrentUser ()
    {

        String fetchURL = SplitwiseMainController.URI + "/get_groups";

        String userToken = SplitwiseMainController.BEARER_TOKEN;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userToken);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<Integer, String>> result = null;

        // Authorization using the Consumer Key and secret

        try
        {
            String responseBody = restTemplate.exchange(fetchURL, HttpMethod.GET, entity, String.class).getBody();
            LOG.info("The response body is " + responseBody);

            if (groupsInMap == null) groupsInMap = splitwiseService.getExpenseMap(responseBody);
            result = ResponseEntity.ok(groupsInMap);
        }
        catch (HttpClientErrorException ie)
        {
            System.out.println("THE RESPONSE IS " + ie);
            result = new ResponseEntity(ie.getMessage(), ie.getStatusCode());
        }
        finally
        {
            return result;
        }


    }

    @GetMapping(value = "/getTotalExpense")
    public ResponseEntity<List<User>> getExpensesForCurrentUser (@RequestParam("group_id") int groupId, @RequestParam("dated_after") String datedAfter, @RequestParam("dated_before") String datedBefore, @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) throws JSONException
    {

        String fetchURL = SplitwiseMainController.URI + "/get_expenses";
        StringBuilder finalURL = new StringBuilder(fetchURL);
        finalURL.append("?")
                .append("group_id=")
                .append(groupId)
                .append("&")
                .append("dated_after=")
                .append(datedAfter)
                .append("&")
                .append("dated_before=")
                .append(datedBefore)
                .append("&")
                .append("limit=")
                .append(limit);

        //        String extractedToken = userToken.split("user_token").length == 2
        //                ? userToken.split("user_token")[1].trim() : userToken;

        String userToken = SplitwiseMainController.BEARER_TOKEN;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userToken);


        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = null;
        String responseBody = null;
        try
        {
            responseBody = restTemplate.exchange(finalURL.toString(), HttpMethod.GET, entity, String.class).getBody();
            result = ResponseEntity.ok(responseBody);
        }
        catch (HttpClientErrorException ie)
        {
            LOG.error("The exception is " + ie);
            result = new ResponseEntity(ie.getMessage(), ie.getStatusCode());
            return result;
        }
        JSONObject jsonObject = new JSONObject(responseBody);
        LOG.info("THE Json Response Body " + jsonObject);
        //        return ResponseEntity.ok(jsonObject.toString());
        JSONArray expensesArr = jsonObject.getJSONArray("expenses");

        List<User> responseUserArr = new ArrayList<>();


        for (int i = 0; i < expensesArr.length(); i++)
        {
            JSONObject expense = expensesArr.getJSONObject(i);
            //  if(payment == true) // skip
            Boolean payment = expense.getBoolean("payment");

            LOG.debug("payment value for expense Id (" + expense.getLong("id") + ") is " + payment);
            if (payment || expense.getString("creation_method").equalsIgnoreCase("debt_consolidation")) continue;
            if (!expense.getString("deleted_by").equals("null")) continue;
            // if(users[0].paid_share == 0 || users[0].owed_share == 0) // skip
            JSONArray users = expense.getJSONArray("users");
            if (users != null && users.length() > 0)
            {

                for (int j = 0; j < users.length(); j++)
                {
                    boolean isNewUser = false;
                    JSONObject user = users.getJSONObject(j);
                    double paidShare = user.getDouble("paid_share");
                    double owedShare = user.getDouble("owed_share");

                    //IF I paid the amount, users[0].paid_share > 0
                    if (paidShare > 0 || owedShare > 0)
                    {
                        long id = user.getLong("user_id");
                        User matchedUser = responseUserArr.isEmpty() || responseUserArr.stream()
                                                                                       .filter(user1 -> user1.getId() == id)
                                                                                       .findAny()
                                                                                       .isEmpty() ? null : responseUserArr.stream()
                                                                                                                          .filter(user1 -> user1.getId() == id)
                                                                                                                          .findAny()
                                                                                                                          .get();

                        // if the user already exists
                        if (matchedUser == null || matchedUser.getId() == 0L)
                        {
                            matchedUser = new User();
                            isNewUser = true;
                            JSONObject personalDetails = user.getJSONObject("user");
                            matchedUser.setId(personalDetails.getLong("id"));
                            String lastName = personalDetails.getString("last_name")
                                                             .equalsIgnoreCase("null") ? "" : personalDetails.getString("last_name");
                            matchedUser.setName(personalDetails.getString("first_name") + " " + lastName);
                            matchedUser.setAllExpenseDetails(new ArrayList<>());
                        }

                        // Paid by me
                        if (paidShare > 0 && owedShare > 0)
                        {
                            double totalExpenses = matchedUser.getTotalExpenses();
                            double myCotri = matchedUser.getMyContributions();
                            totalExpenses = totalExpenses + owedShare;
                            myCotri = myCotri + owedShare;
                            matchedUser.setTotalExpenses(totalExpenses);
                            matchedUser.setMyContributions(myCotri);

                            List<ExpenseDetails> expenseDetailsList = matchedUser.getAllExpenseDetails();

                            ExpenseDetails expenseDetails = new ExpenseDetails();
                            expenseDetails.setAmount(owedShare);
                            expenseDetails.setDescription(expense.getString("description"));
                            expenseDetails.setPaidbyme(true);
                            SimpleDateFormat format = new SimpleDateFormat(DateFormatPattern.DATE_FORMAT_WITH_T);
                            String dateString = expense.getString("created_at");
                            //                            String inputDate = dateString.split("T")[0];
                            try
                            {
                                expenseDetails.setRecordDate(format.parse(dateString));
                            }
                            catch (ParseException e)
                            {
                                LOG.error("The Date Time parsing error is due to " + e);
                                throw new DateTimeConvertionException("The Date Time parsing error is due to " + e.getMessage());
                                // write response entity logic here
                            }
                            expenseDetails.setCategory(expense.getJSONObject("category").getString("name"));

                            expenseDetailsList.add(expenseDetails);


                            // user might need to be updated here
                        }
                        else if (owedShare > 0 && paidShare == 0)
                        {
                            double totalExpenses = matchedUser.getTotalExpenses();
                            double othersContri = matchedUser.getOthersContributions();
                            totalExpenses = totalExpenses + user.getDouble("owed_share");
                            othersContri = othersContri + user.getDouble("owed_share");
                            matchedUser.setTotalExpenses(totalExpenses);
                            matchedUser.setOthersContributions(othersContri);
                            // user might need to be updated here


                            List<ExpenseDetails> expenseDetailsList = matchedUser.getAllExpenseDetails();

                            ExpenseDetails expenseDetails = new ExpenseDetails();
                            expenseDetails.setAmount(owedShare);
                            expenseDetails.setDescription(expense.getString("description"));
                            expenseDetails.setPaidbyme(false);
                            SimpleDateFormat format = new SimpleDateFormat(DateFormatPattern.DATE_FORMAT_WITH_T);
                            String dateString = expense.getString("created_at");
                            //                            String inputDate = dateString.split("T")[0];
                            try
                            {
                                expenseDetails.setRecordDate(format.parse(dateString));
                            }
                            catch (ParseException e)
                            {
                                LOG.error("The Date Time parsing error is due to " + e);
                                throw new DateTimeConvertionException("The Date Time parsing error is due to " + e.getMessage());
                                // write response entity logic here
                            }
                            expenseDetails.setCategory(expense.getJSONObject("category").getString("name"));

                            expenseDetailsList.add(expenseDetails);
                        }

                        if (isNewUser) responseUserArr.add(matchedUser);
                    }


                }
            }


        }

        return ResponseEntity.ok(responseUserArr);

        //                IF I paid the amount, users[0].paid_share > 0
        //
        //        The cost of the service / prod is users[0].owed_share (users...)
    }

    @RequestMapping(value = "/get_current_user", method = RequestMethod.GET)
    public ResponseEntity<User> getDetails ()
    {

        String fetchURL = SplitwiseMainController.URI + "/get_current_user";
        String userToken = SplitwiseMainController.BEARER_TOKEN;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userToken);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> result = null;

        // Authorization using the Consumer Key and secret

        try
        {
            String responseBody = restTemplate.exchange(fetchURL, HttpMethod.GET, entity, String.class).getBody();

            //            result = ResponseEntity.ok(responseBody);
        }
        catch (HttpClientErrorException ie)
        {
            System.out.println("THE RESPONSE IS " + ie);
            result = new ResponseEntity(ie.getMessage(), ie.getStatusCode());
        }
        finally
        {
            return result;
        }


    }


}
