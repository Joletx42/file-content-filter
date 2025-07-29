package com.app.processing;

import java.util.ArrayList;
import java.util.List;

import com.app.error.ErrorHandler;

public class DataTypeDetector {
    private List<String> stringList = new ArrayList<>();
    private List<Long> longList = new ArrayList<>();
    private List<Double> doubleList = new ArrayList<>();

    public void addArr(double number) {
        doubleList.add(number);
    }

    public void addArr(long number) {
        longList.add(number);
    }

    public void addArr(String str) {
        stringList.add(str);
    }

    public List<String> getStrList() {
        return stringList;
    }

    public List<Long> getLongList() {
        return longList;
    }

    public List<Double> getDoubleList() {
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
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
