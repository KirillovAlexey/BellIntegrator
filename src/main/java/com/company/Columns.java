package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class Columns extends Page {
    public HashMap<String, String> getColumns() {
        return columns;
    }

    private HashMap<String, String> columns = new HashMap<>();

    public Columns(Document doc) {
        super(doc);
        NodeList column = doc.getElementsByTagName("column");
        for (int i = 0; i < column.getLength(); i++) {
            Node node = column.item(i);
            Element element = (Element) node;
            columns.put(element.getElementsByTagName("title").item(0).getTextContent(), element.getElementsByTagName("width").item(0).getTextContent());
        }
    }
}
