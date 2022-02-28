package it.unimol.gameui.gamegui.gamepanel;

import it.unimol.gameengine.MineFieldGameHandler;

import javax.swing.*;
import java.awt.*;

class MineFieldBoard extends JPanel {
    private final int rows;
    private final int columns;

    MineFieldBoard() {
        super();

        MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();
        this.rows = gameHandler.getGameBoardRows();
        this.columns = gameHandler.getGameBoardColumns();

        this.setLayoutAndComponents();
    }

    private void setLayoutAndComponents() {
        this.setLayout(new GridLayout(rows, columns));
        MineFieldButton tempButton;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tempButton = new MineFieldButton(i, j);
                this.add(tempButton);
            }
        }
    }
}