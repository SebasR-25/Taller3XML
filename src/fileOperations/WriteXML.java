package fileOperations;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class WriteXML {
    private Document document;

    public void writeXML(int floor, int roomN) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();

        document = implementation.createDocument(null, "Hospital", null);
        document.setXmlVersion("1.0");

        Element rooms = document.createElement("rooms");
        Element room = document.createElement("room");

        Element floorNumber = document.createElement("piso");
        Text floorText  = document.createTextNode(floor+"");
        floorNumber.appendChild(floorText);
        room.appendChild(floorNumber);

        Element roomNumber = document.createElement("numero de habitacion");
        Text roomText  = document.createTextNode(roomN+"");
        floorNumber.appendChild(roomText);
        room.appendChild(roomNumber);

        Element patient = document.createElement("paciente");

        Element firstName = document.createElement("nombre");
        Text firstNameText  = document.createTextNode("afcasdas");
        firstName.appendChild(firstNameText);
        patient.appendChild(firstName);

        Element lastName = document.createElement("apellido");
        Text lastNameText  = document.createTextNode("fasdfasd");
        lastName.appendChild(lastNameText);
       patient.appendChild(lastName);

        Element contactPhoneNumber = document.createElement("nuimero de contacto");
        Text contactPhoneNumberText  = document.createTextNode("adasdasdasd");
        contactPhoneNumber.appendChild(contactPhoneNumberText);
        patient.appendChild(contactPhoneNumber);
        
        rooms.appendChild(room);

        document.getDocumentElement().appendChild(rooms);

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("src/resources/Hospital.xml"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }
}
