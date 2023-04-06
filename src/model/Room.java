package model;

import java.util.List;

public class Room{
    private int bedNumber;
    private int floorNumber;
    private int id;
    private List<Patient> patients;
    private int roomNumber;
    
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
    public int getroomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
