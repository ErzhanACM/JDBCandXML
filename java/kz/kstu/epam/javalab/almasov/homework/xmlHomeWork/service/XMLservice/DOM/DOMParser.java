package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.DOM;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Plane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    public Airport parseFile(File file) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        Element airportElement = (Element) document.getElementsByTagName("airport").item(0);

        NodeList planeNodeList = document.getElementsByTagName("plane");

        List<Plane> planeList = new ArrayList<>();

        for (int planeIndex = 0; planeIndex < planeNodeList.getLength(); planeIndex++) {

            if (planeNodeList.item(planeIndex).getNodeType() == Node.ELEMENT_NODE) {

                Element planeElement = (Element) planeNodeList.item(planeIndex);

                Plane plane = new Plane();
                plane.setNumber(Integer.valueOf(planeElement.getAttribute("number")));

                NodeList planeDescriptionNodeList = planeElement.getChildNodes();

                for (int planeDescriptionIndex = 0; planeDescriptionIndex < planeDescriptionNodeList.getLength(); planeDescriptionIndex++) {

                    if (planeDescriptionNodeList.item(planeDescriptionIndex).getNodeType() == Node.ELEMENT_NODE) {

                        Element planeDescriptionElement = (Element) planeDescriptionNodeList.item(planeDescriptionIndex);

                        switch (planeDescriptionElement.getNodeName()) {

                            case "model": {
                                plane.setModel(planeDescriptionElement.getTextContent());
                            }
                            break;

                            case "origin": {
                                plane.setOrigin(planeDescriptionElement.getTextContent());
                            }
                            break;

                            case "chars": {

                                plane.getChars().setType(planeDescriptionElement.getAttribute("type"));

                                NodeList charsNodeList = planeDescriptionElement.getChildNodes();

                                for (int charsIndex = 0; charsIndex < charsNodeList.getLength(); charsIndex++) {

                                    if (charsNodeList.item(charsIndex).getNodeType() == Node.ELEMENT_NODE) {

                                        Element charsElement = (Element) charsNodeList.item(charsIndex);

                                        switch (charsElement.getNodeName()) {

                                            case "crewSeatsNumber": {
                                                plane.getChars().setCrewSeatsNumber(Integer.valueOf(charsElement.getTextContent()));
                                            }
                                            break;

                                            case "carryingCapacity": {
                                                plane.getChars().setCarryingCapacity(Integer.valueOf(charsElement.getTextContent()));
                                            }

                                            case "passengersNumber": {
                                                plane.getChars().setPassengersNumber(Integer.valueOf(charsElement.getTextContent()));
                                            }

                                        }

                                    }

                                }

                            }
                            break;

                            case "parameters": {

                                NodeList parametersNodeList = planeDescriptionElement.getChildNodes();

                                for (int parametersIndex = 0; parametersIndex < parametersNodeList.getLength(); parametersIndex++) {

                                    if (parametersNodeList.item(parametersIndex).getNodeType() == Node.ELEMENT_NODE) {

                                        Element parametersElement = (Element) parametersNodeList.item(parametersIndex);

                                        switch (parametersElement.getNodeName()) {

                                            case "lenght": {
                                                plane.getParameters().setLenght(Double.valueOf(parametersElement.getTextContent()));
                                            }

                                            case "height": {
                                                plane.getParameters().setHeight(Double.valueOf(parametersElement.getTextContent()));
                                            }

                                            case "width": {
                                                plane.getParameters().setWidth(Double.valueOf(parametersElement.getTextContent()));
                                            }

                                        }

                                    }

                                }

                            }
                            break;

                            case "cost": {

                                plane.getCost().setCurrency(planeDescriptionElement.getAttribute("currency"));

                                plane.getCost().setPrice(Double.valueOf(planeDescriptionElement.getTextContent()));
                            }
                            break;

                        }

                    }

                }

                planeList.add(plane);
            }

        }

        Airport airport = new Airport(planeList);
        return airport;

    }
}
