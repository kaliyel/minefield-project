package it.unimol.gameui.gamegui.mainmenupanel;

import static it.unimol.gameengine.utils.UtilStrings.*;
import it.unimol.gameui.gamegui.MainFrame;
import it.unimol.gameui.gamegui.PanelValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Graphic implementation of a Main Menu for the Game
 *
 * @author Maurizio Albani
 */
public class MainMenuPanel extends JPanel {
    private JLabel gameName;
    private JButton newGameButton;
    private JButton gameInfoButton;
    private JButton exitGameButton;

    /**
     * Class Constructor that sets the attributes of the Panel and it's contents
     */
    public MainMenuPanel() {
        super();
        this.setSize(500, 470);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        this.initializeComponents();
        this.addComponentsToPanel();
        this.addActionListeners();
    }

    private void initializeComponents() {
        this.gameName = new JLabel(MINEFIELD);
        this.gameName.setForeground(Color.RED);
        this.gameName.setFont(new Font(this.gameName.getFont().getName(), Font.PLAIN, 70));

        this.newGameButton = new JButton(NEWGAME);
        this.newGameButton.setForeground(Color.BLUE);
        this.newGameButton.setBackground(Color.ORANGE);
        this.newGameButton.setPreferredSize(new Dimension(300, 25));

        this.exitGameButton = new JButton(QUIT);
        this.exitGameButton.setForeground(Color.BLUE);
        this.exitGameButton.setBackground(Color.ORANGE);
        this.exitGameButton.setPreferredSize(new Dimension(150, 25));

        this.gameInfoButton = new JButton(INFO);
        this.gameInfoButton.setForeground(Color.BLUE);
        this.gameInfoButton.setBackground(Color.ORANGE);
        this.gameInfoButton.setPreferredSize(new Dimension(150, 25));
    }

    private void addComponentsToPanel() {

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 6;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        this.add(this.gameName, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(this.newGameButton, c);

        c.gridy = 2;
        c.weighty = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(this.gameInfoButton, c);

        c.gridx = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(this.exitGameButton, c);

    }

    private void addActionListeners() {
        this.addNewGameActionListener();
        this.addGameInfoActionListener();
        this.addExitActionListener();
    }

    private void addNewGameActionListener() {
        this.newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainFrame.getInstance().showPanel(PanelValue.SELECT_DIFFICULTY);
            }
        });
    }

    private void addGameInfoActionListener() {
        this.gameInfoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainFrame.getInstance().showPanel((PanelValue.INFO));
            }
        });
    }

    private void addExitActionListener() {
        this.exitGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int choice = JOptionPane.showConfirmDialog(null,
                        WANNAQUIT,
                        QUIT,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }

}