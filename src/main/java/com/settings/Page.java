package com.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Page {

    public Page(Document doc) {
        NodeList page = doc.getElementsByTagName("page");
        Node node = page.item(0);
        Element element = (Element) node;
        String width = element.getElementsByTagName("width").item(0).getTextContent();
        String height = element.getElementsByTagName("height").item(0).getTextContent();
    }
}
