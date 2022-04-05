package it.unimol.gameui.gamegui.gameinfopanel;

import it.unimol.gameui.gamegui.MainFrame;
import it.unimol.gameui.gamegui.PanelValue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import static it.unimol.gameengine.utils.UtilStrings.BYAUTHOR;
import static it.unimol.gameengine.utils.UtilStrings.INDICATOR_SCENE;
import static it.unimol.gameengine.utils.UtilStrings.LOSING_SCENE;
import static it.unimol.gameengine.utils.UtilStrings.MAINMENU;
import static it.unimol.gameengine.utils.UtilStrings.MINEFIELD;
import static it.unimol.gameengine.utils.UtilStrings.NEWGAME;
import static it.unimol.gameengine.utils.UtilStrings.TUTORIAL;

/**
 * Graphic implementation of an Informative Screen for the Game
 *
 * @author Maurizio Albani
 */
public class GameInfoPanel extends JPanel {

    private JLabel gameNameLbl;

    private JLabel gameAuthorLbl;

    private JLabel gameTutorialLbl;

    private JLabel indicatorSceneIcoLbl;

    private JLabel indicatorSceneLbl;

    private JLabel losingSceneIcoLbl;

    private JLabel losingSceneLbl;

    private JButton goBackBtn;

    private JButton startGameBtn;

    /**
     * Class constructor that initializes the InfoScreen and it's contents
     */
    public GameInfoPanel() {
        super();
        this.setSize(500, 470);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        this.initializeComponents();
        this.addActionListeners();
        this.addComponentsToPanel();
    }

    private void initializeComponents() {
        initializeTexts();
        initializeIcons();
        initializeButtons();
    }

    private void initializeTexts() {
        this.gameNameLbl = new JLabel(MINEFIELD);
        this.gameNameLbl.setForeground(Color.RED);
        this.gameNameLbl.setFont(new Font(this.gameNameLbl.getFont().getName(), Font.PLAIN, 30));

        this.gameAuthorLbl = new JLabel(BYAUTHOR);
        this.gameAuthorLbl.setForeground(Color.WHITE);
        this.gameAuthorLbl.setFont(new Font(this.gameAuthorLbl.getFont().getName(), Font.BOLD, 15));

        this.gameTutorialLbl = new JLabel(TUTORIAL);
        this.gameTutorialLbl.setForeground(Color.WHITE);
        this.indicatorSceneLbl = new JLabel(INDICATOR_SCENE);
        this.indicatorSceneLbl.setForeground(Color.WHITE);
        this.losingSceneLbl = new JLabel(LOSING_SCENE);
        this.losingSceneLbl.setForeground(Color.WHITE);
    }

    private void initializeIcons() {
        String indicatorScenePath = "/indicatorscene.png";
        String losingScenePath = "/losingscene.png";
        URL indicatorSceneURL = getClass().getResource(indicatorScenePath);
        URL losingSceneURL = getClass().getResource(losingScenePath);

        ImageIcon tempIcon = null;
        if (indicatorSceneURL != null) {
            tempIcon = new ImageIcon(indicatorSceneURL);
        }
        this.indicatorSceneIcoLbl = new JLabel(tempIcon);

        if (losingSceneURL != null) {
            tempIcon = new ImageIcon(losingSceneURL);
        }
        this.losingSceneIcoLbl = new JLabel(tempIcon);
    }

    private void initializeButtons() {
        this.goBackBtn = new JButton(MAINMENU);
        this.goBackBtn.setPreferredSize(new Dimension(150, 25));
        this.goBackBtn.setBackground(Color.ORANGE);
        this.goBackBtn.setForeground(Color.BLUE);

        this.startGameBtn = new JButton(NEWGAME);
        this.startGameBtn.setPreferredSize(new Dimension(150, 25));
        this.startGameBtn.setBackground(Color.ORANGE);
        this.startGameBtn.setForeground(Color.BLUE);
    }

    private void addActionListeners() {
        this.goBackBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainFrame.getInstance().showPanel(PanelValue.MAIN_MENU);
            }
        });

        this.startGameBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainFrame.getInstance().showPanel(PanelValue.SELECT_DIFFICULTY);
            }
        });

    }

    private void addComponentsToPanel() {
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 4;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        this.add(this.gameNameLbl, c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(this.gameAuthorLbl, c);

        c.weighty = 0.5;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(this.gameTutorialLbl, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(this.indicatorSceneIcoLbl, c);

        c.gridx = 2;
        this.add(this.indicatorSceneLbl, c);

        c.gridx = 0;
        c.gridy = 4;
        this.add(this.losingSceneIcoLbl, c);

        c.gridx = 2;
        this.add(this.losingSceneLbl, c);

        c.gridwidth = 1;
        c.gridy = 5;
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.goBackBtn, c);

        c.gridx = 3;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.startGameBtn, c);

    }
}
