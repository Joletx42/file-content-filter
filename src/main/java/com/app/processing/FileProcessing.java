package com.app.processing;

import java.util.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.app.args.ArgsParser;

public class FileProcessing {
    private ArgsParser parser;
    private static final String FILES_DIR_PATH = "src/test/resources/testFiles";

    public FileProcessing(ArgsParser parser) {
        this.parser = parser;
    }

    public void processFiles() {
        List<String> files = parser.getFileList();

        File analyzeFilesDir = new File(FILES_DIR_PATH);
        DataTypeDetector type = new DataTypeDetector();

        analyzeFiles(files, analyzeFilesDir, type);

        List<Long> longList = type.getLongList();
        List<Double> doubleList = type.getDoubleList();
        List<String> strList = type.getStrList();

        String currentDir = System.getProperty("user.dir");
        String pathToFiles = parser.getPathToFiles();

        File newFilesDir;
        if (pathToFiles == null) {
            newFilesDir = new File(currentDir);
        } else {
            newFilesDir = new File(currentDir, pathToFiles);
        }

        String prefixFileName = parser.getPrefix();
        String prefix = (prefixFileName != null) ? prefixFileName : "";

        createFile(parser, longList, newFilesDir, prefix + "integers.txt");
        createFile(parser, doubleList, newFilesDir, prefix + "floats.txt");
        createFile(parser, strList, newFilesDir, prefix + "strings.txt");
    }

    private static void analyzeFiles(List<String> files, File analyzeFilesDir, DataTypeDetector type) {
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

    private static void createFile(ArgsParser parser, List<?> dataList, File filesDir, String fileName) {
        if (dataList == null || dataList.isEmpty()) {
            System.out.println(dataList + ": список пуст");
            return;
        }

        File newFile = new File(filesDir, fileName);

        if (!checkDirectoryExists(filesDir)) {
            return;
        }

        boolean append = parser.getRecordMode();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile, append))) {
            for (var value : dataList) {
                bw.write(value.toString());
                bw.newLine();
            }
            System.out.println("Данные записаны в файл: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static boolean checkDirectoryExists(File filesDir) {
        if (!filesDir.exists()) {
            if (filesDir.mkdirs()) {
                System.out.println("Папки созданы: " + filesDir.getAbsolutePath());
                return true;
            } else {
                System.out.println("Не удалось создать папки: " + filesDir.getAbsolutePath());
                return false;
            }
        }

        return true;
    }
}
