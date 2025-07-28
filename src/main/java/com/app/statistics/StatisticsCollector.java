package com.app.statistics;

import java.util.Collections;
import java.util.List;

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

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
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
        System.out.println("\n" + ANSI_GREEN + "КОЛИЧЕСТВО ЗАПИСАННЫХ ЭЛЕМЕНТОВ В ФАЙЛЫ" + ANSI_RESET);
        System.out.println(ANSI_GREEN + LINE + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "integers.txt: " + amountIntsInFile + ANSI_RESET);
        System.err.println(ANSI_PURPLE + "floats.txt: " + amountDoublesInFile + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "strings.txt: " + amountStringsInFile + ANSI_RESET);
    }

    public void printFullSummary() {
        printShortSummary();

        if (flagInts) {
            System.out.println("\n" + ANSI_GREEN + "СТАТИСТИКА ЗАПИСАННЫХ ЭЛЕМЕНТОВ В integers.txt" + ANSI_RESET);
            System.out.println(ANSI_GREEN + LINE + ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE + "Минимальное значение integers: " + minLong + ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE + "Максимальное значение integers: " + maxLong + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "Сумма integers: " + sumLong + ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE + "Среднее значение integers: " + averageLong + ANSI_RESET);
        }

        if (flagDoubles) {
            System.out.println("\n" + ANSI_GREEN + "СТАТИСТИКА ЗАПИСАННЫХ ЭЛЕМЕНТОВ В floats.txt" + ANSI_RESET);
            System.out.println(ANSI_GREEN + LINE + ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE + "Минимальное значение floats: " + minDouble + ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE + "Максимальное значение floats: " + maxDouble + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "Сумма floats: " + sumDouble + ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE + "Среднее значение floats: " + averageDouble + ANSI_RESET);
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
