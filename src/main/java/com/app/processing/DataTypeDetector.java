package com.app.processing;

import java.util.ArrayList;

public class DataTypeDetector {
    private ArrayList<String> stringList = new ArrayList<>();
    private ArrayList<Long> longList = new ArrayList<>();
    private ArrayList<Double> doubleList = new ArrayList<>();

    public void addArr(double number) {
        doubleList.add(number);
    }

    public void addArr(long number) {
        longList.add(number);
    }

    public void addArr(String str) {
        stringList.add(str);
    }

    public ArrayList<String> getStrList() {
        return stringList;
    }

    public ArrayList<Long> getLongList() {
        return longList;
    }

    public ArrayList<Double> getDoubleList() {
        return doubleList;
    }

    public void clearAll() {
        stringList.clear();
        longList.clear();
        doubleList.clear();
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
