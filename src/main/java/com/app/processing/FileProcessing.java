package com.app.processing;

import java.util.List;

import java.io.File;

import com.app.args.ArgsParser;
import com.app.processing.DataTypeDetector;
import com.app.processing.DataWriterManager;
import com.app.processing.DataReaderManager;

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

        DataReaderManager.distributeDataFromFiles(files, analyzeFilesDir, type);

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

        DataWriterManager.createAndWriteInFile(parser, longList, newFilesDir, prefix + "integers.txt");
        DataWriterManager.createAndWriteInFile(parser, doubleList, newFilesDir, prefix + "floats.txt");
        DataWriterManager.createAndWriteInFile(parser, strList, newFilesDir, prefix + "strings.txt");
    }
}
