package CollisionChecker;


import Game.Game;

import java.awt.*;

public class PlayerCollision {

    public Game game;

    public PlayerCollision(Game game) {
        this.game = game;
    }

    public boolean playerCollision(int x, int y) {
        if (x < 0 || x + game.characterSize > game.WIDTH || y < 0 || y + game.characterSize > game.HEIGHT) {
            return true;
        }

        Rectangle playerRect = new Rectangle(x, y, game.characterSize, game.characterSize);
        try {
            for (Rectangle obstacles : game.map.obstacles) {
                if (obstacles != null && playerRect.intersects(obstacles)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }


        return false;
    }

    public boolean playerCollisionEndTile(int x, int y) {
        Rectangle playerRect = new Rectangle(x, y, game.characterSize, game.characterSize);

        try {
            for (Rectangle endTileRect : game.map.endTileRect) {
                if (playerRect.intersects(endTileRect)) {
                    game.currentMapBeat = true;
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
