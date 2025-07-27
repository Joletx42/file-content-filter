package com.app.processing;

import java.util.ArrayList;

import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.app.args.ArgsParser;
import com.app.processing.DataTypeDetector;

public class FileProcessing {
    private ArgsParser parser;

    public FileProcessing(ArgsParser parser) {
        this.parser = parser;
    }

    public void processFiles() {
        ArrayList<String> files = new ArrayList<>();
        files = parser.getFileList();
        int i = 0;

        File filesDir = new File("src\\test\\resoursec\\testFiles");
        DataTypeDetector type = new DataTypeDetector();

        while (i != files.size()) {
            String currentFile = files.get(i);

            File file = new File(filesDir, currentFile);

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
                System.out.println("Такого файла не существует");
            }

            i++;
        }

        System.out.println(type.getIntList());
        System.out.println(type.getDoubleList());
        System.out.println(type.getStrList());

        String currentDir = System.getProperty("user.dir");

        String pathToFiles = parser.getPathToFiles();
        File dir;
        File newFile;

        if (pathToFiles == null) {
            newFile = new File(currentDir + File.separator + "String.txt");
        } else {
            newFile = new File(currentDir + File.separator + pathToFiles + File.separator + "String.txt");

            dir = new File(currentDir + File.separator + pathToFiles); // <--- Здесь исправление
            System.out.println(dir);

            if (!dir.exists()) {
                boolean dirsCreated = dir.mkdirs();
                if (dirsCreated) {
                    System.out.println("Папки созданы: " + dir.getAbsolutePath());
                } else {
                    System.out.println("Не удалось создать папки: " + dir.getAbsolutePath());
                    return;
                }
            }
        }

        if (!newFile.exists()) {
            try {
                boolean fileCreated = newFile.createNewFile();
                if (fileCreated) {
                    System.out.println("Файл создан: " + newFile.getAbsolutePath());
                } else {
                    System.out.println("Файл не был создан: " + newFile.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл уже существует: " + newFile.getAbsolutePath());
        }
    }

    private static void analyzeLine(String line, DataTypeDetector type) {
        line = line.trim();
        if (line.isEmpty()) {
            System.out.println("Пустая строка");
            return;
        }

        // Пример определения типа данных строки
        if (DataTypeDetector.isInteger(line)) {
            System.out.println(line + " - это целое число");
            type.addArr(Integer
                    .parseInt(line));
        } else if (DataTypeDetector.isDouble(line)) {
            System.out.println(line + " - это число с плавающей точкой");
            type.addArr(Double
                    .parseDouble(line));
        } else {
            System.out.println(line + " - это строка");
            type.addArr(line);
        }
    }
}
