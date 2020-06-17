package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<String[]> dataArray = new ArrayList();

    public void read() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/source-data.tsv"), "utf16"));
        while (bufferedReader.ready()) {
            dataArray.add(bufferedReader.readLine().split("\t"));
        }
        GenerateReport generateReport = new GenerateReport();
        for (String[] item : dataArray) {
            System.out.print(item[0] + "  ");
            System.out.print(item[1] + "  ");
            System.out.print(item[2] + "  \n");
        }

    }
}
