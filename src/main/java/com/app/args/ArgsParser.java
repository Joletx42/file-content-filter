package com.app.args;

import com.app.processing.DataTypeDetector;
import com.app.args.Parameter;

import java.util.ArrayList;
import java.util.Arrays;

public class ArgsParser {
    private final String[] initialArgs;
    private ArrayList<String> fileList = new ArrayList<>();
    private ArrayList<String> paramList = new ArrayList<>();
    private String pathToFiles;
    private String prefix;
    private boolean recordMode;
    private char statisticsOption;

    public ArgsParser(String[] args) {
        this.initialArgs = Arrays.copyOf(args, args.length);
    }

    public void parse() {
        int i = 0;
        while (i != initialArgs.length) {
            String currentElem = initialArgs[i];

            if (isParameter(currentElem)) {
                Parameter param = Parameter.fromFlag(currentElem);
                if (param != null) {
                    switch (param) {
                        case PATH:
                            pathToFiles = initialArgs[i + 1];
                            i++;
                            break;
                        case PREFIX:
                            prefix = initialArgs[i + 1];
                            i++;
                            break;
                        case MODE:
                            recordMode = true;
                            break;
                        case SHORT:
                            statisticsOption = 's';
                            break;
                        case FULL:
                            statisticsOption = 'f';
                            break;
                    }
                } else {
                    System.out.println(currentElem + ": Неизвестный параметр");
                }

                paramList.add(currentElem);
            } else if (isFile(currentElem)) {
                fileList.add(currentElem);
            } else {
                System.out.println((currentElem + ": неизвестный тип аргумента"));
            }

            i++;
        }

    }

    public ArrayList<String> getFileList() {
        return fileList;
    }

    public ArrayList<String> getParamList() {
        return paramList;
    }

    public String getPathToFiles() {
        return pathToFiles;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean getRecordMode() {
        return recordMode;
    }

    public char getStatisticsOption() {
        return statisticsOption;
    }

    private boolean isParameter(String s) {
        return s != null && s.matches("-[a-zA-Z]");
    }

    private boolean isFile(String s) {
        return s.endsWith(".txt");
    }
}
