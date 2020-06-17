package com.company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        XmlParse xmlParse = new XmlParse();
        ReadFile readFile = new ReadFile();
        readFile.read();
        //ReadFile.read();
        GenerateReport generateReport = new GenerateReport(readFile,xmlParse);
    }
}
