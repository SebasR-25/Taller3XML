package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private RoomPanel roomPanel;
    private PatientPanel patientPanel;
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
    }

    private void addComponents() {
        addMainPanel();
        addRoomPanel();
        addPatientPanel();
    }

    private void addMainPanel() {
        getContentPane().add(mainPanel);
        mainPanel.getOutButton().addActionListener(e -> dispose());
        mainPanel.getAddRoomButton().addActionListener(e -> {
            mainPanel.setVisible(false);
            roomPanel.setVisible(true);
            patientPanel.setVisible(false);
        });
        mainPanel.getAddPatientRoom().addActionListener(e -> {
            mainPanel.setVisible(false);
            roomPanel.setVisible(false);
            patientPanel.setVisible(true);
        });

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

    private void backToPrincipalMenu() {
        mainPanel.setVisible(true);
        roomPanel.setVisible(false);
        patientPanel.setVisible(false);
        clearRoomFields();
        clearPatientFields();
    }

    private void clearRoomFields() {
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

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void start() {
        setVisible(true);
    }
}
