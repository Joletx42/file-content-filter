package com.app;

import com.app.args.ArgsParser;
import com.app.statistics.StatisticsCollector;
import com.app.error.ErrorHandler;
import com.app.processing.FileProcessing;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                ErrorHandler.emptyArgs();
            } else {
                // Разбор аргументов
                ArgsParser parser = new ArgsParser(args);
                parser.parse();

                if (parser.getHelpOption()) {
                    ErrorHandler.printHelp();
                } else {
                    // Обработка файлов
                    FileProcessing processor = new FileProcessing(parser);
                    processor.processFiles();

                    // Вывод статистики
                    if (!processor.checkIsEmptyLists()) {
                        char statisticsOption = parser.getStatisticsOption();
                        if (statisticsOption == 's' || statisticsOption == 'f') {
                            StatisticsCollector stats = processor.getStatisticsCollector();

                            if (statisticsOption == 's') {
                                stats.printShortSummary();
                            } else {
                                stats.printFullSummary();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            ErrorHandler.handle(e);
        }
    }
}
