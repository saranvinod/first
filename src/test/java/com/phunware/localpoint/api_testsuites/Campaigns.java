package com.phunware.localpoint.api_testsuites;

import com.phunware.localpoint.api.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.http.client.utils.URIBuilder;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.Map;

/**
 * Created by vinodkumar on 11/5/15.
 */


public class Campaigns {


    JsonResponseParser jsonParser = new JsonResponseParser();
    Logger logger = new LogUtils().createLogger("/logs/");
    HttpResponse response = null;
    int campaignIdValue = 0;

    @BeforeClass
    public void setUp() {
    }

    @AfterClass
    public void tearDown() {
        logger.info("Finishing CAMPAIGN TestCases");
    }


    @Parameters({"url", "filePath"})
    @Test
    public void verifyCreateCampaign(String url, String filePath) {
        try {

            // switch startDate and endDate in the request to currentDate and currentDate+24 hours respectively
            //regex to capture startDate and endDate line in request
            String startDateRegex = "(?=\"startDate\":).*";
            String endDateRegex = "(?=\"endDate\":).*";
            Map map= new HashMap<String,String>();

            FileUtils fileUtils = new FileUtils();
            File file = new File(filePath);
            String jsonAsaString = fileUtils.getJsonText(file);


            String AUTH_VALUE = AuthUtils.getAuthenticationKey(jsonAsaString);
            map.put("AUTH",AUTH_VALUE);
            map.put("content-Type", "application/json");


            //System.out.println("Original postText:"+System.getProperty("line.separator")+jsonAsaString);
            jsonAsaString = jsonAsaString.replaceAll(startDateRegex, "\"startDate\": \"" + TimeUtils.setStartDate() + "\",");
            jsonAsaString = jsonAsaString.replaceAll(endDateRegex, "\"endDate\": \"" + TimeUtils.setEndDate() + "\",");
            //System.out.println("Modified postText:"+System.getProperty("line.separator")+jsonAsaString);


            response = HttpUtils.doPost(url, jsonAsaString,map);

            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
//          System.out.println("Response Description: " + response );
            System.out.println("Response Json: "+responseString);
            System.out.println("Response Status: "+response.getStatusLine().getStatusCode());

            campaignIdValue = jsonParser.getIntValueFromJson(responseString, "id");
            System.out.println("response: "+response+System.getProperty("line.separator")+"campaign ID value: "+campaignIdValue);


            if (response.getStatusLine().getStatusCode() != 200) {
                responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("Response Description: " + response + System.getProperty("line.separator") + responseString);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            Assert.assertTrue(campaignIdValue>0);
        }
    }




    // Convert promotional content into html text
    @Test
    public void verifyPromoContent() {
        System.out.println("verifyPromoContent ");
    }


    @Parameters("url")
    @Test
    public void verifyRetrieveCampaign(String url) {

        //@@ create a campaign, make sure to desc the campaign should not be modiifed. change the id below.
        try {
            System.out.println("testing GET");
            Map map= new HashMap<String,String>();


            String AUTH_VALUE = AuthUtils.getAuthenticationKey("23");
            map.put("AUTH",AUTH_VALUE);
            map.put("content-Type", "application/json");


            response = HttpUtils.doGet(url);
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("control switches to response");

            if (response.getStatusLine().getStatusCode() != 200) {
                //System.out.println("responseDescription: " + response );
                System.out.println("Response Description: " + response + System.getProperty("line.separator") + responseString);
            }
            System.out.println("API Request SUCCESSFUL: "+responseString);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        }

    }

    @Test
    public void verifyRetrieveCollectionCampaign() {

    }

    @Test
    public void verifyRetrieveCampaignStatus() {

    }

    @Test
    public void verifyRetrieveCollectionCampaignStatus() {

    }

/*

    @Parameters({"url", "filePath"})
    @Test
    public void verifyUpdateCampaignStatusForScheduled(String url, String filePath) {

        try {
            response = HttpUtils.doPut(url, );

            FileUtils fileUtils = new FileUtils();
            File file = new File(filePath);
            String jsonAsaString = fileUtils.getJsonText(file);

        } catch (IOException ioe) {

        }
    }
*/


    @Test
    public void verifyUpdateCampaignStatusForActive() {

    }

    @Test
    public void verifyUpdateCampaignStatusForStopped() {

    }

    @Test
    public void verifyUpdateCampaignStatusForCompleted() {

    }


/*    @Parameters({"url", "filePath"})
    @Test
    public void verifyUpdateCampaign(String url, String filePath) {

        try {
            FileUtils fileUtils = new FileUtils();
            File file = new File(filePath);
            String jsonAsaString = fileUtils.getJsonText(file);

            response = HttpUtils.doPost(url,jsonAsaString);


            if (response.getStatusLine().getStatusCode() != 200) {
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("Response Description: " + response + System.getProperty("line.separator") + responseString);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        }

    }*/


}
