package com.phunware.localpoint.api.utils;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vinodkumar on 11/24/15.
 */
public class FileUtils {

    private String jsonText;


    public String getJsonText(File file) throws IOException {


        try (
                BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            jsonText = sb.toString();
        }
       // System.out.println("text form of json:" + jsonText);
        return jsonText;
    }

    public static String fileNameAndPath(String dirPath) {

        Date date= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = sdf.format(date);

        //System.out.println("file name is: "+ "logfile"+"_"+formattedDate+".log");
        return "log"+"_"+formattedDate+".log";

    }

}
