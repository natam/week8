package org.example.afternoon_practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final String LOG_FILE = "src/main/java/org/example/afternoon_practice/log.txt";

    public static void addLog(String logMessage){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            FileWriter logWriter = new FileWriter(LOG_FILE, true);
            logWriter.append(logMessage+ " " + dateFormat.format(new Date()));
            logWriter.append("\n");
            logWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getLogsNumber(){
        try {
            BufferedReader logsReader = new BufferedReader(new FileReader(LOG_FILE));
            long logsNumber = logsReader.lines().count();
            System.out.println(logsNumber + " user file interactions were logged");
            logsReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
