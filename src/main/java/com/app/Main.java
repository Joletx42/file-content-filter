package com.app;

import com.app.args.ArgsParser;
import com.app.statistics.StatisticsCollector;
import com.app.error.ErrorHandler;
import com.app.processing.FileProcessing;

public class Main {
    public static void main(String[] args) {
        try {
            // Разбор аргументов
            ArgsParser parser = new ArgsParser(args);
            parser.parse();

            // Обработка файлов
            FileProcessing processor = new FileProcessing(parser);
            processor.processFiles();

            // Вывод статистики
            char statisticsOption = parser.getStatisticsOption();
            if (statisticsOption == 's' || statisticsOption == 'f') {
                StatisticsCollector stats = processor.getStatisticsCollector();

                if (statisticsOption == 's') {
                    stats.printShortSummary();
                } else {
                    stats.printFullSummary();
                }

            }

        } catch (Exception e) {
            ErrorHandler.handle(e);
        }
    }
}
