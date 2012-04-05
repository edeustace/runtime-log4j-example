package com.ee;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoggingBootstrap implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent contextEvent) {
        LogManager.shutdown();
    }

    public void contextInitialized(ServletContextEvent contextEvent) {
        System.out.println("LoggingBootstrap::contextInitialized");
        new Log4jBootstrap();

    }

    private class Log4jBootstrap {
        /**
         * Environment variable - path to log4j.xml
         */
        private static final String RUNTIME_LOG4J_CONFIG_PATH = "RUNTIME_LOG4J_CONFIG_PATH";

        /**
         * Environment variable - watch the log4j.xml for changes? not recommended for production
         */
        private static final String RUNTIME_LOG4J_CONFIG_WATCH = "RUNTIME_LOG4J_CONFIG_WATCH";

        public Log4jBootstrap() {
            System.out.println("initialising Log4j in LoggingBootstrap");

            if (System.getenv(RUNTIME_LOG4J_CONFIG_PATH) != null) {
                System.out.println("found lo4j config path environment variable, loading config");
                String path = System.getenv(RUNTIME_LOG4J_CONFIG_PATH);
                boolean watch = System.getenv(RUNTIME_LOG4J_CONFIG_WATCH) == "true";
                if (watch) {
                    DOMConfigurator.configureAndWatch(path, 4000L);
                } else {
                    DOMConfigurator.configure(path);
                }
            } else {
                System.out.println("No environment variable found - using basic logger");
                BasicConfigurator.configure();
                Logger.getRootLogger().setLevel(Level.INFO);
            }

        }

    }


}
