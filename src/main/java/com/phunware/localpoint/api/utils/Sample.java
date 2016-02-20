package com.phunware.localpoint.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Sample {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // TODO Auto-generated method stub

        String version ="1.0";
        String timestamp="1";
        String usertype = "API";
        String secretKey = "ed393523-605b-43c9-b0b8-fed4cece34ab";
        String userid = "admin@qabrand.com";
        String uri = "/v1/campaign";
        String params = "";
        String body = "{ \"campaignName\": \"Flash sale on arrows!\",\"campaignType\": \"BROADCAST\", \"description\": \"flash sale announcement campaign\",\"enabled\": true,\"endDate\": \"2015-12-31 21:59\",\"messageMetadata\": {\"image\": \"flash_sale_arrows_1.jpg\",\"link\": \"beedlesshops.com/arrows\"},\"notificationMessage\": \"Ohhh, valued customer! Less rupees to pay for arrows!\",\"notificationTitle\": \"Flash sale on arrows!\",\"profiles\": [],\"promotionDuration\": 390,\"promotionMessage\": \"Enjoy snacks, pick up coupons and play games to win merchandise!\",\"promotionMessageType\": \"EDITOR\",\"promotionTitle\": \"At least 10% off on arrows!\",\"repeatFrequency\": 0,\"startDate\": \"2015-12-18 00:00\",\"targetTimeZone\": \"America/Chicago\"}";


        String key = getAuthenticationKey( version , secretKey, timestamp, userid,
                usertype,uri,params,body);
        System.out.println("The required Key value is:"+key);
    }


    private static String getAuthenticationKey(String version ,String secretKey,String timestamp,String userid,
                                               String usertype,String uri,String params,String body) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String  signatureRaw = secretKey + timestamp + userid + usertype + uri + params + body;
        System.out.println("signatureRaw:  "+ signatureRaw);
        String sh = "lpauth_user_id=" + userid + ",lpauth_user_type=" + usertype
                +  ",lpauth_signature=" + getSignature(signatureRaw) + ",lpauth_timestamp=" + timestamp + ",lpauth_version=" + version;
        System.out.println("sh:  "+sh);
        byte[] encodedBytes = Base64.getEncoder().encode(sh.getBytes());
        return "LocalpointAuth " + new String(encodedBytes);
    }


    private static StringBuffer getSignature(String signatureRaw) throws NoSuchAlgorithmException, UnsupportedEncodingException{

        String encoded = URLEncoder.encode(signatureRaw,"UTF-8")
                .replaceAll("\\+", "%20").replaceAll("\\*", "%2A").replaceAll("%7E", "~");;
// SHA256 conversion

        MessageDigest md  = MessageDigest.getInstance("SHA-256");
        md.update(encoded.getBytes());
        byte byteData[] = md.digest();

/*        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString());*/

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Hex format : " + hexString.toString());
        return hexString;
    }
}