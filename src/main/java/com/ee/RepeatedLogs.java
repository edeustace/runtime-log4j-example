package com.ee;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

public class RepeatedLogs implements ServletContextListener {


    private static Logger LOG = Logger.getLogger(RepeatedLogs.class);

    public void contextDestroyed(ServletContextEvent contextEvent){}

    public void contextInitialized(ServletContextEvent contextEvent){

        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                LOG.info("info");
                LOG.error("error");
                LOG.debug("debug");
            }
        }, 2000, 5000);
    };

}
