package GameStates;

import Game.Game;

import java.awt.*;

public class PausePanel {
    private final Game game;
    public int resumeButton = 1;
    public int soundSettingPauseButton = 1;
    public float volume = 100;
    public int pauseChoice = 1;
    public int volumeLength;

    public PausePanel(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(22, 29, 39));
        g2d.fillRect(0, 0, game.WIDTH, game.HEIGHT);

        g2d.setFont(game.titleFont);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setColor(Color.WHITE);
        g2d.drawString("GAME IS PAUSE", (game.WIDTH - fm.stringWidth("GAME IS PAUSE")) / 2, game.size * 3);
        if (pauseChoice == 1) {
            drawPauseMenus(g2d, 1, 5, "RESUME");
            drawPauseMenus(g2d, 2, 6, "BACK TO MENU");
            drawPauseMenus(g2d, 3, 7, "SETTINGS");
            //game.menu.drawSoundSettings(g2d);
            drawPauseMenus(g2d, 4, 10, "EXIT");
        } else if (pauseChoice == 2) {
            drawSoundSettingPause(g2d);
        }
    }


    public void drawPauseMenus(Graphics2D g2d, int level, int y, String title) {
        g2d.setFont(game.secondaryFont);
        FontMetrics fmS = g2d.getFontMetrics();
        g2d.setColor(resumeButton == level ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y);
        if (resumeButton == level) {
            g2d.drawString("<", (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) + 10, game.size * y);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine((game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y + 10,
                    (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) - 10, game.size * y + 10);
        }
    }

    public void drawSoundSettingPause(Graphics2D g2d) {
        g2d.setFont(game.secondaryFont);
        FontMetrics fmS = g2d.getFontMetrics();


        if (soundSettingPauseButton == 1) {
            g2d.setColor(new Color(221, 178, 178));
            g2d.drawString("<", (game.WIDTH + 250) / 2 + 50, game.size * 7);
            g2d.setStroke(new BasicStroke(20));
            g2d.setColor(new Color(117, 111, 225));
            g2d.drawLine((game.WIDTH - 200) / 2, game.size * 7 - 10, (game.WIDTH + 200) / 2, game.size * 7 - 10);
        }
        g2d.setColor(soundSettingPauseButton == 1 ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString("VOLUME", (game.WIDTH - fmS.stringWidth("BACK")) / 2 - 20, game.size * 7 - 35);
        volumeLength = Math.min((int) volume * 2, 200);
        game.menu.volume = volume;
        g2d.setColor(soundSettingPauseButton == 1 ? new Color(221, 178, 178) : Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine((game.WIDTH - 200) / 2, game.size * 7 - 10, (game.WIDTH - 200) / 2 + volumeLength, game.size * 7 - 10);


        g2d.setColor(soundSettingPauseButton == 2 ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString("BACK", (game.WIDTH - fmS.stringWidth("BACK")) / 2, game.size * 9);
        if (soundSettingPauseButton == 2) {
            g2d.drawString("<", (game.WIDTH - fmS.stringWidth("BACK")) / 2 + fmS.stringWidth("BACK") + 10, game.size * 9);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine((game.WIDTH - fmS.stringWidth("BACK")) / 2, game.size * 9 + 10,
                    (game.WIDTH - fmS.stringWidth("BACK")) / 2 + fmS.stringWidth("BACK") - 10, game.size * 9 + 10);
        }
    }
}
