package Game;

import javax.swing.*;
import java.awt.*;

public class DrawText {
    private final Game game;
    public int continueButton = 1;

    public DrawText(Game game) {
        this.game = game;
    }

    public void drawCongratulations(Graphics2D g2d) {
        FontMetrics fm = g2d.getFontMetrics(game.titleFont);
        g2d.setFont(game.titleFont);
        g2d.setColor(Color.WHITE);
        String title = "DIFFICULT " + game.map.mapDifStr + " COMPLETED";
        g2d.drawString(title, (game.WIDTH - fm.stringWidth(title)) / 2, game.size * 4);

        drawTextButton(g2d, 1, 7, "NEXT DIFFICULTY");
        drawTextButton(g2d, 2, 8, "BACK TO MENU");
        drawTextButton(g2d, 3, 10, "QUIT");
    }

    private void drawTextButton(Graphics2D g2d, int level, int y, String title) {
        g2d.setFont(game.secondaryFont);
        FontMetrics fmS = g2d.getFontMetrics();
        g2d.setColor(continueButton == level ? new Color(221, 178, 178) : Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y);
        if (continueButton == level) {
            g2d.drawString("<", (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) + 10, game.size * y);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine((game.WIDTH - fmS.stringWidth(title)) / 2, game.size * y + 10,
                    (game.WIDTH - fmS.stringWidth(title)) / 2 + fmS.stringWidth(title) - 10, game.size * y + 10);
        }
    }

    public void drawNextStageText(Graphics2D g2d) {
        String title = "STAGE " + game.map.stage;
        String dif = "DIFFICULTY - " + game.map.mapDifStr;
        Timer timer = new Timer(2000, _ -> game.mapId = false);
        timer.setRepeats(false);
        timer.start();
        FontMetrics fm = g2d.getFontMetrics(game.titleFont);
        g2d.setFont(game.titleFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fm.stringWidth(title)) - 100, game.size * 2);
        FontMetrics fms = g2d.getFontMetrics(game.secondaryFont);
        g2d.setFont(game.secondaryFont);
        g2d.drawString(dif, (game.WIDTH - fms.stringWidth(dif)) - 100, game.size * 3);
    }

    public void draw(Graphics2D g2d) {
        String title = "CONGRATULATIONS ";
        String title2 = "YOU BEAT THE MAZE RUNNER";
        String title3 = "THANK YOU FOR PLAYER OUR GAME";
        FontMetrics fm = g2d.getFontMetrics(game.titleFont);
        g2d.setFont(game.titleFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(title, (game.WIDTH - fm.stringWidth(title)) / 2, game.size * 4);
        g2d.drawString(title2, (game.WIDTH - fm.stringWidth(title2)) / 2, game.size * 5);

        FontMetrics fms = g2d.getFontMetrics(game.secondaryFont);
        g2d.setFont(game.secondaryFont);
        g2d.drawString(title3, (game.WIDTH - fms.stringWidth(title3)) / 2, game.size * 7);
        String credit = "CREDITS";
        g2d.drawString(credit, (game.WIDTH - fms.stringWidth(credit)) / 2, game.size * 9);

        String credit1 = "PRINCE";
        String credit2 = "JAKE";
        FontMetrics fmx = g2d.getFontMetrics(game.creditFont);
        g2d.setFont(game.creditFont);
        g2d.drawString(credit1, (game.WIDTH - fmx.stringWidth(credit1)) / 2, game.size * 10);
        g2d.drawString(credit2, (game.WIDTH - fmx.stringWidth(credit2)) / 2, game.size * 10 + 25);

        Timer timer = new Timer(10000, evt -> System.exit(0));
        timer.start();
        timer.setRepeats(false);
        timer.setDelay(0);
    }
}
