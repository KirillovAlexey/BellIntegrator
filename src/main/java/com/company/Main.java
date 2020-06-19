package com.company;

import com.readData.ReadFile;
import com.readData.XmlParse;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        XmlParse xmlParse = new XmlParse();
        ReadFile readFile = new ReadFile();
        GenerateReport generateReport = new GenerateReport(readFile,xmlParse);
        readFile.read();
        //ReadFile.read();
        generateReport.generate();
    }
}
