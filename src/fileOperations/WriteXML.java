package fileOperations;

import java.io.File;
import java.util.List;

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
import model.Patient;
import model.Room;

public class WriteXML {
    private Document document;

    public void writeXML(List<Room> habitaciones)
            throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();

        document = implementation.createDocument(null, "Hospital", null);
        document.setXmlVersion("1.0");

        Element rooms = document.createElement("rooms");
        for (int i = 0; i < habitaciones.size(); i++) {
            Room tempRoom = habitaciones.get(i);
            Element room = document.createElement("room");

            Element floorNumber = document.createElement("piso");
            Text floorText = document.createTextNode(tempRoom.getFloorNumber() + "");
            floorNumber.appendChild(floorText);
            room.appendChild(floorNumber);

            Element roomNumber = document.createElement("numero de habitacion");
            Text roomText = document.createTextNode(tempRoom.getRoomNumber() + "");
            floorNumber.appendChild(roomText);
            room.appendChild(roomNumber);

            for (int a = 0; a < tempRoom.getPatients().size(); a++) {
                List<Patient> tempPatientList =tempRoom.getPatients();
                Element patient = document.createElement("paciente");

                Element firstName = document.createElement("nombre");
                Text firstNameText = document.createTextNode(tempPatientList.get(i).getFirstName());
                firstName.appendChild(firstNameText);
                patient.appendChild(firstName);

                Element lastName = document.createElement("apellido");
                Text lastNameText = document.createTextNode(tempPatientList.get(i).getLastName());
                lastName.appendChild(lastNameText);
                patient.appendChild(lastName);

                Element contactPhoneNumber = document.createElement("nuimero de contacto");
                Text contactPhoneNumberText = document.createTextNode(tempPatientList.get(i).getContactPhoneNumber());
                contactPhoneNumber.appendChild(contactPhoneNumberText);
                patient.appendChild(contactPhoneNumber);
            }
            rooms.appendChild(room);
        }

        document.getDocumentElement().appendChild(rooms);

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("src/resources/Hospital.xml"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }
}