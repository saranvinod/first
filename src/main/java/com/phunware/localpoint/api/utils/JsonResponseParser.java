package com.phunware.localpoint.api.utils;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by vinodkumar on 12/2/15.
 */
public class JsonResponseParser  {
    private static String responseString;


    public static String getStringValueFromJson(String responseString,String key) throws JSONException,IOException {

            //responseString = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(responseString);
            return jsonObject.optString(key);

    }

    public static int getIntValueFromJson(String responseString,String key) throws JSONException,IOException {
        JSONObject jsonObject = new JSONObject(responseString);
        return jsonObject.optInt(key);
    }


    public double getDoubleValueFromJson(String responseString,String key) throws JSONException,IOException {
        //responseString = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(responseString);
        return jsonObject.optDouble(key);
    }
}
