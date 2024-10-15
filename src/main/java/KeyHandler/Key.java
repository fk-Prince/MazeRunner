package KeyHandler;

import Game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

    public boolean up, down, left, right;
    private final Game game;

    public Key(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.difficultCompleted) {
            keyComplete(e);
        } else if (game.pauseState) {
            keyPause(e);
        } else if (game.menuState) {
            keyMenu(e);
        } else if (game.deadState) {
            keyDead(e);
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) game.pauseState = true;
            if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
            if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
            if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        }
    }

    private void keyComplete(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            game.text.continueButton = game.text.continueButton == 1 ? 3 : game.text.continueButton - 1;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            game.text.continueButton = game.text.continueButton == 3 ? 1 : game.text.continueButton + 1;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (game.text.continueButton == 1) {
                game.restart();
                if (game.map.mapDiffuculty == 1 || game.map.mapDiffuculty == 2) {
                    game.map.mapDiffuculty++;
                    game.map.stage = 1;
                    game.map.loadTile();
                    game.music.stopNewMapMusic();
                    game.music.startGameMusic();
                    game.mapId = true;
                }
            }
            if (game.text.continueButton == 2) {
                game.music.startMenuMusic();
                game.music.stopGameOverMusic();
                game.music.startGameMusic();
                game.menu.choice = 1;
                game.restart();
                game.pauseState = false;
                game.menuState = true;
            }
            if (game.text.continueButton == 3) {
                System.exit(0);
            }
            game.text.continueButton = 1;
            game.difficultCompleted = !game.difficultCompleted;
        }
    }

    private void keyDead(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            game.dead.deadChoice = game.dead.deadChoice == 1 ? 3 : game.dead.deadChoice - 1;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            game.dead.deadChoice = game.dead.deadChoice == 3 ? 1 : game.dead.deadChoice + 1;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            game.music.stopGameOverMusic();
            if (game.dead.deadChoice == 1) {
                game.music.startGameMusic();
                game.music.stopGameOverMusic();
                game.restart();
                game.map.loadTile();
                game.mapId = true;
            }
            if (game.dead.deadChoice == 2) {
                game.music.startMenuMusic();
                game.music.stopGameOverMusic();
                game.menu.choice = 1;
                game.restart();
                game.pauseState = false;
                game.menuState = true;
            }
            if (game.dead.deadChoice == 3) {
                System.exit(0);
            }
            game.map.stage = 1;
            game.dead.deadChoice = 1;
            game.deadState = false;
        }
    }


    private void keyPause(KeyEvent e) {
        if (game.pause.pauseChoice == 2) {
            if (game.pause.soundSettingPauseButton == 1) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    game.pause.volume = game.pause.volume > 2 ? game.pause.volume - 1.1f : 0;
                    game.music.volumeDown();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.pause.volume = (game.menu.volume < 100) ? game.pause.volume + 1.1f : 100;
                    game.music.volumeUp();
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                game.pause.soundSettingPauseButton = game.pause.soundSettingPauseButton == 2 ? 1 : game.pause.soundSettingPauseButton + 1;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                game.pause.soundSettingPauseButton = game.pause.soundSettingPauseButton == 1 ? 2 : game.pause.soundSettingPauseButton - 1;
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (game.pause.soundSettingPauseButton == 2) {
                    game.pause.pauseChoice = 1;
                }
            }
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP)
            game.pause.resumeButton = game.pause.resumeButton == 1 ? 4 : game.pause.resumeButton - 1;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            game.pause.resumeButton = game.pause.resumeButton == 4 ? 1 : game.pause.resumeButton + 1;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (game.pause.resumeButton == 1) {
                game.pauseState = false;
            }
            if (game.pause.resumeButton == 2) {
                game.music.stopGameMusic();
                game.music.startMenuMusic();
                game.menu.choice = 1;
                game.restart();
                game.pauseState = false;
                game.menuState = true;
            }
            if (game.pause.resumeButton == 3) {
                game.pause.pauseChoice = 2;
            }
            if (game.pause.resumeButton == 4) {
                System.exit(0);
            }
            game.pause.resumeButton = 1;
        }
    }


    private void keyMenu(KeyEvent e) {
        if (game.menu.diffultyMenu) {
            if (e.getKeyCode() == KeyEvent.VK_UP)
                game.menu.difficultyButton = game.menu.difficultyButton == 1 ? 4 : game.menu.difficultyButton - 1;
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                game.menu.difficultyButton = game.menu.difficultyButton == 4 ? 1 : game.menu.difficultyButton + 1;
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                game.music.stopMenuMusic();
                game.music.startGameMusic();
                if (game.menu.difficultyButton == 1) {
                    game.map.mapDiffuculty = 1;
                    game.menuState = false;
                }
                if (game.menu.difficultyButton == 2) {
                    game.map.mapDiffuculty = 2;
                    game.menuState = false;
                }
                if (game.menu.difficultyButton == 3) {
                    game.map.mapDiffuculty = 3;
                    game.menuState = false;
                }
                if (game.menu.difficultyButton == 4) {
                    game.menu.choice = 1;
                }

                game.menu.choice = 1;
                game.map.stage = 1;
                game.menu.diffultyMenu = false;
                game.mapId = true;
                game.map.loadTile();
                game.menu.difficultyButton = 1;
            }
            return;
        }


        if (game.menu.settingMenu) {
            if (e.getKeyCode() == KeyEvent.VK_UP)
                game.menu.soundButton = game.menu.soundButton == 1 ? 2 : game.menu.soundButton - 1;
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                game.menu.soundButton = game.menu.soundButton == 2 ? 1 : game.menu.soundButton + 1;
            if (game.menu.soundButton == 1) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    game.menu.volume = game.menu.volume > 2 ? game.menu.volume - 1.1f : 0;
                    game.music.volumeDown();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.menu.volume = (game.menu.volume < 100) ? game.menu.volume + 1.1f : 100;
                    game.music.volumeUp();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (game.menu.soundButton == 2) {
                    game.menu.settingMenu = false;
                    game.menu.choice = 1;
                }
                game.menu.soundButton = 1;
            }
            return;
        }


        if (e.getKeyCode() == KeyEvent.VK_UP)
            game.menu.menuActiveButton = game.menu.menuActiveButton == 1 ? 3 : game.menu.menuActiveButton - 1;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            game.menu.menuActiveButton = game.menu.menuActiveButton == 3 ? 1 : game.menu.menuActiveButton + 1;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (game.menu.menuActiveButton == 1) {
                game.menu.choice = 2;
                game.menu.diffultyMenu = true;
            }
            if (game.menu.menuActiveButton == 2) {
                game.menu.choice = 3;
                game.menu.settingMenu = true;
            }
            if (game.menu.menuActiveButton == 3) {
                System.exit(0);
            }
            game.menu.menuActiveButton = 1;
        }

    }
}
