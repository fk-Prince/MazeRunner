package Game;

import CollisionChecker.NpcCollision;
import CollisionChecker.PlayerCollision;
import GameStates.DeadPanel;
import GameStates.MenuPanel;
import GameStates.PausePanel;
import KeyHandler.Key;
import MapManager.*;
import MyFont.*;
import Players.MainPlayer;
import Sound.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {

    private Thread thread;

    //MAP ENTITY
    public final int maxColumn = 23;
    public final int maxRow = 13;
    public final Dimension screenSize = getToolkit().getScreenSize();
    public final int scale = (int) Math.min(screenSize.getWidth() / maxColumn, screenSize.getHeight() / maxRow);
    public final int size = scale;
    public final int WIDTH = maxColumn * size;
    public final int HEIGHT = maxRow * size;

    //PLAYER ENTITY
    public int walking = 0;
    public int playerX = 3;
    public int playerY = 0;
    public final int playerMovement = screenSize.getWidth() == 1920 ? 6 : 4;
    public final int characterSize = (int) ((size * 0.8) - 5);
    public BufferedImage playerImage;

    //IMPORTS
    public MyFont font;
    public Font titleFont, secondaryFont, creditFont;
    public Key key;
    public MainPlayer player;
    public PlayerCollision playerCollision;
    public NpcCollision npcCollision;
    public MapManager map;
    public Music music;
    public DrawText text;


    //GAME STATES
    public boolean menuState = true;
    public MenuPanel menu;
    public boolean pauseState = false;
    public PausePanel pause;
    public boolean deadState = false;
    public DeadPanel dead;
    public boolean mapId = false;
    public boolean difficultCompleted = false;
    public boolean gameBeat = false;
    public boolean currentMapBeat = false;


    public Game() {
        font = new MyFont(this);
        key = new Key(this);
        music = new Music(this);
        playerCollision = new PlayerCollision(this);
        npcCollision = new NpcCollision(this);
        menu = new MenuPanel(this);
        player = new MainPlayer();
        dead = new DeadPanel(this);
        map = new MapManager(this);
        pause = new PausePanel(this);
        text = new DrawText(this);
        playerImage = player.playerDown1;

        addKeyListener(key);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void restart() {
        playerX = 3;
        playerY = 0;
        walking = 0;
    }


    public void startGame() {
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        double draw = 1_000_000_000 / 60;
        long lastTime = System.nanoTime();
        double delta = 0;


        while (thread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / draw;
            lastTime = System.nanoTime();

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    private void update() {
        int X = playerX;
        int Y = playerY;
        try {

            if (playerCollision.playerCollisionEndTile(playerX, playerY)) {
                if (currentMapBeat) {
                    if (map.stage == 5) {
                        music.stopNewMapMusic();
                        music.stopGameMusic();
                        music.startMenuMusic();
                        menuState = false;
                        if (map.mapDiffuculty == 3) {
                            gameBeat = true;
                        } else {
                            difficultCompleted = true;
                        }
                        currentMapBeat = !currentMapBeat;
                        return;
                    }

                    if (map.stage < 5) {
                        playerX = 3;
                        playerY = 0;
                        map.stage++;
                        mapId = !mapId;
                        map.loadTile();
                        music.stopGameMusic();
                        music.startNewMapMusic();
                        Timer timer = new Timer(1500, _ -> {
                            music.stopNewMapMusic();
                            music.startGameMusic();
                        });
                        timer.start();
                        timer.setRepeats(false);
                        timer.setDelay(0);
                        currentMapBeat = !currentMapBeat;
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (key.up) {
            playerImage = walking < 5 ? player.playerUp1 : player.playerUp2;
            Y -= playerMovement;
        } else if (key.down) {
            playerImage = walking < 5 ? player.playerDown1 : player.playerDown2;
            Y += playerMovement;
        } else if (key.left) {
            playerImage = walking < 5 ? player.playerLeft1 : player.playerLeft2;
            X -= playerMovement;
        } else if (key.right) {
            playerImage = walking < 5 ? player.playerRight1 : player.playerRight2;
            X += playerMovement;
        }

        if (walking < 10) {
            walking++;
        } else {
            walking = 0;
        }

        if (!playerCollision.playerCollision(X, Y)) {
            playerX = X;
            playerY = Y;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (mapId) {
            text.drawNextStageText(g2d);
        }

        if (gameBeat) {
            text.draw(g2d);
        } else if (difficultCompleted) {
            text.drawCongratulations(g2d);
        } else if (pauseState) {
            pause.draw(g2d);
        } else if (menuState) {
            menu.draw(g2d);
        } else if (deadState) {
            dead.draw(g2d);
        } else {
            map.drawMap(g2d);
            g2d.drawImage(playerImage, playerX, playerY, characterSize, characterSize, null);
        }


        g2d.dispose();
    }


}
