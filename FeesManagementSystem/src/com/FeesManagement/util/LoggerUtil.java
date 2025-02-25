package com.FeesManagement.util;

import java.util.logging.*;
import java.io.IOException;

public class LoggerUtil {
    public static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            // Create a FileHandler to log messages to a file
            FileHandler fileHandler = new FileHandler("fees-management.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Set logger level to ALL to capture all log levels
            logger.setLevel(Level.ALL);

            // Add handler to the logger
            logger.addHandler(fileHandler);

            // Prevents logging messages from being printed twice
            logger.setUseParentHandlers(false);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);

        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }
}

