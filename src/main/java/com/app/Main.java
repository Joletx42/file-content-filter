package com.app;

import com.app.args.ArgsParser;
// import com.app.statistics.StatisticsCollector;
import com.app.error.ErrorHandler;
import com.app.processing.FileProcessing;

public class Main {
    public static void main(String[] args) {
        try {
            // Разбор аргументов
            ArgsParser parser = new ArgsParser(args);
            parser.parse();

            System.out.println("File list: " + parser.getFileList());
            System.out.println("Parameters list: " + parser.getParamList());
            System.out.println("Path to files: " + parser.getPathToFiles());
            System.out.println("Prefix: " + parser.getPrefix());
            System.out.println("Record mode: " + parser.getRecordMode());
            System.out.println("Statistics option: " + parser.getStatisticsOption());
            System.out.println();

            // Обработка файлов
            FileProcessing processor = new FileProcessing(parser);
            processor.processFiles();

            // // Вывод статистики
            // StatisticsCollector stats = processor.getStatisticsCollector();
            // stats.printSummary();

        } catch (Exception e) {
            ErrorHandler.handle(e);
        }
    }
}
