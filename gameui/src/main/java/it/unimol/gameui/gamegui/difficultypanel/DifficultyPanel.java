package it.unimol.gameui.gamegui.difficultypanel;

import it.unimol.gameengine.MineFieldGameHandler;
import it.unimol.gameengine.exceptions.WrongBoardSizeException;
import it.unimol.gameengine.exceptions.WrongBombQuantityException;
import it.unimol.gameui.gamegui.MainFrame;
import it.unimol.gameui.gamegui.PanelValue;
import it.unimol.gameui.gamegui.gamepanel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static it.unimol.gameengine.utils.UtilStrings.*;

/**
 * Graphical Implementation of a Selection Screen for the Difficulty of the Game
 *
 * @author Maurizio Albani
 */
public class DifficultyPanel extends JPanel {
    private final GamePanel gamePanel;
    private JLabel gameName;
    private JLabel selectionText;
    private JButton easyBtn;
    private JButton normalBtn;
    private JButton hardBtn;
    private JButton customBtn;
    private JButton goBackBtn;

    private JLabel rowLabel;
    private JLabel columnLabel;
    private JLabel bombLabel;
    private JTextField rowField;
    private JTextField columnField;
    private JTextField bombField;
    private JButton confirmCustomBtn;

    private boolean hidden;

    /**
     * Class Constructor that initializes the Panel and it's Contents and handles the creation of a new Game board
     * @param gamePanel the panel in which the Game board will be generated
     */
    public DifficultyPanel(GamePanel gamePanel) {
        super();
        this.setSize(500, 470);
        this.gamePanel = gamePanel;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        this.initializeAllComponents();
        this.setActionListeners();
        this.addComponentsToPanel();
    }

    private void initializeAllComponents() {
        this.initializeNonHiddenComponents();
        this.initializeHiddenComponents();
        this.setComponetsColor();
        this.hideHiddenComponents();
    }

    private void initializeNonHiddenComponents() {

        this.gameName = new JLabel(MINEFIELD);
        this.gameName.setFont(new Font(this.gameName.getFont().getName(), Font.PLAIN, 30));
        this.selectionText = new JLabel(CHOSEDIFFICULTY);
        this.selectionText.setFont(new Font(this.gameName.getFont().getName(), Font.BOLD, 15));
        this.easyBtn = new JButton(EASY);
        this.easyBtn.setPreferredSize(new Dimension(150, 25));
        this.normalBtn = new JButton(NORMAL);
        this.normalBtn.setPreferredSize(new Dimension(150, 25));
        this.hardBtn = new JButton(HARD);
        this.hardBtn.setPreferredSize(new Dimension(150, 25));
        this.customBtn = new JButton(CUSTOM);
        this.customBtn.setPreferredSize(new Dimension(150, 25));
        this.goBackBtn = new JButton(MAINMENU);
        this.goBackBtn.setPreferredSize(new Dimension(150, 25));
    }

    private void initializeHiddenComponents() {
        this.rowLabel = new JLabel(NROWS);
        this.rowLabel.setPreferredSize(new Dimension(150, 25));
        this.rowLabel.setForeground(Color.WHITE);
        this.rowField = new JTextField();
        this.rowField.setPreferredSize(new Dimension(150, 25));
        this.columnLabel = new JLabel(NCOLUMNS);
        this.columnLabel.setPreferredSize(new Dimension(150, 25));
        this.columnLabel.setForeground(Color.WHITE);
        this.columnField = new JTextField();
        this.columnField.setPreferredSize(new Dimension(150, 25));
        this.bombLabel = new JLabel(NBOMBS);
        this.bombLabel.setPreferredSize(new Dimension(150, 25));
        this.bombLabel.setForeground(Color.WHITE);
        this.bombField = new JTextField();
        this.bombField.setPreferredSize(new Dimension(150, 25));
        this.confirmCustomBtn = new JButton(CONFIRM);
        this.confirmCustomBtn.setPreferredSize(new Dimension(150, 25));
    }

    private void setComponetsColor() {
        this.gameName.setForeground(Color.RED);
        this.selectionText.setForeground(Color.WHITE);
        this.easyBtn.setForeground(Color.BLUE);
        this.easyBtn.setBackground(Color.ORANGE);
        this.normalBtn.setForeground(Color.BLUE);
        this.normalBtn.setBackground(Color.ORANGE);
        this.hardBtn.setForeground(Color.BLUE);
        this.hardBtn.setBackground(Color.ORANGE);
        this.customBtn.setForeground(Color.BLUE);
        this.customBtn.setBackground(Color.ORANGE);

        this.goBackBtn.setForeground(Color.BLUE);
        this.goBackBtn.setBackground(Color.ORANGE);
        this.confirmCustomBtn.setForeground(Color.BLUE);
        this.confirmCustomBtn.setBackground(Color.ORANGE);
    }

