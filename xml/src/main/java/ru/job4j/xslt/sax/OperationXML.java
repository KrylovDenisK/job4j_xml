package ru.job4j.xslt.sax;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Counting input xml elements
 */
public class OperationXML extends DefaultHandler  {
    private int result = 0;

    public int getResult() {
        return result;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("entry")) {
            result += Integer.parseInt(attributes.getValue("href"));
        }
    }

    public void parse(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(path, this);
    }
}