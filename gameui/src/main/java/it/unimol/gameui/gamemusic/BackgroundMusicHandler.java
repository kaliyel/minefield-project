package it.unimol.gameui.gamemusic;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;

/**
 * Class extension of a Thread that implements a Handler for the Audio of the Game
 */
public class BackgroundMusicHandler extends Thread {
    private static final BackgroundMusicHandler instance = new BackgroundMusicHandler();

    public static BackgroundMusicHandler getInstance() {
        return instance;
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