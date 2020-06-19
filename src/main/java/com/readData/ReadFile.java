package com.readData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<String[]> getDataArray() {
        return dataArray;
    }

    public static List<String[]> dataArray = new ArrayList();

    public void read() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/source-data.tsv"), "utf16"));
        while (bufferedReader.ready()) {
            dataArray.add(bufferedReader.readLine().split("\t"));
        }

    }
}
