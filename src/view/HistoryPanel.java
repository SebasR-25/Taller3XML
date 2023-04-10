package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private JLabel titleLabel;
    private JTextArea historicalTextArea;
    private int count = 1;

    public HistoryPanel() {
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        titleLabel = new JLabel();
        historicalTextArea = new JTextArea();
        historicalTextArea.setEditable(false);
        historicalTextArea.setFont(Globals.LIST_FONT);
        JScrollPane scrollPane = new JScrollPane(historicalTextArea);
        scrollPane.setMinimumSize(new Dimension(300, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(new JLabel("<html><center><h1>Historial</h1></center></html>"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 10);
        add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 100, 0, 0);
//        add(scrollPane, gbc);
    }

    public void addPatient(List<String> patientData) {
        historicalTextArea.append("Paciente " + count++ + ":");
        historicalTextArea.append("\n    Nombre: " + patientData.get(0));
        historicalTextArea.append("\n    Apellido: " + patientData.get(1));
        historicalTextArea.append("\n    Telefono: " + patientData.get(2));
        historicalTextArea.append("\n    Estado: " + patientData.get(3));
        historicalTextArea.append("\n\n");
    }

    public void clear() {
        historicalTextArea.setText("");
    }

    public void addRoomTitle(List<String> historial) {
        String title = "Habitacion: 101";
        titleLabel.setText("<html><h2><center>" + title + "</center></h2></html>");

    }
}
