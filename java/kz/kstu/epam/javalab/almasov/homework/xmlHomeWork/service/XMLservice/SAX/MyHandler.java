package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.SAX;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Plane;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MyHandler extends DefaultHandler {

    private Airport airport;
    private Plane plane;
    private String currentElement;

    public Airport getAirport() {
        return airport;
    }

    public void startDocument() throws SAXException {
        // Document started
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentElement = qName;

        switch (currentElement) {

            case "airport": {
                airport = new Airport();
            }
            break;

            case "plane": {

                if (airport.getPlaneList() == null) {
                    airport.setPlaneList(new ArrayList<>());
                }

                plane = new Plane();
                plane.setNumber(Integer.valueOf(attributes.getValue("number")));
            }
            break;

            case "chars": {
                plane.getChars().setType(attributes.getValue("type"));
            }
            break;

            case "cost": {
                plane.getCost().setCurrency(attributes.getValue("currency"));
            }
            break;

            default: {
                // Nothing to do
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String text = new String(ch, start, length);

        if (text.contains("<") || currentElement == null) {
            return;
        }

        switch (currentElement) {

            case "model": {
                plane.setModel(text);
            }
            break;

            case "origin": {
                plane.setOrigin(text);
            }
            break;

            case "crewSeatsNumber": {
                plane.getChars().setCrewSeatsNumber(Integer.valueOf(text));
            }
            break;

            case "carryingCapacity": {
                plane.getChars().setCarryingCapacity(Integer.valueOf(text));
            }
            break;

            case "passengersNumber": {
                plane.getChars().setPassengersNumber(Integer.valueOf(text));
            }
            break;

            case "lenght": {
                plane.getParameters().setLenght(Double.valueOf(text));
            }
            break;

            case "height": {
                plane.getParameters().setHeight(Double.valueOf(text));
            }
            break;

            case "width": {
                plane.getParameters().setWidth(Double.valueOf(text));
            }
            break;

            case "cost": {
                plane.getCost().setPrice(Double.valueOf(text));
            }

            default: {
                // Nothing to do
            }

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (qName) {

            case "plane": {
                airport.getPlaneList().add(plane);
                plane = null;
            }
            break;

            default: {
                // Nothing to do
            }

        }

        currentElement = null;
    }

    @Override
    public void endDocument() throws SAXException {
        // end
    }

}
