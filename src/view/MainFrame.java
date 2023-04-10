package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private RoomPanel roomPanel;
    private PatientPanel patientPanel;
    private HistoryPanel historyPanel;
    private ActionListener actionListener;

    public MainFrame(ActionListener actionListener) {
        super("Menu");
        this.actionListener = actionListener;
        setLayout(new GridBagLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
        initComponents();
        addComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    private void initComponents() {
        mainPanel = new MainPanel();
        roomPanel = new RoomPanel();
        patientPanel = new PatientPanel();
        historyPanel = new HistoryPanel();
    }

    private void addComponents() {
        addMainPanel();
        addRoomPanel();
        addPatientPanel();
        addHistoryPanel();
    }

    private void addMainPanel() {
        getContentPane().add(mainPanel);
        mainPanel.getOutButton().addActionListener(e -> dispose());
        mainPanel.getAddRoomButton().addActionListener(e -> {
            mainPanel.setVisible(false);
            roomPanel.setVisible(true);
            patientPanel.setVisible(false);
            historyPanel.setVisible(false);
        });
        mainPanel.getAddPatientRoom().addActionListener(e -> {
            mainPanel.setVisible(false);
            roomPanel.setVisible(false);
            patientPanel.setVisible(true);
            historyPanel.setVisible(false);
        });
        mainPanel.getShowRoomsPatientHistory().addActionListener(e -> {
            mainPanel.setVisible(false);
            roomPanel.setVisible(false);
            patientPanel.setVisible(false);
            historyPanel.setVisible(true);
        });
        historyPanel.getSearchButton().addActionListener(actionListener);
        historyPanel.getSearchButton().setActionCommand("SEARCH_ROOM");
        mainPanel.getGenerateXmlButton().addActionListener(actionListener);
        mainPanel.getGenerateXmlButton().setActionCommand("GENERATE_XML");
        patientPanel.getAddButton().addActionListener(actionListener);
        patientPanel.getAddButton().setActionCommand("CREATE_PATIENT");
        roomPanel.getCreateRoomButton().addActionListener(actionListener);
        roomPanel.getCreateRoomButton().setActionCommand("CREATE_ROOM");
    }

    private void addRoomPanel() {
        getContentPane().add(roomPanel);
        roomPanel.setVisible(false);
        roomPanel.getBackButton().addActionListener(e -> backToPrincipalMenu());
    }

    private void addPatientPanel() {
        getContentPane().add(patientPanel);
        patientPanel.setVisible(false);
        patientPanel.getCancelButton().addActionListener(e -> backToPrincipalMenu());
    }

    private void addHistoryPanel() {
        getContentPane().add(historyPanel);
        historyPanel.setVisible(false);
        historyPanel.getBackButton().addActionListener(e -> backToPrincipalMenu());
    }

    private void backToPrincipalMenu() {
        mainPanel.setVisible(true);
        roomPanel.setVisible(false);
        patientPanel.setVisible(false);
        historyPanel.setVisible(false);
        clearRoomFields();
        clearPatientFields();
        historyPanel.loadDefaultValues();
    }

    public void clearRoomFields() {
        roomPanel.getIdTextField().setText("");
        roomPanel.getFloorTextField().setText("");
        roomPanel.getRoomNumberTextField().setText("");
        roomPanel.getBedNumberTextField().setText("");
    }

    private void clearPatientFields() {
        patientPanel.getRoomNumberField().setText("");
        patientPanel.getPatientNameField().setText("");
        patientPanel.getPatientLastNameField().setText("");
        patientPanel.getPatientPhoneField().setText("");
    }

    public void loadRoomToHistoryPanel(List<String> roomInfo, List<String> patients) {
        historyPanel.setRoomLabelInfo(roomInfo);
        historyPanel.setPatientsData(patients);
    }

    public String getRoomIdToSearch() {
        return historyPanel.getIdRoomField().getText();
    }
    public int getRoomNumberToPatient() {
        return Integer.parseInt(patientPanel.getRoomNumberField().getText());
    }
    public String getPatientName() {
        return patientPanel.getPatientNameField().getText();
    }
    public String getPatientLastName() {
        return patientPanel.getPatientLastNameField().getText();
    }
    public String getContactPhoneNumber() {
        return patientPanel.getPatientPhoneField().getText();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void start() {
        setVisible(true);
    }

    public String getNewRoomId() {
        return roomPanel.getIdTextField().getText();
    }

    public String getNewRoomFloor() {
        return roomPanel.getFloorTextField().getText();
    }

    public String getNewRoomNumber() {
        return roomPanel.getRoomNumberTextField().getText();
    }

    public String getNewRoomBedNumber() {
        return roomPanel.getBedNumberTextField().getText();
    }
}
