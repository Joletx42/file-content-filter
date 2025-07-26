package com.app.processing;

import java.util.ArrayList;

public class DataTypeDetector {
    private ArrayList<String> stringList = new ArrayList<>();
    private ArrayList<Integer> integerList = new ArrayList<>();
    private ArrayList<Double> doubleList = new ArrayList<>();

    public void addArr(double number) {
        doubleList.add(number);
    }

    public void addArr(int number) {
        integerList.add(number);
    }

    public void addArr(String str) {
        stringList.add(str);
    }

    public ArrayList<String> getStrArray() {
        return stringList;
    }

    public ArrayList<Integer> getIntArray() {
        return integerList;
    }

    public ArrayList<Double> getDoubleArray() {
        return doubleList;
    }

    public void clearAll() {
        stringList.clear();
        integerList.clear();
        doubleList.clear();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
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
