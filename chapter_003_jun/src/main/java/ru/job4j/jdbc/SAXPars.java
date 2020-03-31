package ru.job4j.jdbc;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXPars extends DefaultHandler {
    private long sum;

    public long getSum() {
        return this.sum;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        String field = atts.getValue("field");
        this.sum = (field != null) ? this.sum + Integer.parseInt(field) : this.sum;
    }

    public void parse(File source) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(source, this);
    }

} 