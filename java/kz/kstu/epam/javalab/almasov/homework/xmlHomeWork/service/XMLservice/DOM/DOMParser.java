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
        List<Plane> planeList = new ArrayList<>();

        NodeList planeNodeList = document.getElementsByTagName("plane");

        for (int planeIndex = 0; planeIndex < planeNodeList.getLength(); planeIndex++) {

            if (isElementNode(planeNodeList, planeIndex)) {

                Element planeElement = (Element) planeNodeList.item(planeIndex);

                Plane plane = new Plane();

                plane.setNumber(Integer.valueOf(planeElement.getAttribute("number")));

                considerChildNode(plane, planeElement);

                planeList.add(plane);
            }
        }
        Airport airport = new Airport(planeList);
        return airport;
    }

    private void considerChildNode(Plane plane, Element element) {

        NodeList nodeList = element.getChildNodes();

        for (int index = 0; index < nodeList.getLength(); index++) {

            if (isElementNode(nodeList, index)) {

                Element childElement = (Element) nodeList.item(index);
                switchNodeName(plane, childElement);
            }
        }
    }

    private void switchNodeName(Plane plane, Element element) {
        switch (element.getNodeName()) {
            case "model": {
                plane.setModel(element.getTextContent());
            }
            break;

            case "origin": {
                plane.setOrigin(element.getTextContent());
            }
            break;

            case "chars": {
                plane.getChars().setType(element.getAttribute("type"));
                considerChildNode(plane, element);
            }
            break;

            case "parameters": {
                considerChildNode(plane, element);
            }
            break;

            case "cost": {
                plane.getCost().setCurrency(element.getAttribute("currency"));
                plane.getCost().setPrice(Double.valueOf(element.getTextContent()));
            }
            break;

            case "crewSeatsNumber": {
                plane.getChars().setCrewSeatsNumber(getIntTextContent(element));
            }
            break;

            case "carryingCapacity": {
                plane.getChars().setCarryingCapacity(getIntTextContent(element));
            }
            break;

            case "passengersNumber": {
                plane.getChars().setPassengersNumber(getIntTextContent(element));
            }
            break;

            case "lenght": {
                plane.getParameters().setLenght(getDoubleTextContent(element));
            }
            break;

            case "height": {
                plane.getParameters().setHeight(getDoubleTextContent(element));
            }
            break;

            case "width": {
                plane.getParameters().setWidth(getDoubleTextContent(element));
            }
            break;
        }
    }

    private Integer getIntTextContent(Element element) {
        return Integer.valueOf(element.getTextContent());
    }

    private Double getDoubleTextContent(Element element) {
        return Double.valueOf(element.getTextContent());
    }

    private boolean isElementNode(NodeList nodeList, int index) {
        return nodeList.item(index).getNodeType() == Node.ELEMENT_NODE;
    }
}
