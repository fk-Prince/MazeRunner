package Players;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.*;

public class BatNpc {

    private final Game game;
    private final Random random;
    private final Timer timer;

    public Rectangle batRect;
    private BufferedImage bat1;
    private BufferedImage bat2;

    private int npcDirection;
    private int counter = 0;
    public int batX, batY;
    private int walkingCount = 0;

    public BatNpc(Game game, int x, int y) {
        random = new Random();
        this.game = game;
        this.batX = x;
        this.batY = y;
        timer = new Timer(5000, evt -> npcDirection = random.nextInt(4));
        timer.start();
        timer.setDelay(0);
        timer.setRepeats(false);
        batRect = new Rectangle(batX, batY, game.characterSize - 20, game.characterSize - 20);
        loadImage();
    }

    private void loadImage() {
        try {
            bat1 = ImageIO.read(getClass().getResourceAsStream("/Image/Npc/npcbat1.png"));
            bat2 = ImageIO.read(getClass().getResourceAsStream("/Image/Npc/npcbat2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void drawBat(Graphics2D g2d) {
        int x = batX;
        int y = batY;

        if (counter >= 1000) {
            if (!timer.isRunning()) {
                timer.start();
            }

            switch (npcDirection) {
                case 0 -> batY -= 5;
                case 1 -> batY += 5;
                case 2 -> batX -= 5;
                case 3 -> batX += 5;
            }
            counter = 0;
        }
        BufferedImage batImage = walkingCount < 1000 ? bat1 : bat2;
        counter++;
        if (!col(batX, batY)) {
            g2d.drawImage(batImage, batX, batY, game.characterSize, game.characterSize, null);
        } else {
            npcDirection = random.nextInt(4);
            counter = 0;
            batY = y;
            batX = x;
        }
        batRect.setRect(batX, batY, game.characterSize - 30, game.characterSize - 30);
        walking();
    }

    public boolean col(int x, int y) {
        return x < 0 || x + game.characterSize > game.WIDTH || y < 0 || y + game.characterSize > game.HEIGHT;
    }


    public void batFog() {
        int x = batX;
        int y = batY;

        if (counter >= 1000) {
            if (!timer.isRunning()) {
                timer.start();
            }

            switch (npcDirection) {
                case 0 -> batY -= 5;
                case 1 -> batY += 5;
                case 2 -> batX -= 5;
                case 3 -> batX += 5;
            }
            counter = 0;
        }

        counter++;
        if (col(batX, batY)) {
            npcDirection = random.nextInt(4);
            counter = 0;
            batY = y;
            batX = x;
        }
        walking();
    }

    public void walking() {
        if (walkingCount < 2000) {
            walkingCount++;
        } else {
            walkingCount = 0;
        }
    }

}