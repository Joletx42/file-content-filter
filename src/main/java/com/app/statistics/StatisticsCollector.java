package com.app.statistics;

import java.util.Collections;
import java.util.List;

import com.app.util.TerminalColors;

public class StatisticsCollector {
    private int amountStringsInFile = 0;
    private int amountIntsInFile = 0;
    private int amountDoublesInFile = 0;
    private String pathFile = "";
    private long maxLong = 0;
    private long minLong = 0;
    private long sumLong = 0;
    private double maxDouble = 0.0;
    private double minDouble = 0.0;
    private double averageLong = 0.0;
    private double averageDouble = 0.0;
    private double sumDouble = 0.0;
    private int minStrLength = 0;
    private int maxStrLength = 0;
    private boolean flagInts = false;
    private boolean flagDoubles = false;
    private boolean flagStrings = false;

    private static final String LINE = "--------------------------------------------------";

    public void addIntsCounter() {
        amountIntsInFile++;
    }

    public void addDoublesCounter() {
        amountDoublesInFile++;
    }

    public void addStringsCounter() {
        amountStringsInFile++;
    }

    public void printShortSummary() {
        System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
        System.out.println(TerminalColors.colorize("КОЛИЧЕСТВО ЗАПИСАННЫХ ЭЛЕМЕНТОВ В ФАЙЛ", TerminalColors.GREEN));
        System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
        if (amountIntsInFile != 0) {
            System.out.println(pathFile + "/integers.txt: " + amountIntsInFile);
        }
        if (amountDoublesInFile != 0) {
            System.out.println(pathFile + "/floats.txt: " + amountDoublesInFile);
        }
        if (amountStringsInFile != 0) {
            System.out.println(pathFile + "/strings.txt: " + amountStringsInFile);
        }
        System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
    }

    public void printFullSummary() {
        printShortSummary();

        if (flagInts) {
            System.out.println(
                    TerminalColors.colorize("СТАТИСТИКА ЗАПИСАННЫХ ЭЛЕМЕНТОВ В integers.txt", TerminalColors.GREEN));
            System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
            System.out.println(
                    "Минимальное значение integers: " + minLong);
            System.out.println(
                    "Максимальное значение integers: " + maxLong);
            System.out.println("Сумма integers: " + sumLong);
            System.out.println(
                    "Среднее значение integers: " + averageLong);
            System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
        }

        if (flagDoubles) {
            System.out.println(
                    TerminalColors.colorize("СТАТИСТИКА ЗАПИСАННЫХ ЭЛЕМЕНТОВ В floats.txt", TerminalColors.GREEN));
            System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
            System.out.println(
                    "Минимальное значение floats: " + minDouble);
            System.out.println(
                    "Максимальное значение floats: " + maxDouble);
            System.out.println("Сумма floats: " + sumDouble);
            System.out.println(
                    "Среднее значение floats: " + averageDouble);
            System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
        }

        if (flagStrings) {
            System.out.println(
                    TerminalColors.colorize("СТАТИСТИКА ЗАПИСАННЫХ ЭЛЕМЕНТОВ В strings.txt", TerminalColors.GREEN));
            System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
            System.out.println("Длина самой короткой строки: " + minStrLength);
            System.out.println("Длина самой длинной строки: " + maxStrLength);
            System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
        }
    }

    public void collectInts(List<Long> list, String path) {
        if (list == null || list.isEmpty()) {
            return;
        }

        flagInts = true;

        minLong = Collections.min(list);
        maxLong = Collections.max(list);

        for (var elem : list) {
            sumLong += elem;
        }

        averageLong = (double) sumLong / list.size();

        if (path != null)
            pathFile = path;
    }

    public void collectDoubles(List<Double> list, String path) {
        if (list == null || list.isEmpty()) {
            return;
        }

        flagDoubles = true;

        minDouble = Collections.min(list);
        maxDouble = Collections.max(list);

        for (var elem : list) {
            sumDouble += elem;
        }

        if (path != null)
            pathFile = path;

        averageDouble = sumDouble / list.size();
    }

    public void collectStrings(List<String> list, String path) {
        if (list == null || list.isEmpty()) {
            return;
        }

        flagStrings = true;

        minStrLength = list.get(0).length();
        maxStrLength = list.get(0).length();
        for (String elem : list) {
            if (elem.length() < minStrLength) {
                minStrLength = elem.length();
            }
            if (elem.length() > maxStrLength) {
                maxStrLength = elem.length();
            }
        }

        if (path != null)
            pathFile = path;
    }
}
