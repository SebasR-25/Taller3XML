package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class RoomPanel extends JPanel {
    private JLabel titleLabel;
    private JTextField idTextField;
    private JTextField floorTextField;
    private JTextField roomNumberTextField;
    private JTextField bedNumberTextField;
    private JButton createRoomButton;
    private JButton backButton;

    public RoomPanel() {
        setLayout(new GridBagLayout());
        initComponents();
        setFeatures();
        addComponents();
        setVisible(false);
    }

    private void initComponents() {
        titleLabel = new JLabel("<html><center><h1>Crear una habitaci\u00f3n</h1></center></html>");
        idTextField = new JTextField();
        floorTextField = new JTextField();
        roomNumberTextField = new JTextField();
        bedNumberTextField = new JTextField();
        createRoomButton = new JButton("<html><center><h2>Crear habitaci\u00f3n</h2></center></html>");
        backButton = new JButton("<html><center><h2>Volver</h2></center></html>");
    }

    private void addComponents() {
        add(titleLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JLabel("<html><h2>Ingrese el Id de la habitaci\u00f3n: </h2></html>"), new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        add(idTextField, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JLabel("<html><h2>Ingrese el n\u00famero de piso: (N\u00fameros entre 1 y 30)</h2></html>"), new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 50, 0, 10), 0, 0));
        add(floorTextField, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JLabel("<html><h2>Ingrese el n\u00famero de la habitaci\u00f3n: </h2></html>"), new GridBagConstraints(0, 5, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 50, 0, 10), 0, 0));
        add(roomNumberTextField, new GridBagConstraints(0, 6, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JLabel("<html><h2>Ingrese el n\u00famero de camas: (N\u00famero entre 1 y 5)</h2></html>"), new GridBagConstraints(0, 7, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 50, 0, 10), 0, 0));
        add(bedNumberTextField, new GridBagConstraints(0, 8, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
        add(createRoomButton, new GridBagConstraints(0, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 50), 0, 0));
        add(backButton, new GridBagConstraints(1, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 50, 0, 0), 0, 0));
    }

    private void setFeatures() {
        textFieldSetFeatures(idTextField);
        textFieldSetFeatures(floorTextField);
        textFieldSetFeatures(roomNumberTextField);
        textFieldSetFeatures(bedNumberTextField);
        buttonSetFeatures(createRoomButton);
        buttonSetFeatures(backButton);
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

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getFloorTextField() {
        return floorTextField;
    }

    public JTextField getRoomNumberTextField() {
        return roomNumberTextField;
    }

    public JTextField getBedNumberTextField() {
        return bedNumberTextField;
    }

    public JButton getCreateRoomButton() {
        return createRoomButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
