package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.util;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Plane;

public class ParserUtil {

    public static void switchElement(Plane plane, String element, String text){

        switch (element) {
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

}
