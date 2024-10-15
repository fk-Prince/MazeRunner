package GameStates;

import Game.Game;

import java.awt.*;

public class MenuPanel {

    public final Game game;
    private final String title = "MAZE RUNNER";

    public boolean diffultyMenu = false;
    public int choice = 1;
    public int menuActiveButton = 1;
    public int difficultyButton = 1;
    public int soundButton = 1;
    public boolean settingMenu;

    public float volume = 100;
    public int volumeLength;

    public MenuPanel(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(new Color(22, 29, 39));
        g2d.fillRect(0, 0, game.WIDTH, game.HEIGHT);

        g2d.setFont(game.titleFont);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setColor(Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fm.stringWidth(title)) / 2, game.size * 3);


        drawCredits(g2d);
        if (choice == 1) {
            drawMenu(g2d, 1, 6, "START NEW GAME");
            drawMenu(g2d, 2, 7, "SETTINGS");
            drawMenu(g2d, 3, 9, "EXIT");
        } else if (choice == 2) {
            drawDifficulty(g2d, 1, 6, "EASY");
            drawDifficulty(g2d, 2, 7, "MEDIUM");
            drawDifficulty(g2d, 3, 8, "HARD");
            drawDifficulty(g2d, 4, 10, "BACK");
        } else if (choice == 3) {
            drawSoundSettings(g2d);
        }


    }

    public void drawCredits(Graphics2D g2d) {
        g2d.setFont(game.creditFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString("CREDITS", 10, game.size * 10);
        g2d.drawString("Prince", 10, game.size * 11 - 30);
        g2d.drawString("Jake", 10, game.size * 12 - 70);
    }

    public void drawSoundSettings(Graphics2D g2d) {
        g2d.setFont(game.secondaryFont);
        FontMetrics fmS = g2d.getFontMetrics();


        if (soundButton == 1) {
            g2d.setColor(new Color(221, 178, 178));
            g2d.drawString("<", (game.WIDTH + 250) / 2 + 50, game.size * 6);
            g2d.setStroke(new BasicStroke(20));
            g2d.setColor(new Color(117, 111, 225));
            g2d.drawLine((game.WIDTH - 200) / 2, game.size * 6 - 10, (game.WIDTH + 200) / 2, game.size * 6 - 10);
        }
        g2d.setColor(soundButton == 1 ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString("VOLUME", (game.WIDTH - fmS.stringWidth("BACK")) / 2 - 20, game.size * 6 - 35);
        volumeLength = Math.min((int) volume * 2, 200);
        game.pause.volume = volume;
        g2d.setColor(soundButton == 1 ? new Color(221, 178, 178) : Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine((game.WIDTH - 200) / 2, game.size * 6 - 10, (game.WIDTH - 200) / 2 + volumeLength, game.size * 6 - 10);


        g2d.setColor(soundButton == 2 ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString("BACK", (game.WIDTH - fmS.stringWidth("BACK")) / 2, game.size * 9);
        if (soundButton == 2) {
            g2d.drawString("<", (game.WIDTH - fmS.stringWidth("BACK")) / 2 + fmS.stringWidth("BACK") + 10, game.size * 9);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine((game.WIDTH - fmS.stringWidth("BACK")) / 2, game.size * 9 + 10,
                    (game.WIDTH - fmS.stringWidth("BACK")) / 2 + fmS.stringWidth("BACK") - 10, game.size * 9 + 10);
        }
    }


    public void drawMenu(Graphics2D g2d, int level, int y, String title) {
        g2d.setFont(game.secondaryFont);
        FontMetrics fmS = g2d.getFontMetrics();
        g2d.setColor(menuActiveButton == level ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y);
        if (menuActiveButton == level) {
            g2d.drawString("<", (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) + 10, game.size * y);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine((game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y + 10,
                    (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) - 10, game.size * y + 10);
        }
    }

    public void drawDifficulty(Graphics2D g2d, int level, int y, String title) {
        g2d.setFont(game.secondaryFont);
        FontMetrics fmS = g2d.getFontMetrics();
        g2d.setColor(difficultyButton == level ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y);
        if (difficultyButton == level) {
            g2d.drawString("<", (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) + 10, game.size * y);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine((game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y + 10,
                    (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) - 10, game.size * y + 10);
        }
    }


}
