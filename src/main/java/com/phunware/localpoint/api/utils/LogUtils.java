package com.phunware.localpoint.api.utils;


import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;


import java.io.File;


public class LogUtils {


    private static final Logger logger = Logger.getLogger(LogUtils.class.getName());
    FileHandler fh;

    public Logger LogUtils(String logLocation) throws IOException {
        return createLogger(logLocation);
    }


    public Logger createLogger(String logLocation) {

        String logFile = FileUtils.fileNameAndPath(logLocation);
        FileHandler fh;
        File file;
        try {
            file = new File(System.getProperty("user.dir")+logLocation+logFile);
            System.out.println("user directory: "+System.getProperty("user.dir"));
            System.out.println("log file location: "+file.getCanonicalPath());

            fh = new FileHandler(file.getPath());
            logger.addHandler(fh);
        } catch (NoSuchFileException nse){
            nse.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        logger.setLevel(Level.INFO);
        return logger;
    }


    public Logger getConsoleLogger(Logger logger) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);
        return logger;
    }

}