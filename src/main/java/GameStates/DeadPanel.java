package GameStates;

import Game.Game;

import java.awt.*;

public class DeadPanel {
    private final Game game;
    public int deadChoice = 1;

    public DeadPanel(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g2d) {
        FontMetrics fm = g2d.getFontMetrics(game.titleFont);
        g2d.setColor(Color.RED);
        g2d.setFont(game.titleFont);
        g2d.drawString("YOU DIED", (game.WIDTH - fm.stringWidth("YOU DIED")) / 2, game.size * 3 + 20);

        drawChoices(g2d, 1, 6, "BACK TO STAGE 1");
        drawChoices(g2d, 2, 7, "BACK TO MENU");
        drawChoices(g2d, 3, 9, "QUIT");
    }

    private void drawChoices(Graphics2D g2d, int level, int y, String title) {
        FontMetrics fmS = g2d.getFontMetrics(game.secondaryFont);
        g2d.setFont(game.secondaryFont);
        g2d.setColor(deadChoice == level ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y - 35);
        if (deadChoice == level) {
            g2d.setColor(new Color(221, 178, 178));
            g2d.drawString("<", (game.WIDTH + fmS.stringWidth(title)) / 2 + 50, game.size * y - 35);
        }
    }
}
