package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.service.XMLservice.XMLTransformer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XMLTransformer {

    public void transform(String XSLpath, String XMLpath, String HTMLpath) {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(XSLpath)));
            transformer.transform(new StreamSource(new File(XMLpath)), new StreamResult(new File(HTMLpath)));
            System.out.println("Transform is complete");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
