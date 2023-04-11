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
        loadDefaultRooms();
    }

    private void loadDefaultRooms() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("123456789", "Juan", "Perez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Maria", "Gomez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Pedro", "Gonzalez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Luis", "Rodriguez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Jose", "Martinez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Ana", "Lopez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Laura", "Gutierrez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Sofia", "Ramirez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Camila", "Sanchez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Valentina", "Torres", Status.ACTIVE));
        patients.add(new Patient("123456789", "Isabella", "Diaz", Status.ACTIVE));
        patients.add(new Patient("987654321", "Martina", "Perez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Antonia", "Gomez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Fernanda", "Gonzalez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Lucia", "Torres", Status.ACTIVE));
        patients.add(new Patient("987654321", "Daniela", "Gutierrez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Mariana", "Lopez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Julieta", "Martinez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Valeria", "Sanchez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Sara", "Ramirez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Mia", "Rodriguez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Victoria", "Diaz", Status.ACTIVE));
        patients.add(new Patient("123456789", "Nicole", "Perez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Renata", "Gomez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Constanza", "Gonzalez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Jimena", "Torres", Status.ACTIVE));
        patients.add(new Patient("123456789", "Catalina", "Gutierrez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Paula", "Lopez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Isidora", "Martinez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Emilia", "Sanchez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Florencia", "Ramirez", Status.ACTIVE));
        patients.add(new Patient("987654321", "Antonella", "Rodriguez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Javiera", "Diaz", Status.ACTIVE));
        patients.add(new Patient("987654321", "Micaela", "Perez", Status.ACTIVE));
        patients.add(new Patient("123456789", "Agustina", "Gomez", Status.ACTIVE));
        ;
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(5, 1, 1, List.of(patients.get(0), patients.get(1)), 101));
        rooms.add(new Room(5, 1, 2, List.of(patients.get(2), patients.get(3)), 102));
        rooms.add(new Room(5, 1, 3, List.of(patients.get(4), patients.get(5)), 103));
        rooms.add(new Room(5, 1, 4, List.of(patients.get(6), patients.get(7)), 104));
        rooms.add(new Room(5, 1, 5, List.of(patients.get(8), patients.get(9)), 105));

        rooms.add(new Room(5, 2, 6, List.of(patients.get(10), patients.get(11), patients.get(11), patients.get(12)), 201));
        rooms.add(new Room(5, 2, 7, List.of(patients.get(13), patients.get(14), patients.get(15), patients.get(16)), 202));
        rooms.add(new Room(5, 2, 8, List.of(patients.get(17), patients.get(18), patients.get(19), patients.get(20)), 203));
        roomManager.setRooms(rooms);
    }

    private void start() throws SAXException, ParserConfigurationException, IOException {
        readder.readXML();
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
                view.showSuccessMessage("Se cerró exitosamente el programa");
                view.dispose();
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
