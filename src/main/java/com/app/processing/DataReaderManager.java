package com.app.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.app.error.ErrorHandler;
import com.app.processing.DataTypeDetector;
import com.app.statistics.StatisticsCollector;

public class DataReaderManager {
    public static void distributeDataFromFiles(List<String> files, File analyzeFilesDir,
            DataTypeDetector types, StatisticsCollector stats) {
        for (var currentFile : files) {

            File file = new File(analyzeFilesDir, currentFile);

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        analyzeLine(line, types, stats);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ErrorHandler.noFile(file);
            }
        }
    }

    private static void analyzeLine(String line, DataTypeDetector types, StatisticsCollector stats) {
        line = line.trim();
        if (line.isEmpty()) {
            System.out.println("Пустая строка");
            return;
        }

        try {
            if (DataTypeDetector.isLong(line)) {
                types.addArr(Long
                        .parseLong(line));
                stats.addIntsCounter();
            } else if (DataTypeDetector.isDouble(line)) {
                types.addArr(Double
                        .parseDouble(line));
                stats.addDoublesCounter();
            } else {
                types.addArr(line);
                stats.addStringsCounter();
            }
        } catch (NumberFormatException e) {
            System.out.println("Не удалось распарсить строку: " + line);
            types.addArr(line); // Можно считать строкой, если парсинг не удался
        }
    }
}
