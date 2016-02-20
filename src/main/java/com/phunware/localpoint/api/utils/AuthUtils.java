package com.phunware.localpoint.api.utils;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

/**
 * Created by vinodkumar on 12/8/15.
 */
public class AuthUtils {

    static Properties prop = new PropertyManager().loadProperties("config.properties");

    static String version=prop.getProperty("version");
    static String secretKey=prop.getProperty("secretkey");
    static String timeStamp=prop.getProperty("timestamp");
    static String userId=prop.getProperty("userid");
    static String userType=prop.getProperty("usertype");
    static String uri=prop.getProperty("campaignResource");
    static String params=""; //prop.getProperty("params");


     public static String getAuthenticationKey(String body) throws NoSuchAlgorithmException, UnsupportedEncodingException{
         //Need to escape the quotes in json body
         body=body.replace("\"","\\\"");

         //System.out.println("print the body:  "+System.getProperty("line.separator")+body);
        String  signatureRaw = secretKey + timeStamp + userId + userType + uri + params + body;

        // System.out.println("signatureRaw:  "+ signatureRaw);
        String sh = "lpauth_user_id=" + userId + ",lpauth_user_type=" + userType
                +  ",lpauth_signature=" + getSignature(signatureRaw) + ",lpauth_timestamp=" + timeStamp + ",lpauth_version=" + version;
         //System.out.println("sh:  "+sh);

         byte[] encodedBytes = Base64.getEncoder().encode(sh.getBytes());
        return "LocalpointAuth " + new String(encodedBytes);
    }


    private static StringBuffer getSignature(
            String signatureRaw) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String encoded = URLEncoder.encode(signatureRaw, "UTF-8")
                .replaceAll("\\+", "%20").replaceAll("\\*", "%2A").replaceAll("%7E", "~");


        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(encoded.getBytes());
        byte byteData[] = md.digest();

        //convert the byte to hex format
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        //System.out.println("Hex format : " + hexString.toString());
        return hexString;
    }


}
