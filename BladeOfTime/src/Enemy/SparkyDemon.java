package Enemy;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
//Demon general niveluri
public class SparkyDemon extends Entity {

    public SparkyDemon(GamePanel gp) {
        super(gp);

        type = 2;
        speed = 3;
        attack = 1;
        maxLife = 3;
        life = maxLife;

        solidArea = new Rectangle(20, 10, 48, 70);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }


    public void getImage() {
        /**
         up1 = setup("/DemonsSprite/mersLup0");
         up2 = setup("/DemonsSprite/mersLup1");
         down1 = setup("/DemonsSprite/mersLup0");
         down2 = setup("/DemonsSprite/mersLup1");
         left1 = setup("/DemonsSprite/mersLup0");
         left2 = setup("/DemonsSprite/mersLup1");
         right1 = setup("/DemonsSprite/mersLup0");
         right2 = setup("/DemonsSprite/mersLup1");

         */
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/ru1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/ru2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/ru3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rd1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rd2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rd3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rl1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rl2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rl3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rr1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rr2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/DemonsSprite/Sparky/rr3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 90) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "down";
            }
            if (i > 25 && i <= 50) {
                direction = "up";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }


    public void draw(Graphics2D g2, int scale1, int scale2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        /*if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
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
                if (spriteNum == 3) {
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
        if (type == 2) {

            double oneScale = (double) gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(19, 19, 19));
            g2.fillRect(screenX + 30, screenY - 20, gp.tileSize + 2, 12);
            g2.setColor(new Color(197, 11, 11));
            g2.fillRect(screenX + 32, screenY - 19, (int) hpBarValue, 10);
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        if (dying == true) {
            dyingAnimation(g2);
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //g2.setColor(Color.BLUE);
        //g2.fillRect((int)(screenX + solidArea.x), (int)(screenY + solidArea.y), solidArea.width , solidArea.height);
        //g2.setColor(Color.red);
        // g2.fillRect((int) (screenX + attackArea.x), (int) (screenY + attackArea.y), attackArea.width, attackArea.height);


    }
}
