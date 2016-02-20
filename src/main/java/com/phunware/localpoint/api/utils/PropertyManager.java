package com.phunware.localpoint.api.utils;

import org.apache.logging.log4j.LogManager;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.Logger;

/**
 *
 * Created by vinodkumar on 12/8/15.
 */

@SuppressWarnings("nls")
public class PropertyManager
{
    private static PropertyManager propManager = new PropertyManager();
    private Logger log = LogManager.getLogger(PropertyManager.class);

    /**
     * @description::Method used to return the instance of property manager
     *                      class
     * @return - instance of property manager class
     */
    public static PropertyManager getPropertyManager()
    {
        return propManager;
    }


    /**
     * @description::Method to load properties file
     * @param fileName
     *            - name of file
     * @return - loaded properties file
     */
    public Properties loadProperties(String fileName)
    {

        Properties props = new Properties();
        try
        {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
            props.load(is);

        } catch (Exception ex)
        {
            log.error("Print stack trace" + ex.getStackTrace());
        }
        return props;

    }
}