package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel roomLabelInfo;
    private JTextField idRoomField;
    private JTextArea historicalTextArea;
    private JButton backButton;
    private JButton searchButton;

    public HistoryPanel() {
        setLayout(new GridBagLayout());
        initComponents();
        loadDefaultValues();
//        loadChargedData();
    }

    private void loadChargedData() {
        setRoomLabelInfo(List.of("1", "101", "1", "5"));
        setPatientsData(List.of("Juan", "Perez", "123456789", "Activo", "Pedro", "Gomez", "987654321", "Activo", "Maria", "Gonzalez", "123456789", "Activo", "Jose", "Perez", "987654321", "Activo", "Juan", "Perez", "123456789", "Activo"));
    }

    private void loadDefaultValues() {
        setRoomLabelInfo(List.of("NO ELEGIDO", "NO ELEGIDO", "NO ELEGIDO", "NO ELEGIDO"));
        setPatientsData(new ArrayList<>());
    }

    private void initComponents() {
        titleLabel = new JLabel("<html><center><h1>Historial de pacientes</h1></center></html>");
        roomLabelInfo = new JLabel();
        idRoomField = new JTextField();
        historicalTextArea = new JTextArea();
        backButton = new JButton("<html><center><h2>Volver</h2></center></html>");
        searchButton = new JButton("<html><center><h2>Buscar habitaci\u00f3n</h2></center></html>");

        addTitleLabel();
        addRoomLabelInfo();
        addIdRoomField();
        addHistoricalTextArea();
        addBackButton();
        addSearchButton();
    }

    private void addTitleLabel() {
        add(titleLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
    }

    private void addRoomLabelInfo() {
        add(roomLabelInfo, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));
    }

    private void addIdRoomField() {
        add(new JLabel("<html><center><h2>Id de la habitacion a buscar</h2></center></html>"), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));
        idRoomField.setPreferredSize(new Dimension(150, 50));
        idRoomField.setFont(Globals.FIELD_FONT);
        ((AbstractDocument) idRoomField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        add(idRoomField, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(-40, 10, 0, 10), 0, 0));
    }

    private void addHistoricalTextArea() {
        historicalTextArea.setEditable(false);
        historicalTextArea.setFont(Globals.FIELD_FONT);
        JScrollPane scrollPane = new JScrollPane(historicalTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        add(scrollPane, new GridBagConstraints(1, 1, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 10), 0, 0));
    }

    private void addSearchButton() {
        searchButton.setPreferredSize(new Dimension(150, 50));
        searchButton.setFont(Globals.OPTION_BUTTON_FONT);
        searchButton.setFocusable(false);
        add(searchButton, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
    }

    private void addBackButton() {
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(Globals.OPTION_BUTTON_FONT);
        backButton.setFocusable(false);
        add(backButton, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
    }

    public void setPatientsData(List<String> patientData) {
        for (int i = 0; i < patientData.size() / 4; i++) {
            historicalTextArea.append("Paciente " + i+1 + ": ");
            historicalTextArea.append("\n    Nombre: " + patientData.get(i * 4));
            historicalTextArea.append("\n    Apellido: " + patientData.get(i * 4 + 1));
            historicalTextArea.append("\n    Telefono: " + patientData.get(i * 4 + 2));
            historicalTextArea.append("\n    Estado: " + patientData.get(i * 4 + 3));
            historicalTextArea.append("\n\n");
        }
    }
    public void setRoomLabelInfo(List<String> roomData) {
        String builder = "Numero de habitaci\u00f3n " + roomData.get(0) +
                "<br>    N\u00famero de habitaci\u00f3n: " + roomData.get(1) +
                "<br>    N\u00famero de piso: " + roomData.get(2) +
                "<br>    N\u00famero de camas: " + roomData.get(3);
        roomLabelInfo.setText("<html><center><h2>Información de la habitación</h2><h3>" + builder + "</h3></center></html>");
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextArea getHistoricalTextArea() {
        return historicalTextArea;
    }

    public JLabel getRoomLabelInfo() {
        return roomLabelInfo;
    }

    public JTextField getIdRoomField() {
        return idRoomField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
