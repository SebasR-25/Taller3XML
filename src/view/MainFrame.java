package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel mainPanel;
    private RoomPanel roomPanel;
    private PatientPanel patientPanel;
    public MainFrame() {
        super("Menu");
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

    private void addComponents() {
        addMainPanel();
        addRoomPanel();
        addPatientPanel();
    }

    private void addPatientPanel() {
        getContentPane().add(patientPanel);
        patientPanel.setVisible(false);
    }

    private void addMainPanel() {
        getContentPane().add(mainPanel);
        mainPanel.getOutButton().addActionListener(e -> dispose());
    }

    private void addRoomPanel() {
        getContentPane().add(roomPanel);
        roomPanel.setVisible(false);
    }

    private void initComponents() {
        mainPanel = new MainPanel();
        roomPanel = new RoomPanel();
        patientPanel = new PatientPanel();
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void start() {
        setVisible(true);
    }
}
