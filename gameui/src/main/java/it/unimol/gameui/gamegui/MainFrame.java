package it.unimol.gameui.gamegui;

import it.unimol.gameui.gamegui.difficultypanel.DifficultyPanel;
import it.unimol.gameui.gamegui.gameinfopanel.GameInfoPanel;
import it.unimol.gameui.gamegui.gamepanel.GamePanel;
import it.unimol.gameui.gamegui.mainmenupanel.MainMenuPanel;
import it.unimol.gameui.gamemusic.BackgroundMusicHandler;

import javax.swing.JFrame;

/**
 * Class Singleton that implements the Main Window of the game.<br>
 * Call method {@link #getInstance()} to handle it;<br><br>
 *
 * Example: {@code MainFrame.getInstance().setVisible(true)}
 *
 * @author Maurizio Albani
 */
public final class MainFrame extends JFrame {
    private static final MainFrame INSTANCE = new MainFrame();

    public static MainFrame getInstance() {
        return INSTANCE;
    }

    private static GamePanel gamePanel;

    private static DifficultyPanel difficultyPanel;

    private static MainMenuPanel mainMenuPanel;

    private static GameInfoPanel infoPanel;

    private MainFrame() {
        super();
        gamePanel = new GamePanel();
        difficultyPanel = new DifficultyPanel(gamePanel);
        mainMenuPanel = new MainMenuPanel();
        infoPanel = new GameInfoPanel();
        add(gamePanel);
        add(difficultyPanel);
        add(mainMenuPanel);
        add(infoPanel);

        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        showPanel(PanelValue.MAIN_MENU);

        BackgroundMusicHandler.getInstance().start();
    }

    /**
     * Changes the content that is visualized inside the MainWindow according to the parameter passed as Input
     * @param value indicates the panel to set visible (possible values are described in :{@link PanelValue})
     */
    public void showPanel(PanelValue value) {
        switch (value) {
            case MAIN_MENU:
                mainMenuPanel.setVisible(true);
                difficultyPanel.setVisible(false);
                gamePanel.setVisible(false);
                infoPanel.setVisible(false);
                break;
            case SELECT_DIFFICULTY:
                mainMenuPanel.setVisible(false);
                difficultyPanel.setVisible(true);
                gamePanel.setVisible(false);
                infoPanel.setVisible(false);
                break;
            case GAME:
                mainMenuPanel.setVisible(false);
                difficultyPanel.setVisible(false);
                gamePanel.setVisible(true);
                infoPanel.setVisible(false);
                break;
            case INFO:
                mainMenuPanel.setVisible(false);
                difficultyPanel.setVisible(false);
                gamePanel.setVisible(false);
                infoPanel.setVisible(true);
                break;
            default: break;
        }
    }
}
