package fileOperations;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {
    public void readXML() throws SAXException, ParserConfigurationException, IOException{
        File xmlFile = new File ("src/resources/Hospital.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList list = document.getElementsByTagName("habitacion");
        for (int i = 0; i < list.getLength(); i++) {
            Node habitacion = list.item(i);
        }
    }
}
