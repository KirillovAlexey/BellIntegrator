package com.readData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<String[]> getDataArray() {
        return dataArray;
    }

    private static final List<String[]> dataArray = new ArrayList<>();

    public ReadFile(String param) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream
                ("src\\main\\resources\\" + param), StandardCharsets.UTF_16));
        while (bufferedReader.ready()) {
            dataArray.add(bufferedReader.readLine().split("\t"));
        }
    }
 /*   public ReadFile() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream("src/main/resources/source-data.tsv"), StandardCharsets.UTF_16));

        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream
                //("src\\main\\resources\\source-data.tsv" + param), StandardCharsets.UTF_16));
        while (bufferedReader.ready()) {
            dataArray.add(bufferedReader.readLine().split("\t"));
        }
    }*/
}
