package logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLog {

    public static void log(String text){
        Logger log = LoggerFactory.getLogger(CustomLog.class);
        log.info("\n" + text + "\n");
    }

    public static void log(String text, Class clazz){
        Logger log = LoggerFactory.getLogger(clazz);
        log.info("\n" + text + "\n");
    }

    public static void log(String text, Throwable exception){
        Logger log = LoggerFactory.getLogger(CustomLog.class);
        log.error(text, exception);
    }
}
