package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.runner;

import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.dao.impl.PlaneDAO;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.dao.ConnectionToDB;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.DOM.DOMParser;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.SAX.SAXParser;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.StAX.StAXParaser;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.Validator.XMLValidator;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.XMLTransformer.XMLTransformer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

/*
1. Создать файл XML и соответствующую ему схему XSD.
2. При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и предельные значения.
3. Сгенерировать класс, соответствующий данному описанию.
4. Создать приложение для разбора XML-документа и инициализации коллекции объектов информацией из XML-файла. Для разбора использовать
SAX, DOM и StAX парсеры. Для сортировки объектов использовать интерфейс Comparator.
5. Произвести проверку XML-документа с привлечением XSD.
6. Определить метод, производящий преобразование разработанного XMLдокумента в документ, указанный в каждом задании.

 Самолеты.
Самолеты можно описать по следующей схеме:
— Model — название модели;
— Origin — страна производства;
— Chars (должно быть несколько) — характеристики, могут быть следующими: тип (пассажирский, грузовой, почтовый, пожарный, сельскохозяйственный), количество мест для экипажа, характеристики (грузоподъемность, число пассажиров), наличие радара;
— Parameters — длина (в метрах), ширина (в метрах), высота (в метрах);
— Price — цена (в талерах).
Корневой элемент назвать Plane.
С помощью XSL преобразовать XML-файл в формат HTML, при выводе
отсортировать по стоимости.
*/

public class AppXML {

    final static String XML_PATH = "D:\\Erzhan\\Learning\\Projects\\Java\\maven-projects\\XMLparsing\\src\\main\\java\\kz\\kstu\\epam\\javalab\\almasov\\homework\\xmlHomeWork\\xml\\file.xml";
    final static String XSD_PATH = "D:\\Erzhan\\Learning\\Projects\\Java\\maven-projects\\XMLparsing\\src\\main\\java\\kz\\kstu\\epam\\javalab\\almasov\\homework\\xmlHomeWork\\xml\\schemaOfAirport.xml";
    final static String XSL_PATH = "D:\\Erzhan\\Learning\\Projects\\Java\\maven-projects\\XMLparsing\\src\\main\\java\\kz\\kstu\\epam\\javalab\\almasov\\homework\\xmlHomeWork\\xml\\style.xsl";
    final static String HTML_PATH = "D:\\Erzhan\\Learning\\Projects\\Java\\maven-projects\\XMLparsing\\src\\main\\java\\kz\\kstu\\epam\\javalab\\almasov\\homework\\xmlHomeWork\\xml\\airport.html";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, SQLException, XMLStreamException {

        XMLTransformer xmlTransformer = new XMLTransformer();
        xmlTransformer.transform(XSL_PATH, XML_PATH, HTML_PATH);

        XMLValidator xmlValidator = new XMLValidator();
        xmlValidator.validate(XML_PATH, XSD_PATH);

        System.out.println("\nxml DOM parsing");
        DOMParser domParser = new DOMParser();
        Airport domAirport = domParser.parseFile(new File(XML_PATH));
        domAirport.getPlaneList().forEach(System.out::println);
        Collections.sort(domAirport.getPlaneList());
        System.out.println("Sorted DOMairport");
        domAirport.getPlaneList().forEach(System.out::println);

        System.out.println("\nxml SAX parsing");
        SAXParser saxParser = new kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.SAX.SAXParser();
        Airport saxAirport = saxParser.parseFile(XML_PATH);
        saxAirport.getPlaneList().forEach(System.out::println);
        Collections.sort(saxAirport.getPlaneList());
        System.out.println("Sorted SAXairport");
        saxAirport.getPlaneList().forEach(System.out::println);

        System.out.println("\nxml StAX parsing");
        StAXParaser staxParaser = new StAXParaser();
        Airport staxAirport = staxParaser.parseFile(XML_PATH);
        staxAirport.getPlaneList().forEach(System.out::println);
        Collections.sort(staxAirport.getPlaneList());
        System.out.println("Sorted StAXairport");
        staxAirport.getPlaneList().forEach(System.out::println);

        System.out.println(domAirport.equals(saxAirport) + ", " + domAirport.equals(staxAirport) + ", " + saxAirport.equals(staxAirport));
        System.out.println(domAirport.hashCode() + ", " + saxAirport.hashCode() + ", " + staxAirport.hashCode());


        try (Connection connection = ConnectionToDB.getConnection()) {

            PlaneDAO planeDAO = new PlaneDAO(connection);

            planeDAO.dropTable(connection);
            planeDAO.createTable(connection);

            for (int i = 0; i < domAirport.getPlaneList().size(); i++) {
                planeDAO.add(domAirport.getPlaneList().get(i));
                planeDAO.add(saxAirport.getPlaneList().get(i));
                planeDAO.add(staxAirport.getPlaneList().get(i));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
