package fileOperations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Status;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import model.Patient;
import model.Room;

public class ReadXML {
    public List<Room> readRooms;

    public ReadXML() {
        readRooms = new ArrayList<>();
    }

    public List<Room> getReadRooms() {
        return readRooms;
    }

    public void readXML() throws SAXException, ParserConfigurationException, IOException {
        File xmlFile = new File("src/resources/Hospital.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList list = document.getElementsByTagName("room");
        for (int i = 0; i < list.getLength(); i++) {
            Room tempRoom = new Room();
            Node room = list.item(i);
            if (room.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) room;
                tempRoom.setId(Integer.parseInt(element.getAttribute("id")));
                tempRoom.setFloorNumber(Integer.parseInt(element.getElementsByTagName("floorNumber").item(0).getTextContent()));
                tempRoom.setRoomNumber(Integer.parseInt(element.getElementsByTagName("roomNumber").item(0).getTextContent()));

                NodeList patientList = element.getElementsByTagName("patient");
                for (int j = 0; j < patientList.getLength(); j++) {
                    Patient tempPatient = new Patient();
                    Node patient = patientList.item(j);
                    if (patient != null && patient.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) patient;
                        tempPatient.setFirstName(element2.getElementsByTagName("firstName").item(0).getTextContent());
                        tempPatient.setLastName(element2.getElementsByTagName("lastName").item(0).getTextContent());
                        tempPatient.setContactPhoneNumber(element2.getElementsByTagName("contactPhoneNumber").item(0).getTextContent());
                    }
                    tempRoom.addPatient(tempPatient);
                }
                setBedNumber(tempRoom);
            }
            readRooms.add(tempRoom);
        }
    }

    private void setBedNumber(Room tempRoom) {
        List<Patient> tempPatientList = tempRoom.getPatients();
        if (tempPatientList.size() <=5) {
            tempRoom.setBedNumber(5);
        } else{
            tempRoom.setBedNumber(5);
            for (int i = 5; i < tempPatientList.size(); i++) {
                tempPatientList.get(i).setStatus(Status.INACTIVE);
            }
        }
    }
}
