package main;

import Tile.TileManager;
import entity.Entity;
import entity.Player;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//JPANEL PICTURA IN SINE, JFRAME RAMA DE TABLOU
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16 x 16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 x 48
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    //World settings
    public final int maxWorldCol = 250;
    public final int maxWorldRow = 250;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //Harti
    public final int maxMap = 10;
    public int currentMap = 0;

    //UI

    public UI ui = new UI(this);


    int FPS = 60;

    Thread gameThread; //folosesc un thread
    KeyHandler keyH = new KeyHandler(this);

    MouseHandler mouseH = new MouseHandler(this);


    // TileManager tileM = new TileManager(this, "/Maps/map01.txt");
    TileManager tileML; //layer 1 harta
    TileManager tileM2; //layer 2 harta
    //TileManager t;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Assets aSetter = new Assets(this);

    //Entitati
    public Player player = new Player(this, keyH, mouseH);
    public Entity[][] demon = new Entity[maxMap][30];
    public SuperObject[][] obj = new SuperObject[maxMap][40];
    ArrayList<Entity> entityList = new ArrayList<>();

    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int gameOverState = 2;
    public final int gameFinishedState = 3;
    public final int nextLevelState = 4;


    public GamePanel()  {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true); // with this, this gamepanel can be focused to receive key input;
    }

    public void setupGame() { //initializam obiectele si afisam prima data meniul
        aSetter.setObject();
        aSetter.setEnemy();
        gameState = titleState;
    }

    public void restart() { // metoda de resetare a valorilor pentru reluarea jocului
        player.setDefaultValues();
        aSetter.setObject();
        aSetter.setEnemy();
        currentMap = 0;
        setMap();
    }

    public void setMap() { //functie de setare a hartii

        switch (currentMap) {
            case 0:
                tileML = new TileManager(this, "/Maps/HartaNivel1Layer1.txt", 0);
                tileM2 = new TileManager(this, "/Maps/HartaNivel1Layer2.txt", 0);
                System.out.println("Map 1 done");
                break;
            case 1:
                tileML = new TileManager(this, "/Maps/HartaNivel2Layer1.txt", 1);
                tileM2 = new TileManager(this, "/Maps/HartaNivel2Layer2.txt", 1);
                System.out.println("Map 2 done");
                break;
            case 2:
                tileML = new TileManager(this, "/Maps/HartaNivel3Layer1.txt", 2);
                tileM2 = new TileManager(this, "/Maps/HartaNivel3Layer2.txt", 2);
                System.out.println("Map 3 done");
                break;
        }

    }

    public void startGameThread() { //pornire thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//
//        double drawInterval = 1000000000 / FPS;
//        double  nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null)
//        {
//
//            //UPDATE INFORMATION AS CARACTER POSITION
//            update();
//            //2 DRAW : DRAW THE SCREEN IS THE UPDATED INFORMATION
//            repaint();
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if(remainingTime < 0)
//                {
//                    remainingTime = 0;
//                }
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//        }
//    }
    public void run() { //metoda run pt joc
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() { //update pt demoni si player
        if (gameState == playState) {
            player.update();
        }
        for (int i = 0; i < demon[1].length; i++) {
            if (demon[currentMap][i] != null) {
                if (demon[currentMap][i].alive == true && demon[currentMap][i].dying == false) {
                    demon[currentMap][i].update();
                }

                if (demon[currentMap][i].alive == false) { //daca nu sunt in viata creste counter-ul care ajuta la scor
                    demon[currentMap][i] = null;
                    player.demonsEliminated++;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics 2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management and text layout.
        Graphics2D g2 = (Graphics2D) g; //we change Graphics g in graphics2D
        // tileM.draw(g2); //be careful, if you put tileM.draw after player.draw, then the tiles will hide the character
        if (gameState == titleState) {//Desenare UI
            ui.draw(g2);
        } else { //desenare layere harta

            tileML.draw(g2);
            tileM2.draw(g2);


            for (int i = 0; i < obj[1].length; i++) { //desenare obiecte
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2, this);
                }
            }
            for (int i = 0; i < demon[1].length; i++) {
                if (demon[currentMap][i] != null) {
                    entityList.add(demon[currentMap][i]);
                }
            }


            //Sortare vector pentru a nu exista conflicte la desenarea demonilor
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.y, e2.y);
                    return result;
                }

            });

            //Desenare demoni
            for (int i = 0; i < entityList.size(); i++) {

                entityList.get(i).draw(g2, 1, 1);
            }

            entityList.clear();//eliberare vector
            player.draw(g2);//desenare player
            ui.draw(g2);//desenare inimioare
        }
        g2.dispose();
    }

}

