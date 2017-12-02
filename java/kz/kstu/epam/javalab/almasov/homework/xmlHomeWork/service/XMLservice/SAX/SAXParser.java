package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.SAX;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParser {

    public Airport parseFile(String file) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        MyHandler handler = new MyHandler();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(file);

        Airport airport = handler.getAirport();

        return airport;

    }

}
