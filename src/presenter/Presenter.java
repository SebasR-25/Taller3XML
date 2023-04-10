package presenter;

import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {
    private MainFrame view;

    public Presenter() {
        view = new MainFrame(this);
    }

    private void start() {
        view.setVisible(true);
    }

    public static void main(String[] args) {
        new Presenter().start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "SEARCH_ROOM":
//                view.loadRoomToHistoryPanel();
                break;
            case "generateXML":
                break;
        }
    }
}
