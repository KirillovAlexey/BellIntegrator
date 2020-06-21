package com.company;

import com.readData.ReadFile;
import com.readData.XmlParse;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //new XmlParse(args[0]);//читаем файл с настройками таблицы
        new XmlParse(args[0]);
        ReadFile readFile = new ReadFile(args[1]);//читаем файл с исходными данными
        //readFile.read(args[1]);
        GenerateReport generateReport = new GenerateReport(readFile);
        generateReport.generate(args[2]);
        //generateReport.generate(args[2]);
    }
}

//java Generator.class settings.xml source-data.tsv example-report.txt