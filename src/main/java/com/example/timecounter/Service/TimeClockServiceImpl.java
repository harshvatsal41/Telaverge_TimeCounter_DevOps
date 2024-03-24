package com.example.timecounter.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class TimeClockServiceImpl implements TimeClockService {


    private static final String FILE_NAME = "time.txt";



    @Override
    public void stopClock(long elapsedTime) {
        saveTimeToFile(elapsedTime);
    }





    private String formatTime(long milliseconds) {
        long hours = milliseconds / (1000 * 60 * 60);
        long minutes = (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (milliseconds % (1000 * 60)) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void saveTimeToFile(long elapsedTime) {
        System.out.println(formatTime(elapsedTime));
        LocalDateTime currentDateTime = LocalDateTime.now();

        String formattedTime = "Time : "+formatTime(elapsedTime)+" saved at "+currentDateTime;
        System.out.println(formattedTime);

        try {
            File file = new File(FILE_NAME);

            // Check if the file exists, create it if not
            if (!file.exists()) {
                file.createNewFile();
            }


            // Write formatted time to the file
            FileWriter fw = new FileWriter(file, true); // true for append mode
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(formattedTime);
            bw.newLine();
            bw.close();

            System.out.println("Time data saved to file.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }}
}
