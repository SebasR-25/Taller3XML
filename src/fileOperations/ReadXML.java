package fileOperations;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import model.Patient;
import model.Room;

public class ReadXML {
    public List<Room> readRooms;
    
    public void readXML() throws SAXException, ParserConfigurationException, IOException{
        File xmlFile = new File ("src/resources/Hospital.xml");
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
                
                NodeList patientList = document.getElementsByTagName("patient");
                for(int j = 0; i < patientList.getLength(); j++){
                    Patient tempPatient = new Patient();
                    Node patient = patientList.item(j);
                    if(patient.getNodeType() == Node.ELEMENT_NODE){
                        Element element2 = (Element) patient;
                        tempPatient.setFirstName(element2.getElementsByTagName("firstName").item(0).getTextContent());
                        tempPatient.setLastName(element2.getElementsByTagName("lastName").item(0).getTextContent());
                        tempPatient.setContactPhoneNumber(element2.getElementsByTagName("contactPhoneNumber").item(0).getTextContent());
                    }
                    tempRoom.addPatient(tempPatient);
                }
            }
        }
    } 
}
