package com.app.args;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.app.error.ErrorHandler;

public class ArgsParser {
    private final String[] initialArgs;
    private List<String> fileList = new ArrayList<>();
    private List<String> paramList = new ArrayList<>();
    private String pathToFiles;
    private String prefix;
    private boolean recordMode = false;
    private char statisticsOption;
    private boolean helpOption = false;

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
                        case PREFIX:
                            if (i + 1 >= initialArgs.length) {
                                ErrorHandler.noValueForParam(currentElem);
                            } else {
                                if (param == Parameter.PATH) {
                                    pathToFiles = initialArgs[i + 1];
                                } else if (param == Parameter.PREFIX) {
                                    prefix = initialArgs[i + 1];
                                }
                                i++;
                            }
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
                        case HELP:
                            helpOption = true;
                            break;
                    }
                } else {
                    ErrorHandler.unknownParam(currentElem);
                }

                paramList.add(currentElem);
            } else if (isFile(currentElem)) {
                fileList.add(currentElem);
            } else {
                ErrorHandler.unknownArgumentType(currentElem);
            }

            i++;
        }

    }

    public List<String> getFileList() {
        return fileList;
    }

    public List<String> getParamList() {
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

    public boolean getHelpOption() {
        return helpOption;
    }

    private boolean isParameter(String s) {
        return s != null && s.matches("-[a-zA-Z]");
    }

    private boolean isFile(String s) {
        return s.endsWith(".txt");
    }
}
