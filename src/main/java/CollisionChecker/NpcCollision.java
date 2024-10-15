package CollisionChecker;

import Game.*;

import java.awt.*;


public class NpcCollision {

    public final Game game;

    public NpcCollision(Game game) {
        this.game = game;
    }

    public boolean collision(int x, int y) {
        if (x < 0 || x + game.characterSize > game.WIDTH || y < 0 || y + game.characterSize > game.HEIGHT) {
            return true;
        }
        Rectangle npcRect = new Rectangle(x, y, game.characterSize, game.characterSize);
        for (Rectangle obstacles : game.map.obstacles) {
            if (npcRect.intersects(obstacles)) {
                return true;
            }
        }
        return false;
    }
}
