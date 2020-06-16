package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReadFile {

    static String s;
    static StringTokenizer st ;
    public static void read() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/source-data.tsv"));
        s = bufferedReader.readLine();
        while (s != null){
            List<String> dataArray = new ArrayList<String>();
            String[] data = s.split("\t");
            for (String item:data) {
                System.out.print(item + "  ");
            }
            System.out.println(); // Print the data line.
            s = bufferedReader.readLine(); // Read next line of data.
        }

    }
}
