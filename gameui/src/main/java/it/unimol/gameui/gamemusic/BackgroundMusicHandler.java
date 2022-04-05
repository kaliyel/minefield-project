package it.unimol.gameui.gamemusic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * Class extension of a Thread that implements a Handler for the Audio of the Game
 */
public final class BackgroundMusicHandler extends Thread {
    private static final BackgroundMusicHandler INSTANCE = new BackgroundMusicHandler();

    public static BackgroundMusicHandler getInstance() {
        return INSTANCE;
    }

    private BackgroundMusicHandler() {
        super();
    }

    /**
     * Starts the thread and the background music of the Game
     */
    @Override
    public void run() {
        super.run();
        play();
    }

    private void play() {
        String backgroundMusicPath = "/backgroundmusic.wav";
        URL musicFileURL = getClass().getResource(backgroundMusicPath);
        try {
            AudioInputStream audioInputStream = null;
            if (musicFileURL != null) {
                audioInputStream = AudioSystem.getAudioInputStream(musicFileURL);
            }
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
