package Sound;

import Game.Game;

import javax.sound.sampled.*;
import java.io.*;

public class Music {
    public Clip menuMusic, gameOverMusic, gameMusic, newMapMusic;
    public FloatControl menuFc, gameOverFc, gameFc, newMapFc;
    public float currentVolume = 6.0f;
    private final Game game;

    public Music(Game game) {
        this.game = game;
        menuMusic();
        gameMusic();
        gameOverMusic();
        newMapMusic();
    }

    public void menuMusic() {
        try {
            if (menuMusic != null && menuMusic.isRunning()) {
                menuMusic.stop();
            }
            InputStream is = new BufferedInputStream(getClass().getResourceAsStream("/Music/menumusic.wav"));
            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
            menuMusic = AudioSystem.getClip();
            menuMusic.open(ais);
            menuMusic.start();
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
            menuFc = (FloatControl) menuMusic.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameMusic() {
        try {
            if (gameMusic != null && gameMusic.isRunning()) {
                gameMusic.stop();
            }
            InputStream is1 = new BufferedInputStream(getClass().getResourceAsStream("/Music/gamemusic.wav"));
            AudioInputStream ais = AudioSystem.getAudioInputStream(is1);
            gameMusic = AudioSystem.getClip();
            gameMusic.open(ais);
            gameFc = (FloatControl) gameMusic.getControl(FloatControl.Type.MASTER_GAIN);
//            gameFc.setValue(menuFc.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newMapMusic() {
        try {
            if (newMapMusic != null && newMapMusic.isRunning()) {
                newMapMusic.stop();
            }
            InputStream is3 = new BufferedInputStream(getClass().getResourceAsStream("/Music/newmap.wav"));
            AudioInputStream ais = AudioSystem.getAudioInputStream(is3);
            newMapMusic = AudioSystem.getClip();
            newMapMusic.open(ais);
            newMapFc = (FloatControl) newMapMusic.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameOverMusic() {
        try {
            if (gameOverMusic != null && gameOverMusic.isRunning()) {
                gameOverMusic.stop();
            }
            InputStream is2 = new BufferedInputStream(getClass().getResourceAsStream("/Music/gameover.wav"));
            AudioInputStream ais1 = AudioSystem.getAudioInputStream(is2);
            gameOverMusic = AudioSystem.getClip();
            gameOverMusic.open(ais1);
            gameOverFc = (FloatControl) gameOverMusic.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startNewMapMusic() {
        if (!newMapMusic.isRunning()) {
            newMapMusic.loop(Clip.LOOP_CONTINUOUSLY);
            newMapMusic.start();
        }
    }

    public void stopNewMapMusic() {
        if (newMapMusic != null) {
            newMapMusic.stop();
        }
    }

    public void startGameOverMusic() {
        if (!gameOverMusic.isRunning()) {
            gameOverMusic.start();
        }
    }

    public void stopGameOverMusic() {
        if (gameOverMusic != null) {
            gameOverMusic.stop();
        }
    }

    public void startMenuMusic() {
        if (!menuMusic.isRunning()) {
            menuMusic.start();
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopMenuMusic() {
        if (menuMusic != null) {
            menuMusic.stop();
        }
    }

    public void stopGameMusic() {
        if (gameMusic != null) {
            gameMusic.stop();
        }
    }

    public void startGameMusic() {
        if (!gameMusic.isRunning()) {
            gameMusic.start();
            gameMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }


    public void volumeUp() {
        currentVolume += 1f;
        if (currentVolume > 6.0f) currentVolume = 6.0f;


        if (game.menu.volume >= 20 && game.menu.volume <= 35) currentVolume = -15.f;
        if (game.menu.volume >= 45 && game.menu.volume <= 60) currentVolume = -10.0f;
        if (game.menu.volume >= 60 && game.menu.volume <= 75) currentVolume = 0f;
        if (game.menu.volume > 80 && game.menu.volume < 90) currentVolume = 2.0f;
        if (game.menu.volume > 95) currentVolume = 6.0f;


        menuFc.setValue(currentVolume);
        newMapFc.setValue(currentVolume);
        gameOverFc.setValue(currentVolume);
        gameFc.setValue(currentVolume);
    }

    public void volumeDown() {
        currentVolume -= 0.3f;
        if (currentVolume < -80.0f) currentVolume = -80.0f;

        if (game.menu.volume >= 86 && game.menu.volume <= 100) currentVolume = 6.0f;
        if (game.menu.volume >= 70 && game.menu.volume <= 85) currentVolume = 0f;
        if (game.menu.volume >= 45 && game.menu.volume <= 60) currentVolume = -10.0f;
        if (game.menu.volume >= 20 && game.menu.volume <= 35) currentVolume = -15.f;
        if (game.menu.volume >= 10 && game.menu.volume <= 20) currentVolume = -30.f;
        if (game.menu.volume < 2) currentVolume = -80.0f;


        menuFc.setValue(currentVolume);
        newMapFc.setValue(currentVolume);
        gameOverFc.setValue(currentVolume);
        gameFc.setValue(currentVolume);
    }
}
