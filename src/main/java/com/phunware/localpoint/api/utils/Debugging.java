package com.phunware.localpoint.api.utils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by vinodkumar on 1/7/16.
 */
public class Debugging {

    public static void main(String args[]) throws IOException {


           String startDateRegex ="(?=\"startDate\":).*";
           //String endDateRegex ="(?m=\"endDate\":)\\S+";
            String filePath="/Users/vinodkumar/Github/localpoint-api/src/main/resources/JSONs/campaign/CreateBC.txt";



            FileUtils fileUtils = new FileUtils();
            File file =new File(filePath);
            String jsonAsaString = fileUtils.getJsonText(file);


            //jsonAsaString=jsonAsaString.replaceA("(?=\"startDate\":)",TimeUtils.setStartDate());
            System.out.println("does it match: "+jsonAsaString.matches(startDateRegex));
            jsonAsaString=jsonAsaString.replaceAll("(?=\"startDate\":).*","\"startDate\": \""+TimeUtils.setStartDate()+"\",");

            //System.out.println("does it match: "+jsonAsaString.matches(startDateRegex));
            //System.out.println("does it match: "+postText.matches(endDateRegex));*/
           // System.out.println("startDate from TimeUtils: "+ TimeUtils.setStartDate());


            //jsonAsaString=jsonAsaString.replace(startDateRegex,TimeUtils.setStartDate());
            //jsonAsaString=jsonAsaString.replace(endDateRegex,TimeUtils.setEndDate());
            System.out.println("postText:"+System.getProperty("line.separator")+jsonAsaString);
    }
}
