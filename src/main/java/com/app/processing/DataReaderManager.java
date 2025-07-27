package com.app.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataReaderManager {
    public static void analyzeFiles(List<String> files, File analyzeFilesDir, DataTypeDetector type) {
        for (var currentFile : files) {

            File file = new File(analyzeFilesDir, currentFile);

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        analyzeLine(line, type);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Такого файла не существует: " + file);
            }
        }
    }

    private static void analyzeLine(String line, DataTypeDetector type) {
        line = line.trim();
        if (line.isEmpty()) {
            System.out.println("Пустая строка");
            return;
        }

        try {
            if (DataTypeDetector.isLong(line)) {
                type.addArr(Long
                        .parseLong(line));
            } else if (DataTypeDetector.isDouble(line)) {
                type.addArr(Double
                        .parseDouble(line));
            } else {
                type.addArr(line);
            }
        } catch (NumberFormatException e) {
            System.out.println("Не удалось распарсить строку: " + line);
            type.addArr(line); // Можно считать строкой, если парсинг не удался
        }
    }
}
