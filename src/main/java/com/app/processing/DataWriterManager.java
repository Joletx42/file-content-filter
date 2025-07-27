package com.app.processing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.app.args.ArgsParser;

public class DataWriterManager {
    public static void createFile(ArgsParser parser, List<?> dataList, File filesDir, String fileName) {
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
