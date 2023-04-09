package view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JLabel titleLabel;
    private JButton addRoomButton;
    private JButton addPatientRoom;
    private JButton showRoomsPatientHistory;
    private JButton generateXmlButton;
    private JButton outButton;

    public MainPanel() {
        setLayout(new GridBagLayout());
        initComponents();
        setFeatures();
        addComponents();
        setVisible(true);
    }

    private void initComponents() {
        titleLabel = new JLabel("<html><center><h1>Bienvenidos al sistema de disponibilidad de camas</h1> <h2>E.P.S. SANITAS</h2><center></html>");
        addRoomButton = new JButton("<html><center><h2>Crear una habitaci\u00f3n</h2></center></html>");
        addPatientRoom = new JButton("<html><center><h2>Crear un paciente</h2></center></html>");
        showRoomsPatientHistory = new JButton("<html><center><h2>Mostrar historial de pacientes por habitaci\u00f3n</h2></center></html>");
        generateXmlButton = new JButton("<html><center><h2>Generar XML</h2></center></html>");
        outButton = new JButton("<html><center><h2>Salir</h2></center></html>");

        setFeatures();
        addComponents();
    }

    private void setFeatures() {
        buttonSetFeatures(addRoomButton);
        buttonSetFeatures(addPatientRoom);
        buttonSetFeatures(showRoomsPatientHistory);
        buttonSetFeatures(generateXmlButton);
        buttonSetFeatures(outButton);
    }

    private void buttonSetFeatures(JButton button) {
        button.setPreferredSize(new Dimension(300, 50));
        button.setFont(Globals.OPTION_BUTTON_FONT);
        button.setFocusable(false);
    }

    private void addComponents() {
        add(titleLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(addRoomButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 50, 0, 0), 0, 0));
        add(addPatientRoom, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 50, 0, 0), 0, 0));
        add(showRoomsPatientHistory, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 50, 0, 0), 0, 0));
        add(generateXmlButton, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 50, 0, 0), 0, 0));
        add(outButton, new GridBagConstraints(0, 5, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 0, 0, 0), 0, 0));
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getAddRoomButton() {
        return addRoomButton;
    }

    public JButton getAddPatientRoom() {
        return addPatientRoom;
    }

    public JButton getShowRoomsPatientHistory() {
        return showRoomsPatientHistory;
    }

    public JButton getGenerateXmlButton() {
        return generateXmlButton;
    }

    public JButton getOutButton() {
        return outButton;
    }
}
