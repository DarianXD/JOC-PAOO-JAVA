package main;

import entity.Entity;
import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.DataBase.getFromDataBase;

//Creeare clasa pentru user interface
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arkkos;
    public int commandNum = 0;
    public int titleScreenState = 0; // 0-> meniu principal, 1-> meniu help
    BufferedImage heart_full, heart_half, heart_blank;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/Mono.ttf"); //creeare font
            arkkos = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        SuperObject heart = new OBJ_Heart(gp); //creeare inimi
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void draw(Graphics2D g2) { //desenare ecran in functie de gamestate
        this.g2 = g2;

        g2.setFont(arkkos);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        if (gp.gameState == gp.nextLevelState) {
            drawNextLevel();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        if (gp.gameState == gp.gameFinishedState) {
            drawGameFinishedScreen();
        }
    }


    public void drawPlayerLife() { //metoda pentru afisarea inimilor
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //nume joc
            g2.setFont(arkkos);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
            String text = "Blade of Time";
            int x = getXforCenter(text);
            int y = gp.tileSize * 4;


            //umbra nume joc
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 4, y + 4);

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //Meniu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            text = "New Game";
            x = getXforCenter(text);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            //Load Game -> nefunctional
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            text = "Load Game";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            //Quit -> functional
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            text = "Quit";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            //Help -> functional
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            text = "Help";
            x = getXforCenter(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            // titleScreenState = 1;

        } else if (titleScreenState == 1) {

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(28F));

            String text1 = "To get to the next level you first have to eliminate ";
            int x = getXforCenter(text1) - gp.tileSize;
            int y = gp.tileSize * 3;
            g2.drawString(text1, x, y);

            String text = "the level's boss and then go to the portal on the right side.";
            x = getXforCenter(text1) - gp.tileSize;
            y += gp.tileSize * 1;
            g2.drawString(text, x, y);

            text = "To win you have to eliminate all 3 bosses.";
            x = getXforCenter(text1) - gp.tileSize;
            y += gp.tileSize * 1;
            g2.drawString(text, x, y);
            commandNum = 0;

            text = "Back";
            x = getXforCenter(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    public void drawNextLevel() { //Ecran next level
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(arkkos);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
        String text = "Level";
        int x = getXforCenter(text) - gp.tileSize * 2;
        int y = gp.tileSize * 6;
        g2.drawString(text, x, y);
        int lv = gp.currentMap;
        lv += 2;
        text = String.valueOf(lv);
        x += getXforCenter(text) - gp.tileSize;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        text = "Press Enter to continue";
        x = getXforCenter(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);


    }

    public void drawGameOverScreen() {//ecran joc pierdut
        g2.setFont(arkkos);
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 130f));

        text = "Game Over";
        x = getXforCenter(text);
        y = gp.tileSize * 5;

        //umbra
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        //titlu
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        commandNum = 0;
        //revenire la meniu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90f));
        text = "Restart";
        x = getXforCenter(text);
        y += gp.tileSize * 7;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 60, y);
        }
    }

    public void drawGameFinishedScreen() {
        g2.setFont(arkkos);
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "You Won!";
        x = getXforCenter(text);
        y = gp.tileSize * 5;

        //Umbra
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        //text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        text = "Your score is :";
        x = getXforCenter(text) - gp.tileSize * 2;
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        int s;
        s = getFromDataBase("DarianBigBazaDeDate", "DarianBigBazaDeDateTabel");//Se afiseaza din baza de date scorul
        text = String.valueOf(s);
        x += gp.tileSize * 15;
        g2.drawString(text, x, y);

        commandNum = 0;
        //Quit
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Quit";
        x = getXforCenter(text);
        y += 100;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }
    }

    public int getXforCenter(String text) {//mijloc ecran
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - textLength / 2;
        return x;
    }
}
