package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.StAX;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Plane;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.util.ParserUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StAXParaser {

    Airport airport;
    Plane plane;

    public Airport parseFile(String filePath) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = new FileInputStream(filePath);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            process(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return airport;
    }

    private void process(XMLStreamReader reader) throws XMLStreamException {

        while (reader.hasNext()) {

            int type = reader.next();

            switchType(reader, type);
        }

    }

    private void switchType(XMLStreamReader reader, int type){

        String name = "";

        switch (type) {

            case XMLStreamConstants.START_ELEMENT: {
                name = reader.getLocalName();

                switchLocalName(reader, name);
            }
            break;

            case XMLStreamConstants.END_ELEMENT: {
                if (reader.getLocalName() == "plane") {
                    fillAirport();
                }
            }
            break;

            case XMLStreamConstants.CHARACTERS: {
                getCharacters(reader, name);
            }
            break;
        }
    }

    private void switchLocalName(XMLStreamReader reader, String name){

        switch (name) {

            case "airport": {
                airport = new Airport();
                if (airport.getPlaneList() == null) {
                    airport.setPlaneList(new ArrayList<>());
                }
            }
            break;

            case "plane": {
                plane = new Plane();
                getAttribute(reader);
            }
            break;

            case "chars": {
                getAttribute(reader);
            }
            break;

            case "cost": {
                getAttribute(reader);
            }
            break;
        }
    }

    private void getAttribute(XMLStreamReader reader) {
        String attribute = reader.getAttributeLocalName(0);
        if (attribute != null) {

            switch (attribute) {

                case "number": {
                    plane.setNumber(Integer.valueOf(reader.getAttributeValue(null, "number")));
                }
                break;

                case "type": {
                    plane.getChars().setType(reader.getAttributeValue(null, "type"));
                }
                break;

                case "currency": {
                    plane.getCost().setCurrency(reader.getAttributeValue(null, "currency"));
                }
                break;

            }

        }
    }

    private void getCharacters(XMLStreamReader reader, String name) {
        if (!reader.getText().trim().equals("")) {
            ParserUtil.switchElement(plane, name, reader.getText());
        }
    }

    private void fillAirport() {
        airport.getPlaneList().add(plane);
    }

}
