package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

//KeyListener is an interface for receiving keyboard events
public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Meniu principal
        if (gp.ui.titleScreenState == 0) {
            if (gp.gameState == gp.titleState) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState;

                    }
                    if (gp.ui.commandNum == 1) {
                        //load
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 1;
                    }
                }

            }
            //Meniu help
        } else if (gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 0; //daca apesi enter se duce inapoi pe meniul principal
                }
            }


        }
        //Joc
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
        }
        //Nivel urmator
        if (gp.gameState == gp.nextLevelState) {
            if (code == KeyEvent.VK_ENTER) {
                if (gp.currentMap == 0) {
                    gp.currentMap = 1;
                    gp.setMap();
                    gp.player.setPosition1();
                    gp.gameState = gp.playState;
                } else if (gp.currentMap == 1) {
                    gp.currentMap = 2;
                    gp.setMap();
                    gp.player.setPosition2();
                    gp.gameState = gp.playState;
                }
            }
        } else if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
                gp.ui.commandNum = 0;
                gp.restart();
            }
        } else if (gp.gameState == gp.gameFinishedState) {
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    System.exit(0);
                }
            }

        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
