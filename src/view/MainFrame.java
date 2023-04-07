package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel mainPanel;
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
    }

    private void addMainPanel() {
        getContentPane().add(mainPanel);
        mainPanel.getOutButton().addActionListener(e -> dispose());
    }

    private void initComponents() {
        mainPanel = new MainPanel();
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void start() {
        setVisible(true);
    }
}
