package model;

import java.util.ArrayList;
import java.util.List;

public class Room{
    private int bedNumber;
    private int floorNumber;
    private int id;
    private List<Patient> patients;
    private int roomNumber;

    public Room() {
        bedNumber = 0;
        floorNumber = 0;
        id = 0;
        patients = new ArrayList<>();
        roomNumber = 0;
    }

    public Room(int bedNumber, int floorNumber, int id, List<Patient> patients, int roomNumber) {
        this.bedNumber = bedNumber;
        this.floorNumber = floorNumber;
        this.id = id;
        this.patients = patients;
        this.roomNumber = roomNumber;
    }

    public void addPatient(Patient patient){
        patients.add(patient);
    }
    public int getBedNumber() {
        return bedNumber;
    }
    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }
    public int getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
