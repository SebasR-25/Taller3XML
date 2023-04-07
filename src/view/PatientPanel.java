package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class PatientPanel extends JPanel {
    private JLabel titleLabel;
    private JTextField roomNumberField, patientNameField,patientLastNameField, patientPhoneField;
    private JButton addButton, cancelButton;

    public PatientPanel() {
        setLayout(new GridBagLayout());
        initComponents();
        setFeatures();
        addComponents();
        setVisible(false);
    }

    private void initComponents() {
        titleLabel = new JLabel("<html><center><h1>Crear un paciente</h1></center></html>");
        roomNumberField = new JTextField();
        patientNameField = new JTextField();
        patientLastNameField = new JTextField();
        patientPhoneField = new JTextField();
        addButton = new JButton("<html><center><h2>Agregar</h2></center></html>");
        cancelButton = new JButton("<html><center><h2>Cancelar</h2></center></html>");
    }

    private void setFeatures() {
        buttonSetFeatures(addButton);
        buttonSetFeatures(cancelButton);
        textFieldSetFeatures(roomNumberField);
        textFieldSetFeatures(patientNameField);
        textFieldSetFeatures(patientLastNameField);
        textFieldSetFeatures(patientPhoneField);
    }

    private void buttonSetFeatures(JButton button) {
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(Globals.OPTION_BUTTON_FONT);
        button.setFocusable(false);
    }

    private void textFieldSetFeatures(JTextField textField) {
        textField.setPreferredSize(new Dimension(400, 30));
        textField.setFont(Globals.FIELD_FONT);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    private void addComponents() {
        add(titleLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JLabel("<html><center><h2>Ingrese el n\u00famero de la habitaci\u00f3n</h2></center></html>"), new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        add(roomNumberField, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(new JLabel("<html><center><h2>Ingrese el nombre del paciente</h2></center></html>"), new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        add(patientNameField, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(new JLabel("<html><center><h2>Ingrese el apellido del paciente</h2></center></html>"), new GridBagConstraints(0, 5, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        add(patientLastNameField, new GridBagConstraints(0, 6, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(new JLabel("<html><center><h2>Ingrese el tel\u00e9fono del paciente</h2></center></html>"), new GridBagConstraints(0, 7, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        add(patientPhoneField, new GridBagConstraints(0, 8, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        add(addButton, new GridBagConstraints(0, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 25), 0, 0));
        add(cancelButton, new GridBagConstraints(1, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 25, 0, 0), 0, 0));
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextField getRoomNumberField() {
        return roomNumberField;
    }

    public JTextField getPatientNameField() {
        return patientNameField;
    }

    public JTextField getPatientLastNameField() {
        return patientLastNameField;
    }

    public JTextField getPatientPhoneField() {
        return patientPhoneField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
