package com.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class Columns {

    private final HashMap<String, String> listColumn = new HashMap<>();

    public HashMap<String, String> getColumns() {
        return listColumn;
    }

    public Columns(Document doc) {
        NodeList data = doc.getElementsByTagName("column");
        for (int i = 0; i < data.getLength(); i++) {
            Node node = data.item(i);
            Element element = (Element) node;
            listColumn.put(element.getElementsByTagName("title").item(0).getTextContent(),
                    element.getElementsByTagName("width").item(0).getTextContent());
        }
    }
}
