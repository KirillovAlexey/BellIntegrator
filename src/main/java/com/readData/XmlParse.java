package com.readData;

import com.settings.Columns;
import com.settings.Page;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParse {
    Columns column;
    Page page;

    public Columns getColumn() {
        return column;
    }

    public Page getPage() {
        return page;
    }

    public XmlParse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/main/resources/settings.xml"));
        page = new Page(doc);
        column = new Columns(doc);
    }
}
