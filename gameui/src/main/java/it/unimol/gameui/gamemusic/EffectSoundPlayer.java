package it.unimol.gameui.gamemusic;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class EffectSoundPlayer extends Thread{
    private final URL effectSoundURL;

    public EffectSoundPlayer(String effectPath) {
        this.effectSoundURL = getClass().getResource(effectPath);
        this.start();
    }

    public void run() {
        this.play();
    }

    private void play(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.effectSoundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}