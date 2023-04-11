package presenter;

import fileOperations.ReadXML;
import model.Patient;
import model.Room;
import model.RoomManager;
import model.Status;
import org.xml.sax.SAXException;
import view.MainFrame;
import fileOperations.*;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

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
                try {
                    addPatient(view.getRoomNumberToPatient(), view.getPatientName(), view.getPatientLastName(), view.getContactPhoneNumber());
                } catch (NumberFormatException e) {
                    view.showErrorMessage("Debe ingresar todos los datos");
                }
                break;
            case "CREATE_ROOM":
                try {
                    addRoom(Integer.parseInt(view.getNewRoomId()), Integer.parseInt(view.getNewRoomFloor()), Integer.parseInt(view.getNewRoomNumber()), Integer.parseInt(view.getNewRoomBedNumber()));
                } catch (NumberFormatException e) {
                    view.showErrorMessage("Debe ingresar todos los datos");
                }
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

    private void setPatientStatus(int roomNumber, String patient, Status status) {
        for (Patient patient1 : roomManager.searchRoomByNumber(roomNumber).getPatients()) {
            if ((patient1.getFirstName() + " " + patient1.getLastName()).equals(patient)) {
                patient1.setStatus(status);
            }
        }
    }


    private List<String> patientsActive(int roomNumber) {
        List<String> patients = new ArrayList<>();
        for (Patient patient : roomManager.searchRoomByNumber(roomNumber).getPatients()) {
            if (patient.getStatus() == Status.ACTIVE) {
                patients.add(patient.getFirstName() + " " + patient.getLastName());
            }
        }
        return patients;
    }
    private void addRoom(int id, int floor, int roomNumber, int bedNumber) {
        if (id < 0 && roomNumber < 0) {
            view.showErrorMessage("Los datos ingresados no son validos");
        } else if (roomManager.searchRoomById(id) != null) {
            view.showErrorMessage("La habitación ya existe");
        } else if (floor <= 0 || floor > 30) {
            view.showErrorMessage("El piso debe Estar entre 1 y 30");
        } else if (roomManager.searchRoomByNumber(roomNumber) != null && roomManager.searchRoomByNumber(roomNumber).getFloorNumber() == floor) {
            view.showErrorMessage("El número de habitación ya existe en el piso");
        } else if (bedNumber <= 0 || bedNumber > 5) {
            view.showErrorMessage("La cantidad de camas debe estar entre 1 y 5");
        } else {
            roomManager.addRoom(new Room(bedNumber, floor, id, new ArrayList<>(), roomNumber));
            view.showSuccessMessage("La habitación ha sido creada");
            view.clearRoomFields();
        }
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
        if (roomNumber < 0) {
            view.showErrorMessage("El número de la habitación debe ser mayor que 0");
        } else if (roomManager.searchRoomByNumber(roomNumber) == null) {
            view.showErrorMessage("La habitación no existe");
        } else if (firstName.isEmpty() || lastName.isEmpty() || contactPhoneNumber.isEmpty()) {
            view.showErrorMessage("Debe ingresar todos los datos");
        } else if (getPatiensActive(roomNumber) >= roomManager.searchRoomByNumber(roomNumber).getBedNumber()) {
            view.showErrorMessage("La habitación está llena");
            try {
                String patient = view.patientToInactivate(roomNumber, patientsActive(roomNumber));
                setPatientStatus(roomNumber, patient, Status.INACTIVE);
                addPatient(roomNumber, firstName, lastName, contactPhoneNumber);
            } catch (NullPointerException ignored) {
            }
        } else {
            roomManager.searchRoomByNumber(roomNumber).addPatient(new Patient(contactPhoneNumber, firstName, lastName, Status.ACTIVE));
            view.showSuccessMessage("El paciente ha sido creado");
            view.clearPatientFields();
        }
    }

    private int getPatiensActive(int roomNumber) {
        int count = 0;
        for (Patient patient : roomManager.searchRoomByNumber(roomNumber).getPatients()) {
            if (patient.getStatus() == Status.ACTIVE) {
                count++;
            }
        }
        return count;
    }
}
