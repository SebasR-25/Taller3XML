package model;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private List<Room> rooms;

    public RoomManager() {
        rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Room searchRoom(int idRoom) {
        for (Room room : rooms) {
            if (room.getId() == idRoom) {
                return room;
            }
        }
        return null;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
    public void addPatient(Room room, Patient patient){
        room.addPatient(patient);
    }
}
