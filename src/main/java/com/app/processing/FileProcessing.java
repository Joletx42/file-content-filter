package com.app.processing;

import java.util.List;

import java.io.File;

import com.app.args.ArgsParser;
import com.app.processing.DataTypeDetector;
import com.app.processing.DataWriterManager;
import com.app.statistics.StatisticsCollector;
import com.app.processing.DataReaderManager;

public class FileProcessing {
    private ArgsParser parser;
    private static final String FILES_DIR_PATH = "src/test/resources/testFiles";
    private StatisticsCollector stats = new StatisticsCollector();
    private DataTypeDetector types = new DataTypeDetector();

    public FileProcessing(ArgsParser parser) {
        this.parser = parser;
    }

    public void processFiles() {
        List<String> files = parser.getFileList();

        File analyzeFilesDir = new File(FILES_DIR_PATH);

        DataReaderManager.distributeDataFromFiles(files, analyzeFilesDir, types, stats);

        List<Long> longList = types.getLongList();
        List<Double> doubleList = types.getDoubleList();
        List<String> strList = types.getStrList();

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

        String fileIntsName = prefix + "integers.txt";
        String fileFloatsName = prefix + "floats.txt";
        String fileStringsName = prefix + "strings.txt";

        stats.collectInts(longList, parser.getPathToFiles(), fileIntsName);
        stats.collectDoubles(doubleList, parser.getPathToFiles(), fileFloatsName);
        stats.collectStrings(strList, parser.getPathToFiles(), fileStringsName);

        DataWriterManager.createAndWriteInFile(parser, longList, newFilesDir, fileIntsName);
        DataWriterManager.createAndWriteInFile(parser, doubleList, newFilesDir, fileFloatsName);
        DataWriterManager.createAndWriteInFile(parser, strList, newFilesDir, fileStringsName);
    }

    public StatisticsCollector getStatisticsCollector() {
        return stats;
    }

    public boolean checkIsEmptyLists() {
        return types.getStrList().isEmpty() && types.getLongList().isEmpty() && types.getDoubleList().isEmpty();
    }
}
