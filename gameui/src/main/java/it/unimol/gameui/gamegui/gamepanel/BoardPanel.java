package it.unimol.gameui.gamegui.gamepanel;

import it.unimol.gameengine.MineFieldGameHandler;
import it.unimol.gameengine.exceptions.BoardCoordinatesOutOfBoundException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

class BoardPanel extends JPanel {
    private final MineFieldBoard board;

    private final JPanel rowBombs;

    private final JPanel columnBombs;

    BoardPanel() {
        super();

        this.columnBombs = new JPanel();
        this.rowBombs = new JPanel();
        this.board = new MineFieldBoard();
        this.setLayout(new GridBagLayout());

        this.initializeRowBombs();
        this.initializeColumnBombs();
        this.setPanelContent();
    }

    private void setPanelContent() {
        this.setColumnBombLabels();
        this.setRowBombLabels();
        this.setGameBoard();
    }

    private void setGameBoard() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;

        this.add(this.board, constraints);
    }

    private void setRowBombLabels() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        this.add(this.rowBombs, constraints);
    }

    private void setColumnBombLabels() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LAST_LINE_END;

        this.add(this.columnBombs, constraints);
    }

    private void initializeRowBombs() {
        MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();
        int rows = gameHandler.getGameBoardRows();
        JLabel tempLabel;
        Dimension preferredDim = new Dimension(50, 50);

        this.rowBombs.setLayout(new GridLayout(0, 1));

        for (int i = 0; i < rows; i++) {
            try {
                int bombs = gameHandler.getBombsByRow(i);

                tempLabel = new JLabel(Integer.toString(bombs));
                tempLabel.setPreferredSize(preferredDim);
                tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tempLabel.setForeground(Color.DARK_GRAY);

                this.rowBombs.add(tempLabel);
            } catch (BoardCoordinatesOutOfBoundException ignore) {
                assert false;
            }
        }
    }

    private void initializeColumnBombs() {
        MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();
        int columns = gameHandler.getGameBoardColumns();
        JLabel tempLabel;
        Dimension preferredDim = new Dimension(50, 50);

        this.columnBombs.setLayout(new GridLayout(1, 0));
        for (int j = 0; j < columns; j++) {
            try {
                int bombs = gameHandler.getBombsByColumn(j);

                tempLabel = new JLabel(Integer.toString(bombs));
                tempLabel.setPreferredSize(preferredDim);
                tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tempLabel.setForeground(Color.DARK_GRAY);

                this.columnBombs.add(tempLabel);
            } catch (BoardCoordinatesOutOfBoundException ignore) {
                assert false;
            }
        }
    }
}