    private void hideHiddenComponents() {
        this.rowLabel.setVisible(false);
        this.rowField.setVisible(false);
        this.columnLabel.setVisible(false);
        this.columnField.setVisible(false);
        this.bombLabel.setVisible(false);
        this.bombField.setVisible(false);
        this.confirmCustomBtn.setVisible(false);

        this.hidden = true;
    }

    private void setActionListeners() {
        this.setEasyButtonListener();
        this.setNormalButtonListener();
        this.setHardButtonListener();
        this.setGoBackButtonListener();
        this.setCustomButtonListener();
        this.setConfirmCustomListener();
    }

    private void setEasyButtonListener() {
        this.easyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();

                try {
                    gameHandler.initializeGame(5, 5, 5);
                } catch (WrongBoardSizeException | WrongBombQuantityException ignore) {
                    assert false;
                }
                gamePanel.startGame();
                MainFrame.getInstance().showPanel(PanelValue.GAME);
            }
        });
    }

    private void setNormalButtonListener() {
        this.normalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();

                try {
                    gameHandler.initializeGame(5, 5, 10);
                } catch (WrongBoardSizeException | WrongBombQuantityException ignore) {
                    assert false;
                }
                gamePanel.startGame();
                MainFrame.getInstance().showPanel(PanelValue.GAME);
            }
        });
    }

    private void setHardButtonListener() {
        this.hardBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MineFieldGameHandler gameHandler = MineFieldGameHandler.getInstance();

                try {
                    gameHandler.initializeGame(5, 5, 15);
                } catch (WrongBoardSizeException | WrongBombQuantityException ignore) {
                    assert false;
                }
                gamePanel.startGame();
                MainFrame.getInstance().showPanel(PanelValue.GAME);
            }
        });
    }

    private void setGoBackButtonListener() {
        this.goBackBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainFrame.getInstance().showPanel(PanelValue.MAIN_MENU);
            }
        });
    }

    private void setCustomButtonListener() {
        this.customBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(hidden) {
                    visualizeHiddenComponents();
                } else {
                    hideHiddenComponents();
                }
            }
        });
    }

    private void setConfirmCustomListener() {
        this.confirmCustomBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int rows = 0;
                int columns = 0;
                int bombs = 0;
                boolean correctInput = true;

                try {
                    rows = Integer.parseInt(rowField.getText());
                    columns = Integer.parseInt(columnField.getText());
                    bombs = Integer.parseInt(bombField.getText());
                } catch (NumberFormatException ex) {
                    correctInput = false;
                    JOptionPane.showMessageDialog(null,
                            WRONGINPUT,
                            WRONGINPUTTITLE,
                            JOptionPane.ERROR_MESSAGE);
                }

                if (correctInput) {
                    try {
                        MineFieldGameHandler.getInstance().initializeGame(rows, columns, bombs);
                    } catch (WrongBoardSizeException ex) {
                        JOptionPane.showMessageDialog(null,
                                WRONGBOARDSIZE,
                                WRONGBOARDSIZETITLE,
                                JOptionPane.ERROR_MESSAGE);
                        correctInput = false;
                    } catch (WrongBombQuantityException ex) {
                        JOptionPane.showMessageDialog(null,
                                WRONGBOMBNUMBER + ex.getMessage(),
                                WRONGBOMBNUMBERTITLE,
                                JOptionPane.ERROR_MESSAGE);
                        correctInput = false;
                    }
                }

                if (correctInput) {
                    gamePanel.startGame();
                    hideHiddenComponents();
                    MainFrame.getInstance().showPanel(PanelValue.GAME);
                }
            }
        });
    }

    private void visualizeHiddenComponents() {
        this.rowLabel.setVisible(true);
        this.rowField.setVisible(true);
        this.columnLabel.setVisible(true);
        this.columnField.setVisible(true);
        this.bombLabel.setVisible(true);
        this.bombField.setVisible(true);
        this.confirmCustomBtn.setVisible(true);

        this.hidden = false;
    }

    private void addComponentsToPanel() {
        this.addNonHiddenComponents();
        this.addHiddenComponents();
    }

    private void addNonHiddenComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        this.add(this.gameName, c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(this.selectionText, c);

        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        this.add(this.easyBtn, c);

        c.gridy = 3;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(this.hardBtn, c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        this.add(this.normalBtn, c);

        c.gridy = 3;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(this.customBtn, c);

        c.gridx = 0;
        c.gridy = 11;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.goBackBtn, c);
    }

    private void addHiddenComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 0.5;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.rowLabel, c);

        c.gridy = 8;
        this.add(this.columnLabel, c);

        c.gridy = 9;
        this.add(this.bombLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.rowField, c);

        c.gridy = 8;
        this.add(this.columnField, c);

        c.gridy = 9;
        this.add(this.bombField, c);

        c.gridy = 11;
        this.add(this.confirmCustomBtn, c);
    }
}