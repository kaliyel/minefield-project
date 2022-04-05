package it.unimol.gameui.gamegui.gamepanel;

import it.unimol.gameengine.MineFieldGameHandler;
import it.unimol.gameengine.exceptions.BoardCoordinatesOutOfBoundException;
import it.unimol.gameengine.utils.CellContentID;
import it.unimol.gameengine.utils.GameStatus;

import static it.unimol.gameengine.utils.UtilStrings.DEFEAT;
import static it.unimol.gameengine.utils.UtilStrings.LOSER;
import static it.unimol.gameengine.utils.UtilStrings.VICTORY;
import static it.unimol.gameengine.utils.UtilStrings.WINNER;

import it.unimol.gameui.gamegui.MainFrame;
import it.unimol.gameui.gamegui.PanelValue;
import it.unimol.gameui.gamemusic.EffectSoundPlayer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

class MineFieldButton extends JButton {

    private final int row;

    private final int column;

    MineFieldButton(int rowInput, int columnInput) {
        super();

        this.row = rowInput;
        this.column = columnInput;
        Dimension preferredDimension = new Dimension(50, 50);

        this.setPreferredSize(preferredDimension);

        this.setActionListener();
    }

    private void setActionListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        String clickSoundPath = "/clicksound.wav";
                        new EffectSoundPlayer(clickSoundPath);

                        MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();
                        GameStatus gameStatus;
                        CellContentID cellContent;

                        cellContent = gameHandler.getCellContent(row, column);
                        changeSpaceToContent(cellContent);
                        gameHandler.markCell(row, column);
                        setEnabled(false);

                        gameStatus = gameHandler.getGameStatus();

                        if (gameStatus != null) {
                            showGameOverDialog(gameStatus);
                        }

                    } catch (BoardCoordinatesOutOfBoundException ignored) {
                        assert false;
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (getIcon() == null) {
                        setFlag();
                    } else {
                        removeFlag();
                    }
                }
            }
        });
    }

    private void removeFlag() {
        this.setIcon(null);
    }

    private void setFlag() {
        String flagIconPath = "/flag.png";
        URL flagIconURL = getClass().getResource(flagIconPath);
        ImageIcon victoryIcon = null;
        if (flagIconURL != null) {
            victoryIcon = new ImageIcon(flagIconURL);
        }
        this.setIcon(victoryIcon);
    }

    private void showGameOverDialog(GameStatus gameStatus) {
        switch (gameStatus) {
            case WINNER:
                String victoryIconPath = "/victory.png";
                URL victoryIconURL = getClass().getResource(victoryIconPath);
                String victorySoundPath = "/victorysound.wav";
                new EffectSoundPlayer(victorySoundPath);
                ImageIcon victoryIcon = null;
                if (victoryIconURL != null) {
                    victoryIcon = new ImageIcon(victoryIconURL);
                }
                JOptionPane.showMessageDialog(null,
                        WINNER,
                        VICTORY,
                        JOptionPane.PLAIN_MESSAGE,
                        victoryIcon);
                returnToMenu();
                break;
            case GAMEOVER:
                String defeatIconPath = "/defeat.png";
                URL defeatIconURL = getClass().getResource(defeatIconPath);
                ImageIcon defeatIcon = null;
                if (defeatIconURL != null) {
                    defeatIcon = new ImageIcon(defeatIconURL);
                }
                String defeatSoundPath = "/losesound.wav";
                new EffectSoundPlayer(defeatSoundPath);
                JOptionPane.showMessageDialog(null,
                        LOSER,
                        DEFEAT,
                        JOptionPane.PLAIN_MESSAGE,
                        defeatIcon);
                returnToMenu();
                break;
            default: break;
        }
    }

    private void returnToMenu() {
        MainFrame.getInstance().showPanel(PanelValue.MAIN_MENU);
    }

    private void changeSpaceToContent(CellContentID cellContent) {
        if (getIcon() != null) {
            removeFlag();
        }
        switch (cellContent) {
            case BOMB:
                setBackground(Color.RED);
                String bombIconPath = "/bomb.png";
                URL bombIconURL = getClass().getResource(bombIconPath);
                ImageIcon icon = null;
                if (bombIconURL != null) {
                    icon = new ImageIcon(bombIconURL);
                }
                this.setIcon(icon);
                break;
            case SAFESPACE:
                setBackground(Color.GREEN);
                break;
            default: break;
        }
    }
}
