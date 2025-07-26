package com.app.processing;

import java.util.ArrayList;

import com.app.args.ArgsParser;
import com.app.args.Parameter;

public class FileProcessing {
    private ArgsParser parser;

    public FileProcessing(ArgsParser parser) {
        this.parser = parser;
    }

    public void processFiles() {
        ArrayList<String> files = new ArrayList<>();
        files = parser.getFileList();
        int i = 0;

        while (i != files.size()) {
            String currentElem = files.get(i);
            System.out.println(currentElem);
            i++;
        }
    }

}
