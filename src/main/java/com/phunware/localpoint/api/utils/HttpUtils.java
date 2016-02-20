package com.phunware.localpoint.api.utils;


/**
 * Created by vinodkumar on 11/4/15.
 */

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;


/**
 * @desc This class performs all HTTP operations
 * @author bhargavas
 *
 */


public class HttpUtils {
    private static  Logger logger = LogManager.getLogger(HttpUtils.class);
    //private static String AUTH = "X-Auth";
    private static String AUTH = "Authorization";
    private static String AUTH_VALUE = null;
    private static HttpClient httpClient = null;
    private static HttpResponse response = null;



    public static HttpResponse doGet(String url,Map<String,String> map) throws HttpException,
            IOException, URISyntaxException {
        try {
            httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            System.out.println(httpGet.getRequestLine().getUri());

            if(!map.isEmpty()) {
                AUTH_VALUE = map.get("AUTH");
                httpGet.addHeader(AUTH, AUTH_VALUE);
                httpGet.addHeader("content-Type", "application/json");
            }

            response = httpClient.execute(httpGet);

        } catch (MalformedURLException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }finally {
            httpClient.getConnectionManager().closeExpiredConnections();
            return response;
        }
    }

    /**
     * This method performs HTTP Post operation
     *
     * @param url
     * @return
     * @throws URISyntaxException
     * @throws HttpException
     * @throws IOException
     */

    public static HttpResponse doPost(String url,
                                      String postText,Map<String,String> map) {

        try {

            httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);


            if(!map.isEmpty()) {
                AUTH_VALUE = map.get("AUTH");
                httpPost.addHeader(AUTH, AUTH_VALUE);
                httpPost.addHeader("content-Type", "application/json");
            }

                StringEntity input = new StringEntity(postText);
                input.setContentType("application/json");
                httpPost.setEntity(input);


            response=httpClient.execute(httpPost);
        } catch (MalformedURLException mue) {
            logger.error(mue);
            mue.printStackTrace();
        } catch (IOException ioe) {
            logger.error(ioe);
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("getting null pointer exception");
            logger.error(npe);
            npe.printStackTrace();
        }  finally {
            httpClient.getConnectionManager().closeExpiredConnections();
            return response;
        }
    }




    /**
     * This method performs HTTP Put operation
     *
     * @param putText
     * @return
     * @throws URISyntaxException
     * @throws HttpException
     * @throws IOException
     */
    public static HttpResponse doPut(String url,
                                     String putText,Map<String,String> map)
            throws URISyntaxException, HttpException, IOException {
        try {

            httpClient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut(url);

            if(!map.isEmpty()) {
                AUTH_VALUE = map.get("AUTH");
                httpPut.addHeader(AUTH, AUTH_VALUE);
                httpPut.addHeader("content-Type", "application/json");
            }

            StringEntity input = new StringEntity(putText);
            input.setContentType("application/json");
            httpPut.setEntity(input);

            response = httpClient.execute(httpPut);

        } catch (MalformedURLException mue) {
            logger.log(Level.ERROR,"did we catch it?",mue);
            mue.printStackTrace();
           // logger.error(mue);
        } catch (IOException ioe) {
            logger.log(Level.ERROR, "did we catch it?", ioe);
            ioe.printStackTrace();
        }finally {
            httpClient.getConnectionManager().closeExpiredConnections();
            return response;
        }
    }

    /**
     * This method performs HTTP Delete operation
     *
     * @return
     * @throws HttpException
     * @throws IOException
     * @throws URISyntaxException
     */

    public static HttpResponse doDelete(String url,String deleteParameters,Map<String,String> map) throws HttpException,
            IOException, URISyntaxException {
        try {
            httpClient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(url);

            if(!map.isEmpty()) {
                AUTH_VALUE = map.get("AUTH");
                httpDelete.addHeader(AUTH, AUTH_VALUE);
                httpDelete.addHeader("content-Type", "application/json");
            }

            response = httpClient.execute(httpDelete);
            return response;
        } catch (MalformedURLException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        finally {
            httpClient.getConnectionManager().closeExpiredConnections();
            return response;
        }
    }


}
