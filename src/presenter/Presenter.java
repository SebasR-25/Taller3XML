package presenter;

import view.MainFrame;

public class Presenter {
    private MainFrame view;
    public Presenter() {
        view = new MainFrame();
    }

    private void start() {
        view.setVisible(true);
    }

    public static void main(String[] args) {
        new Presenter().start();
    }
}
