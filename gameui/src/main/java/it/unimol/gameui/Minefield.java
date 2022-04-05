package it.unimol.gameui;

import it.unimol.gameui.gamegui.MainFrame;

/**
 * Main Class of the Program
 *
 * @author Maurizio Albani
 */
public final class Minefield {
    private Minefield() { }

    public static void main(String[] args) {
        MainFrame.getInstance().setVisible(true);
    }
}
