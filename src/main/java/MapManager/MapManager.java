package MapManager;

import Game.Game;
import Players.BatNpc;
import Players.Npc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapManager {
    public final Game game;
    public int mapDiffuculty = 1;
    public int stage = 1;
    public String mapDifStr;
    private final MapTile[] tile;
    private final int[][] map;

    public final CopyOnWriteArrayList<Rectangle> obstacles;
    private final CopyOnWriteArrayList<Npc> npc;
    private final CopyOnWriteArrayList<BatNpc> batNpc;
    private final CopyOnWriteArrayList<Rectangle> npcR;
    private final CopyOnWriteArrayList<Rectangle> batR;
    public final CopyOnWriteArrayList<Rectangle> endTileRect;


    public MapManager(Game game) {
        this.game = game;
        endTileRect = new CopyOnWriteArrayList<>();
        npc = new CopyOnWriteArrayList<>();
        batNpc = new CopyOnWriteArrayList<>();
        npcR = new CopyOnWriteArrayList<>();
        batR = new CopyOnWriteArrayList<>();
        map = new int[game.maxColumn][game.maxRow];
        tile = new MapTile[10];
        obstacles = new CopyOnWriteArrayList<>();
        loadImage();
        loadTile();
    }

    public void loadTile() {

        try {
            if (obstacles != null) {
                obstacles.clear();
            }
            if (npc != null) {
                npc.clear();
            }
            if (batNpc != null) {
                batNpc.clear();
            }
            BufferedReader br = null;
            if (mapDiffuculty == 1) {
                mapDifStr = "EASY";
                InputStream is = getClass().getResourceAsStream("/MapEasy/Map0" + stage + "E.txt");
                br = new BufferedReader(new InputStreamReader(is));
            }
            if (mapDiffuculty == 2) {
                mapDifStr = "MEDIUM";
                InputStream is = getClass().getResourceAsStream("/MapMedium/Map0" + stage + "M.txt");
                br = new BufferedReader(new InputStreamReader(is));
                if (stage == 1) {
                    npc.add(new Npc(game, game.size * 5, game.size * 4 + 10, true));
                    npc.add(new Npc(game, game.size * 5, game.size * 9 + 10, true));
                    npc.add(new Npc(game, game.size * 22 + 5, game.size * 9 + 10, false));
                    npc.add(new Npc(game, game.size * 20 + 5, game.size * 11 + 10, false));
                    npc.add(new Npc(game, game.size * 20 + 5, game.size * 2 + 10, false));
                }
                if (stage == 2) {
                    npc.add(new Npc(game, game.size * 5, game.size + 10, true));
                    npc.add(new Npc(game, game.size * 5, game.size * 8 + 10, true));
                    npc.add(new Npc(game, game.size * 22 + 5, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 20 + 5, game.size * 10 + 10, false));
                    npc.add(new Npc(game, game.size * 17 + 5, game.size * 10 + 10, false));
                }
                if (stage == 3) {
                    npc.add(new Npc(game, game.size * 14, 10, true));
                    npc.add(new Npc(game, 5, game.size * 5, false));
                    npc.add(new Npc(game, game.size * 14, game.size * 10 + 10, true));
                    npc.add(new Npc(game, game.size * 15 + 10, game.size * 3 + 10, true));
                    npc.add(new Npc(game, game.size * 5, game.size * 11 + 10, true));
                }
                if (stage == 4) {
                    npc.add(new Npc(game, game.size * 5, game.size * 11 + 10, true));
                    npc.add(new Npc(game, 5, game.size * 5, false));
                    npc.add(new Npc(game, game.size * 12 + 5, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 17, game.size * 11 + 10, true));
                    npc.add(new Npc(game, game.size * 19, 10, true));
                }
                if (stage == 5) {
                    npc.add(new Npc(game, game.size * 5, game.size * 3 + 10, true));
                    npc.add(new Npc(game, game.size * 7, game.size * 10 + 10, true));
                    npc.add(new Npc(game, game.size * 15 + 10, game.size * 2, false));
                    npc.add(new Npc(game, game.size * 15, game.size * 2 + 10, true));
                    npc.add(new Npc(game, game.size * 20, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 20, game.size * 12 + 10, true));
                }
            }
            if (mapDiffuculty == 3) {
                InputStream is = getClass().getResourceAsStream("/MapHard/Map0" + stage + "H.txt");
                br = new BufferedReader(new InputStreamReader(is));
                mapDifStr = "HARD";
                if (stage == 1) {
                    npc.add(new Npc(game, game.size * 2, game.size * 4 + 10, true));
                    npc.add(new Npc(game, game.size * 7, game.size * 11 + 10, true));
                    npc.add(new Npc(game, game.size * 18, game.size * 7 + 10, true));
                    npc.add(new Npc(game, game.size * 22 + 5, game.size * 7 + 10, false));
                    npc.add(new Npc(game, game.size * 9, game.size * 7 + 10, true));
                    npc.add(new Npc(game, game.size * 18, game.size * 3 + 10, true));
                    batNpc.add(new BatNpc(game, game.size * 19, game.size * 2 + 10));
                    batNpc.add(new BatNpc(game, game.size * 10, game.size * 5 + 10));
                    batNpc.add(new BatNpc(game, game.size * 2, game.size * 10 + 10));
                    batNpc.add(new BatNpc(game, game.size * 16, game.size * 13 + 10));
                }
                if (stage == 2) {
                    game.playerY = game.size * 10;
                    npc.add(new Npc(game, 5, game.size * 4 + 10, false));
                    npc.add(new Npc(game, 5, game.size * 12 + 10, true));
                    npc.add(new Npc(game, game.size * 8, game.size * 8 + 10, true));
                    npc.add(new Npc(game, game.size * 12, game.size * 5 + 10, true));
                    npc.add(new Npc(game, game.size * 19 + 5, game.size * 8 + 10, false));
                    npc.add(new Npc(game, game.size * 16, game.size * 11 + 10, true));
                    npc.add(new Npc(game, game.size * 20, game.size * 3 + 10, true));
                    npc.add(new Npc(game, game.size * 20, game.size * 9 + 10, false));
                    batNpc.add(new BatNpc(game, game.size * 5, game.size * 3 + 10));
                    batNpc.add(new BatNpc(game, game.size * 15, game.size * 10 + 10));
                    batNpc.add(new BatNpc(game, game.size * 9, game.size * 9 + 10));
                    batNpc.add(new BatNpc(game, game.size * 16, game.size * 13 + 10));
                    batNpc.add(new BatNpc(game, game.size * 16, game.size * 3 + 10));
                }
                if (stage == 3) {
                    game.playerX = game.size * 3 + 10;
                    game.playerY = game.size * 10 + 10;
                    npc.add(new Npc(game, game.size + 10, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 7, game.size * 12 + 10, true));
                    npc.add(new Npc(game, game.size * 15, game.size * 6 + 10, true));
                    npc.add(new Npc(game, game.size * 17, game.size * 12 + 10, true));
                    npc.add(new Npc(game, game.size * 13, 0, true));
                    npc.add(new Npc(game, game.size * 22 + 5, game.size * 9 + 10, false));
                    npc.add(new Npc(game, game.size * 20 + 5, game.size * 10 + 10, false));
                    npc.add(new Npc(game, game.size * 19, game.size * 4 + 10, true));
                    npc.add(new Npc(game, game.size * 11, game.size * 2 + 10, true));
                    batNpc.add(new BatNpc(game, game.size * 5, game.size * 3 + 10));
                    batNpc.add(new BatNpc(game, game.size * 15, game.size * 10 + 10));
                    batNpc.add(new BatNpc(game, game.size * 9, game.size * 9 + 10));
                    batNpc.add(new BatNpc(game, game.size * 16, game.size * 13 + 10));
                    batNpc.add(new BatNpc(game, game.size * 16, game.size * 3 + 10));
                }
                if (stage == 4) {
                    game.playerX = game.size * 2 + 5;
                    game.playerY = game.size * 12 + 10;
                    npc.add(new Npc(game, game.size + 10, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 4 + 5, game.size * 9 + 10, false));
                    npc.add(new Npc(game, game.size * 4, game.size * 9 + 10, true));
                    npc.add(new Npc(game, game.size * 5, game.size * 12 + 10, true));
                    npc.add(new Npc(game, game.size * 10, game.size * 2 + 10, true));
                    npc.add(new Npc(game, game.size * 8, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 22+5, game.size * 9 + 10, false));
                    npc.add(new Npc(game, game.size * 20+5, game.size * 5 + 10, false));
                    npc.add(new Npc(game, game.size * 16, game.size * 9 + 10, false));
                    npc.add(new Npc(game, game.size * 16, game.size * 9 + 10, false));
                    npc.add(new Npc(game, game.size * 15, game.size * 11 + 10, true));
                }
                if (stage == 5) {
                    game.playerX = game.size * 10 + 5;
                    game.playerY = game.size * 8 + 10;
                    npc.add(new Npc(game, game.size * 3, game.size * 8 + 10, true));
                    npc.add(new Npc(game, game.size * 8, game.size * 10 + 10, true));
                    npc.add(new Npc(game, game.size * 4, game.size * 7 + 10, true));
                    npc.add(new Npc(game, game.size * 3, 10, true));
                    npc.add(new Npc(game, game.size * 3, game.size * 6 + 10, true));
                    npc.add(new Npc(game, game.size * 12, game.size * 9 + 10, true));
                    npc.add(new Npc(game, game.size * 17 + 5, game.size * 8 + 10, false));
                    npc.add(new Npc(game, game.size * 22 + 5, game.size * 8 + 10, false));
                    npc.add(new Npc(game, game.size * 3 + 5, game.size * 2 + 10, false));
                    npc.add(new Npc(game, game.size * 17 + 5, game.size * 12 + 10, true));
                    npc.add(new Npc(game, game.size * 17 + 5, game.size * 8 + 10, false));
                    npc.add(new Npc(game, game.size * 13 + 5, game.size * 6 + 10, false));
                }

            }

            String line;
            int row = 0;

            while (row < game.maxRow && (line = br.readLine()) != null) {
                String[] mapCol = line.split(" ");
                for (int col = 0; col < game.maxColumn && col < mapCol.length; col++) {
                    map[col][row] = Integer.parseInt(mapCol[col]);

                }
                row++;

            }
            br.close();
        } catch (Exception e) {

        }
    }

    private void loadImage() {
        try {
            tile[0] = new MapTile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Image/Blocks/sand.png"));
            tile[1] = new MapTile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Image/Blocks/wall.png"));
            tile[3] = new MapTile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Image/Blocks/start.png"));
            tile[4] = new MapTile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Image/Blocks/end.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void drawMap(Graphics2D g2d) {
        if (obstacles.size() >= game.maxColumn * game.maxColumn) {
            obstacles.clear();
            npcR.clear();
            batR.clear();
            endTileRect.clear();
        }

        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;

        while (col < game.maxColumn && row < game.maxRow) {
            int b = map[col][row];
            Rectangle tileRect = new Rectangle(x, y, game.size, game.size);
            Rectangle2D playerRectangle = new Rectangle2D.Double(
                    game.playerX - 100, game.playerY - 100,
                    100 * 2 + game.characterSize, 100 * 2 + game.characterSize
            );

            if (map[col][row] == 4) {
                endTileRect.add(new Rectangle(col * game.size, row * game.size, game.size, game.size));
            }

            if (b == 1) {
                obstacles.add(tileRect);
            }

            if (playerRectangle.intersects(tileRect)) {
                g2d.drawImage(tile[b].image, x, y, game.size, game.size, null);
            }

            for (Npc npc : this.npc) {
                if (playerRectangle.intersects(npc.npcRect)) {
                    npc.drawNpc(g2d);
                    npcR.add(npc.npcRect);
                } else {
                    if (npc.isRight) {
                        npc.fogX();
                    } else {
                        npc.fogY();
                    }
                }
                Rectangle playerRectSolid = new Rectangle(game.playerX, game.playerY, game.characterSize, game.characterSize);
                if (playerRectSolid.intersects(npc.npcRect)) {
                    game.deadState = true;
                    game.music.stopGameMusic();
                    game.music.startGameOverMusic();
                }
            }

            for (BatNpc batNpc : this.batNpc) {
                if (playerRectangle.intersects(batNpc.batRect)) {
                    batNpc.drawBat(g2d);
                    batR.add(batNpc.batRect);
                } else {
                    batNpc.batFog();
                }
                Rectangle playerRectSolid = new Rectangle(game.playerX, game.playerY, game.characterSize, game.characterSize);
                if (playerRectSolid.intersects(batNpc.batRect)) {
                    game.music.stopGameMusic();
                    game.music.startGameOverMusic();
                    game.deadState = true;

                }
            }


            col++;
            x += game.size;
            if (col == game.maxColumn) {
                col = 0;
                row++;
                x = 0;
                y += game.size;
            }
        }
    }


}
