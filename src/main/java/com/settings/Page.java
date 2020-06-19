package com.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Page {
    private final String width;
    private final String height;

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public Page(Document doc) {
        NodeList page = doc.getElementsByTagName("page");
        Node node = page.item(0);
        Element element = (Element) node;
        width = element.getElementsByTagName("width").item(0).getTextContent();
        height = element.getElementsByTagName("height").item(0).getTextContent();
    }
}
