package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.StAX;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Plane;
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

        String name = "";


        while (reader.hasNext()) {

            int type = reader.next();

            switch (type) {

                case XMLStreamConstants.START_ELEMENT: {
                    name = reader.getLocalName();

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
                break;

                case XMLStreamConstants.END_ELEMENT: {
                    if (reader.getLocalName() == "plane") {
                        fillAirport();
                    }
                }
                break;

                case XMLStreamConstants.CHARACTERS: {
                    getCharacters(name, reader);
                }
                break;

            }

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

    private void getCharacters(String name, XMLStreamReader reader) {
        if (!reader.getText().trim().equals("")) {

            switch (name) {

                case "model": {
                    plane.setModel(reader.getText());
                }
                break;

                case "origin": {
                    plane.setOrigin(reader.getText());
                }
                break;

                case "crewSeatsNumber": {
                    plane.getChars().setCrewSeatsNumber(Integer.valueOf(reader.getText()));
                }
                break;

                case "carryingCapacity": {
                    plane.getChars().setCarryingCapacity(Integer.valueOf(reader.getText()));
                }
                break;

                case "passengersNumber": {
                    plane.getChars().setPassengersNumber(Integer.valueOf(reader.getText()));
                }
                break;

                case "lenght": {
                    plane.getParameters().setLenght(Double.valueOf(reader.getText()));
                }
                break;

                case "height": {
                    plane.getParameters().setHeight(Double.valueOf(reader.getText()));
                }
                break;

                case "width": {
                    plane.getParameters().setWidth(Double.valueOf(reader.getText()));
                }
                break;

                case "cost": {
                    plane.getCost().setPrice(Double.valueOf(reader.getText()));
                }
                break;

            }

        }
    }

    private void fillAirport() {
        airport.getPlaneList().add(plane);
    }

}
