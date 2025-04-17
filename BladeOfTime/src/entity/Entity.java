package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Clasa abstracta pentru entitati
public abstract class Entity {
    public GamePanel gp;
    public int x, y;
    public int worldX, worldY;
    public int speed;
    public int life, maxLife;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction = "down";

    public int mouse;/////////////////////

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public boolean collisionOn = false; //se activeaza cand entitatea atinge un obiect/tile/entitate cu coliziune
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int type;
    public boolean invincible = false; //Perioada de invicibilitate pentru evitarea atacului multiplu
    public int invincibleCounter = 0;
    public int actionLockCounter;

    public int attack;
    public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter = 0;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    } //Functie pentru inamici pentru a le da directie, acestia au directia random


    public void update() { //functie de update care verifica tile-urile si alte entitati pentru a intra in coliziune cu ele
        setAction();


        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.demon);
        //System.out.println("Checking");


        boolean contactPlayer = gp.cChecker.checkPlayer(this);


        if (this.type == 2 && contactPlayer == true) { //daca entitatea e de tip inamic si intra in contact cu player-ul acesta ia damage
            damagePlayer(attack);
        }


        if (collisionOn == false) { //Daca nu exista coliziune entitatea se poate misca
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
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
        if (invincible == true) { //Perioada de invicibilitate pentru entitate
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }


    public BufferedImage setup(String imagePath) { //functie nefolosita
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void draw(Graphics2D g2, int scale1, int scale2) { // functie folosita pentru afisarea entitatii in functie de directie
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        /*if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && //Pentru randare
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) */


        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 1) {
                    image = down3;
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
                    image = left3;
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
                    image = right3;
                }
                break;
        }
        if (type == 2) { //Daca entitatea e de tip inamic se afiseaza bara de viata

            double oneScale = (double) gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(19, 19, 19));
            g2.fillRect(worldX + 59, worldY + 29, gp.tileSize + 2, 12);
            g2.setColor(new Color(196, 9, 9));
            g2.fillRect(worldX + 60, worldY + 30, (int) hpBarValue, 10);
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));// scade opacitatea entitatii pentru a se sesiza faptul ca e invincibila
        }

        if (dying == true) {
            dyingAnimation(g2);
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize * scale1, gp.tileSize * scale2, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //g2.setColor(Color.BLUE);
        //g2.fillRect((int)(screenX + solidArea.x), (int)(screenY + solidArea.y), solidArea.width , solidArea.height);
        //g2.setColor(Color.red);
        //g2.fillRect((int) (screenX + attackArea.x), (int) (screenY + attackArea.y), attackArea.width, attackArea.height);


    }

    public void dyingAnimation(Graphics2D g2) { //Efectual de blinking
        dyingCounter++;
        int i = 3;
        if (dyingCounter <= i) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > i * 8) {

            alive = false;
        }
    }

    public void damagePlayer(int attack) { //metoda pentru scaderea vietii jucatorului la coliziune/contact
        if (gp.player.invincible == false) {
            gp.player.life -= attack;
            gp.player.invincible = true;
        }

    }
}
