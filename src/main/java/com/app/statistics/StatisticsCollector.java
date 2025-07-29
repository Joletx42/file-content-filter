package com.app.statistics;

import java.util.Collections;
import java.util.List;

import com.app.util.TerminalColors;

public class StatisticsCollector {
    private int amountStringsInFile = 0;
    private int amountIntsInFile = 0;
    private int amountDoublesInFile = 0;
    private long maxLong = 0;
    private long minLong = 0;
    private long sumLong = 0;
    private double maxDouble = 0.0;
    private double minDouble = 0.0;
    private double averageLong = 0.0;
    private double averageDouble = 0.0;
    private double sumDouble = 0.0;
    private boolean flagInts = false;
    private boolean flagDoubles = false;

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
        System.out.println(TerminalColors.colorize("КОЛИЧЕСТВО ЗАПИСАННЫХ ЭЛЕМЕНТОВ В ФАЙЛЫ", TerminalColors.GREEN));
        System.out.println(TerminalColors.colorize(LINE, TerminalColors.GREEN));
        System.out.println("integers.txt: " + amountIntsInFile);
        System.err.println("floats.txt: " + amountDoublesInFile);
        System.out.println("strings.txt: " + amountStringsInFile);
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
    }

    public void collectInts(List<Long> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        flagInts = true;

        minLong = Collections.min(list);
        maxLong = Collections.max(list);

        for (var elem : list) {
            sumLong += elem;
        }

        averageLong = (double) sumLong / list.size();
    }

    public void collectDoubles(List<Double> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        flagDoubles = true;

        minDouble = Collections.min(list);
        maxDouble = Collections.max(list);

        for (var elem : list) {
            sumDouble += elem;
        }

        averageDouble = sumDouble / list.size();
    }
}
