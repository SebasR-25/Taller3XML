package presenter;

import model.Patient;
import model.Room;
import model.RoomManager;
import model.Status;
import view.MainFrame;
import fileOperations.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public class Presenter implements ActionListener {
    private MainFrame view;
    private RoomManager roomManager;
    private ReadXML readder;
    private WriteXML writter;

    public Presenter() {
        view = new MainFrame(this);
        roomManager = new RoomManager();
        readder = new ReadXML();
        writter = new WriteXML();
    }

    private void start() throws SAXException, ParserConfigurationException, IOException {
        readder.readXML();
        roomManager.setRooms(readder.getReadRooms());
        view.start();
    }

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        new Presenter().start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "CREATE_PATIENT":
                addPatient(view.getRoomNumberToPatient(), view.getPatientName(), view.getPatientLastName(), view.getContactPhoneNumber());
                view.showSuccessMessage("Paciente creado con exito");
                view.clearPatientFields();
                break;
            case "CREATE_ROOM":
                addRoom(Integer.parseInt(view.getNewRoomId()), Integer.parseInt(view.getNewRoomFloor()), Integer.parseInt(view.getNewRoomNumber()), Integer.parseInt(view.getNewRoomBedNumber()));
                view.showSuccessMessage("Habitacion creada con exito");
                view.clearRoomFields();
                break;
            case "SEARCH_ROOM":
                Room room = searchRoom(Integer.parseInt(view.getRoomIdToSearch()));
                if (room != null) {
                    view.loadRoomToHistoryPanel(List.of(String.valueOf(room.getId()), String.valueOf(room.getRoomNumber()), String.valueOf(room.getFloorNumber()), String.valueOf(room.getBedNumber())), getPatientData(room));
                }
                break;
            case "GENERATE_XML":
                view.showErrorMessage("No implementado");
                break;
                case  "EXIT":
                try {
                    writter.writeXML(roomManager.getRooms());
                } catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
                    view.showErrorMessage("Se generó un error al escribir el archivo");
                }
                view.showSuccessMessage("Se cerró exitosamente el programa");
                System.exit(0);
                break;
        }
    }

    private void addRoom(int id, int floor, int number, int bedNumber) {
        roomManager.addRoom(new Room(bedNumber, floor, id, new ArrayList<>(), number));
    }

    private List<String> getPatientData(Room room) {
        List<String> patientData = new ArrayList<>();
        for (Patient patient : room.getPatients()) {
            patientData.add(patient.getFirstName());
            patientData.add(patient.getLastName());
            patientData.add(patient.getContactPhoneNumber());
            patientData.add(String.valueOf(patient.getStatus()));
        }
        return patientData;
    }

    private Room searchRoom(int idRoom) {
        if (idRoom < 0) {
            view.showErrorMessage("El id de la habitación debe ser mayor que 0");
        } else if (roomManager.searchRoomById(idRoom) == null) {
            view.showErrorMessage("La habitación no existe");
        } else {
            view.showSuccessMessage("La habitación ha sido encontrada");
            return roomManager.searchRoomById(idRoom);
        }
        return null;
    }

    private Room searchRoomByNumber(int roomNumber) {
        if (roomNumber < 0) {
            view.showErrorMessage("El número de la habitación debe ser mayor que 0");
        } else if (roomManager.searchRoomByNumber(roomNumber) == null) {
            view.showErrorMessage("La habitación no existe");
        } else {
            view.showSuccessMessage("La habitación ha sido encontrada");
            return roomManager.searchRoomByNumber(roomNumber);
        }
        return null;
    }

    private void addPatient(int roomNumber, String firstName, String lastName, String contactPhoneNumber) {
        Patient tempPatient = new Patient(contactPhoneNumber, firstName, lastName, Status.ACTIVE);
        Room tempRoom = searchRoomByNumber(roomNumber);
        roomManager.addPatient(tempRoom, tempPatient);
    }
}
