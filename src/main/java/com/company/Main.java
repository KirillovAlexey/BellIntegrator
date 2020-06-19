package com.company;

import com.readData.ReadFile;
import com.readData.XmlParse;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        XmlParse xmlParse = new XmlParse();//читаем файл с настройками таблицы
        ReadFile readFile = new ReadFile();//читаем файл с исходными данными
        readFile.read();
        GenerateReport generateReport = new GenerateReport(readFile, xmlParse);
        generateReport.generate();
    }
}

//java Generator.class settings.xml source-data.tsv example-report.txt