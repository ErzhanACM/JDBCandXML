package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.Validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    private static final String MESSAGE = "xml соответствует XSD : ";
    private static final String XML_NOT_FOUND = "xml не найден";
    private static final String XSD_NOT_FOUND = "XSD не найден";

    public void validate(String XMLpath, String XSDpath) {
        boolean b = checkFileForSchema(XMLpath, XSDpath);
        System.out.println(MESSAGE + b);
    }

    private boolean checkFileForSchema(String XMLpath, String XSDpath) {

        try {
            File xml = new File(XMLpath);
            File xsd = new File(XSDpath);

            if (!xml.exists()) {
                System.out.println(XML_NOT_FOUND + XMLpath);
                return false;
            }

            if (!xsd.exists()) {
                System.out.println(XSD_NOT_FOUND + XSDpath);
                return false;
            }

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(XSDpath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(XMLpath));
            return true;

        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
