package com.company;

import java.io.*;

public class GenerateReport {
    ReadFile readFile;
    XmlParse xmlParse;
    OutputStreamWriter outputStreamWriter;
    public GenerateReport(){

    }

    public GenerateReport(ReadFile read, XmlParse xmlParse){
        this.readFile = read;
        this.xmlParse = xmlParse;
    }

    public void generate() throws FileNotFoundException, UnsupportedEncodingException {
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream(""),"utf16");
        while ()
    }
}
