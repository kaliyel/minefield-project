package it.unimol.gameui.gamegui.gamepanel;
import static it.unimol.gameengine.utils.UtilStrings.*;

import javax.swing.*;
import java.awt.*;

/**
 * Graphic implementation of the Game Board
 *
 * @author Maurizio Albani
 */
public class GamePanel extends JPanel {
    /**
     * Class Constructor that initializes the Panel
     */
    public GamePanel(){
        super();
        this.setSize(500, 470);
        this.setBackground(Color.DARK_GRAY);
    }

    /**
     * Generates a new GameBoard
     */
    public void startGame(){
        this.removeAll();

        JLabel gameName = new JLabel(MINEFIELD);
        gameName.setForeground(Color.RED);
        BoardPanel boardPanel = new BoardPanel();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(gameName, c);

        c.gridy = 1;
        this.add(boardPanel, c);

        revalidate();
        repaint();
    }
}