package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static main.DataBase.insertInDataBase;

//import static main.DataBase.insertB;

//Clasa de baza a jucatorului
public class Player extends Entity {
    KeyHandler keyH;

    MouseHandler mouseH;

    BufferedImage attack1, attack2, attack3, attack4, attack5, attack6;
    BufferedImage attack11, attack22, attack33, attack44, attack55, attack66;
    public int screenX; //describes where we draw the player on the screen
    public int screenY;
    public boolean win;

    public int score;
    public int demonsEliminated;


    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) { // Constructor player -> seteaza valorile default si obtine imaginea jucatorului
        super(gp);
        this.keyH = keyH;
        this.mouseH = mouseH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() //Genereaza player-ul in centrul hartii si al ecranului
    {
        worldX = gp.tileSize * 125;
        worldY = gp.tileSize * 125;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2) - 40;
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2) - 48;

        solidArea = new Rectangle();

        solidArea.x = 0;
        solidArea.y = 30;
        solidArea.width = 80;
        solidArea.height = 80;

        attackArea = new Rectangle(-30, 0, 100, 100); //zona de atac a jucatorului
        //solidArea= new Rectangle(80, 30, 48, 70);
        //attackArea.width = 150;
        //attackArea.height = 150;


        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        type = 0;
        speed = 20;
        direction = "down";
        maxLife = 14;
        life = 14;
        attack = 2;

    }

    public void setPosition1() { //pozitie pentru nivelul 2
        worldX = gp.tileSize * 125;
        worldY = gp.tileSize * 125;
        direction = "down";
        win = false;
    }

    public void setPosition2() { //pozitie pentru nivelul 3
        worldX = gp.tileSize * 125;
        worldY = gp.tileSize * 125;
        direction = "down";
        win = false;
    }

    public void getPlayerImage() {//Functie pentru a citi din folderul de resurse imaginile player-ului
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersInainte11/Animatie11-0.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersInainte11/Animatie11-1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersSpate10/Animatie10-0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersSpate10/Animatie10-1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersStanga9.1/Animatie9.1-0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersStanga9.1/Animatie9.1-1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersDreapta9/Animatie9-0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frameuri de mers/MersDreapta9/Animatie9-1.png"));
            attack1 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7/Animatie7-0.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7/Animatie7-1.png"));
            attack3 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7/Animatie7-2.png"));
            attack4 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7/Animatie7-3.png"));
            attack5 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7/Animatie7-4.png"));
            attack6 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7/Animatie7-5.png"));
            //attack11 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7.1/Animatie7.1-0.png"));
            //attack22 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7.1/Animatie7.1-1.png"));
            //attack33 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7.1/Animatie7.1-2.png"));
            //attack44 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7.1/Animatie7.1-3.png"));
            //attack55 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7.1/Animatie7.1-4.png"));
            //attack66 = ImageIO.read(getClass().getResourceAsStream("/Samurai/Frame-uri atac/AtacSimpluDreapta7.1/Animatie7.1-5.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { //metoda pentru schimbarea directiei unei taste/mouse
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || mouseH.mouseClicked) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }
            if (mouseH.mouseClicked == true) {
                attacking();
                direction = "mouseStanga";

            }
            //verificare coliziuni cu tile-uri, obiecte, entitati
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int enemyIndex = gp.cChecker.checkEntity(this, gp.demon);
            contactEnemy(enemyIndex);

            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
        }

    }


    public void draw(Graphics2D g2) {
        //    g2.setColor(Color.white);
        //    g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up1;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down1;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left1;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right1;
                }
                break;
            case "mouseStanga":
                if (spriteNum == 1) {
                    image = attack1;
                }
                if (spriteNum == 2)
                    image = attack2;
                if (spriteNum == 3) {
                    image = attack1;
                }
                break;
        }

        //Opacitatea player-ului e de 60% pe parcursul perioadei de invicibilitate
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        }
        g2.drawImage(image, screenX, screenY, 126, 126, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //g2.setColor(Color.red);
        //g2.fillRect((int) (screenX + attackArea.x), (int) (screenY + attackArea.y), attackArea.width, attackArea.height);
        //g2.setColor(Color.BLUE);
        //g2.fillRect((int) (screenX + solidArea.x), (int) (screenY + solidArea.y), solidArea.width, solidArea.height);
    }

    public void attacking() {//metoda atac al jucatorului
        int currentX = worldX;
        int currentY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;
        //incadreaza atacul in chenarului "invizibil" al mapei
        switch (direction) {
            case "up":
                worldY = currentY - attackArea.height;
                break;
            case "down":
                worldY = currentY + attackArea.height;
                break;
            case "left":
                worldX = currentX - attackArea.width;
                break;
            case "right":
                worldX = currentX + attackArea.width;
                break;

        }
        //Zona de atac devine solida pentru a putea intra in coliziune cu alta entitate si a-i da damage
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        //Verificare coliziune inamic
        int enemyIndex = gp.cChecker.checkEntity(this, gp.demon);
        damageEnemy(enemyIndex, attack);

        //restabilire valori curente
        worldX = currentX;
        worldY = currentY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }

    //Scadere viata jucator la contact cu atacul inamicului
    public void contactEnemy(int i) {
        if (i != 999) {
            if (invincible == false && gp.demon[gp.currentMap][i].dying == false) {
                life -= gp.demon[gp.currentMap][i].attack;
                invincible = true;
            }
        }
    }

    //Scadere viata inamic la contact cu atacul playerului
    public void damageEnemy(int i, int attack) {
        if (i != 999) {
            System.out.println("hit");
            if (gp.demon[gp.currentMap][i].invincible == false) {
                gp.demon[gp.currentMap][i].life -= attack;
                gp.demon[gp.currentMap][i].invincible = true;
                //gp.demon[i].attackReaction();

                if (gp.demon[gp.currentMap][i].life <= 0) {
                    gp.demon[gp.currentMap][i].dying = true;
                }
            }
        } else {
            System.out.println("miss");
        }
    }

    //Se face trecerea intre niveluri la contactul cu portalul daca boss-ul nivelului este eliminat
    //Se calculeaza scorul in functie de viata si nr de inamici eliminati si se incarca in baza de date la finalul jocului
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[gp.currentMap][i].name;
            switch (objectName) {
                case "PortalLvl1":
                    if (gp.currentMap == 0 && gp.demon[0][0] == null) {
                        win = true;
                        gp.gameState = gp.nextLevelState;
                    } else if (gp.currentMap == 1 && gp.demon[1][6] == null) {
                        win = true;
                        gp.gameState = gp.nextLevelState;
                    } else if (gp.currentMap == 2 && gp.demon[2][13] == null) {
                        score = life * demonsEliminated;
                        insertInDataBase("DarianBigBazaDeDate", "DarianBigBazaDeDateTabel", score);
                        gp.gameState = gp.gameFinishedState;
                    }
                    break;
            }
        }
    }

}
