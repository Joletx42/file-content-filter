package com.app.processing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.app.args.ArgsParser;
import com.app.error.ErrorHandler;

public class DataWriterManager {
    public static void createAndWriteInFile(ArgsParser parser, List<?> dataList, File filesDir, String fileName) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        File file = new File(filesDir, fileName);

        if (!checkDirectoryExists(filesDir)) {
            return;
        }

        if (!writeInFile(parser, dataList, file, fileName)) {
            return;
        }

    }

    private static boolean checkDirectoryExists(File filesDir) {
        if (!filesDir.exists()) {
            if (filesDir.mkdirs()) {
                return true;
            } else {
                ErrorHandler.makeDirsError(filesDir.getAbsolutePath());
                return false;
            }
        }

        return true;
    }

    private static boolean writeInFile(ArgsParser parser, List<?> dataList, File file, String fileName) {
        if (dataList == null || dataList.isEmpty()) {
            return false;
        }

        if (file == null) {
            return false;
        }

        boolean append = parser.getRecordMode();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, append))) {
            for (var value : dataList) {
                bw.write(value.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            ErrorHandler.writerError(e, fileName);
            return false;
        }

        return true;
    }
}
