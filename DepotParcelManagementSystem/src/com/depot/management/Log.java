package com.depot.management;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private static Log instance; // Singleton used here
    private List<String> logs;   // List for log enteries to be stored


    private Log() {
        logs = new ArrayList<>();
    }

    // Singleton of log class
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Log entry is added to list
    public void addLog(String logEntry) {
        logs.add(logEntry);
        System.out.println("Log added: " + logEntry);
    }

    // All log entries are written to a file
    public void writeToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String log : logs) {
                writer.write(log + "\n");
            }
            System.out.println("Logs written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing logs to file: " + e.getMessage());
        }
    }

    // All logs are returned as a single string for GUI integration
    public String getAllLogs() {
        StringBuilder allLogs = new StringBuilder();
        for (String log : logs) {
            allLogs.append(log).append("\n");
        }
        return allLogs.toString();
    }
}
