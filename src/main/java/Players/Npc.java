package Players;

import Game.Game;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

public class Npc {
    private BufferedImage npcRight, npcLeft, npcUp, npcDown, npcImage;
    private final Game game;
    public Rectangle npcRect;

    public int npcX;
    public int npcY;
    public boolean rightDirection, bottomDirection;
    public boolean isRight;
    private int counter;
    private int walkingCount = 0;

    public Npc(Game game, int x, int y, boolean right) {
        this.game = game;
        this.npcX = x;
        this.npcY = y;
        this.isRight = right;
        npcRect = new Rectangle(npcX, npcY, game.characterSize, game.characterSize);
        loadImage();
    }

    private void loadImage() {
        try {
            npcRight = ImageIO.read(getClass().getResourceAsStream("/Image/Npc/npcright1.png"));
            npcLeft = ImageIO.read(getClass().getResourceAsStream("/Image/Npc/npcleft1.png"));
            npcUp = ImageIO.read(getClass().getResourceAsStream("/Image/Npc/npcup1.png"));
            npcDown = ImageIO.read(getClass().getResourceAsStream("/Image/Npc/npcdown1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawNpc(Graphics2D g2d) {
        if (isRight) {
            npcCollisionX(g2d);
        } else {
            npcCollisionY(g2d);
        }
    }

    private void npcCollisionY(Graphics2D g2d) {
        int oldY = npcY;

        if (counter >= 1000) {
            npcY += bottomDirection ? 5 : -5;
            counter = 0;
        }
        counter++;


        if (!game.npcCollision.collision(npcX, npcY)) {
            g2d.drawImage(npcImage, npcX, npcY, game.characterSize, game.characterSize, null);
        } else {
            bottomDirection = !bottomDirection;
            npcY = oldY;
        }
        npcRect.setRect(npcX, npcY, game.characterSize, game.characterSize);
        npcImage = bottomDirection ? npcDown : npcUp;
        walking();
    }

    public void npcCollisionX(Graphics2D g2d) {
        int oldX = npcX;

        if (counter >= 1000) {
            npcX += rightDirection ? 5 : -5;
            counter = 0;
        }
        counter++;


        if (!game.npcCollision.collision(npcX, npcY)) {
            g2d.drawImage(npcImage, npcX, npcY, game.characterSize, game.characterSize, null);
        } else {
            rightDirection = !rightDirection;
            npcX = oldX;
        }
        npcRect.setRect(npcX, npcY, game.characterSize, game.characterSize);
        npcImage = rightDirection ? npcRight : npcLeft;
        walking();
    }

    public void walking() {
        if (walkingCount < 10) {
            walkingCount++;
        } else {
            walkingCount = 0;
        }
    }

    public void fogX() {
        int oldX = npcX;
        if (counter >= 1000) {
            npcX += rightDirection ? 5 : -5;
            counter = 0;
        }
        counter++;
        npcRect = new Rectangle(npcX, npcY, game.characterSize, game.characterSize);

        if (game.npcCollision.collision(npcX, npcY)) {
            rightDirection = !rightDirection;
            npcX = oldX;
            counter = 1000;
        }

        npcImage = rightDirection ? npcRight : npcLeft;
        walking();
    }

    public void fogY() {
        int oldY = npcY;
        if (counter >= 1000) {
            npcY += bottomDirection ? 5 : -5;
            counter = 0;
        }
        counter++;
        npcRect = new Rectangle(npcX, npcY, game.characterSize, game.characterSize);

        if (game.npcCollision.collision(npcX, npcY)) {
            bottomDirection = !bottomDirection;
            npcY = oldY;
            counter = 1000;
        }


        npcImage = bottomDirection ? npcDown : npcUp;
        walking();
    }
}
