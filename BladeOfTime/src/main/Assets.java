package main;

import Enemy.FinalDemon;
import Enemy.MagicDemon;
import Enemy.SparkyDemon;
import Enemy.WolfDemon;
import object.OBJ_PortalLvl1;
//Instantiere inamici/obiecte pt fiecare nivel si stabilirea pozitiilor lor pe harta
public class Assets {
    GamePanel gp;

    public Assets(GamePanel gp) {
        this.gp = gp;
    }

    public void setEnemy() {
        int mapNum = 0;
        int i = 0;
        gp.demon[mapNum][i] = new WolfDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 47;
        gp.demon[mapNum][i].worldY = gp.tileSize * 211;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 118;
        gp.demon[mapNum][i].worldY = gp.tileSize * 224;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 120;
        gp.demon[mapNum][i].worldY = gp.tileSize * 226;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 116;
        gp.demon[mapNum][i].worldY = gp.tileSize * 220;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 110;
        gp.demon[mapNum][i].worldY = gp.tileSize * 225;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 112;
        gp.demon[mapNum][i].worldY = gp.tileSize * 230;
        i++;


        mapNum = 1;
        gp.demon[mapNum][i] = new MagicDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 45;
        gp.demon[mapNum][i].worldY = gp.tileSize * 210;
        i++;
        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 118;
        gp.demon[mapNum][i].worldY = gp.tileSize * 224;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 120;
        gp.demon[mapNum][i].worldY = gp.tileSize * 226;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 116;
        gp.demon[mapNum][i].worldY = gp.tileSize * 220;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 110;
        gp.demon[mapNum][i].worldY = gp.tileSize * 225;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 112;
        gp.demon[mapNum][i].worldY = gp.tileSize * 230;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 114;
        gp.demon[mapNum][i].worldY = gp.tileSize * 233;
        i++;

        mapNum = 2;
        gp.demon[mapNum][i] = new FinalDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 44;
        gp.demon[mapNum][i].worldY = gp.tileSize * 215;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 118;
        gp.demon[mapNum][i].worldY = gp.tileSize * 224;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 120;
        gp.demon[mapNum][i].worldY = gp.tileSize * 226;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 116;
        gp.demon[mapNum][i].worldY = gp.tileSize * 220;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 110;
        gp.demon[mapNum][i].worldY = gp.tileSize * 225;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 112;
        gp.demon[mapNum][i].worldY = gp.tileSize * 230;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 114;
        gp.demon[mapNum][i].worldY = gp.tileSize * 233;
        i++;

        gp.demon[mapNum][i] = new SparkyDemon(gp);
        gp.demon[mapNum][i].worldX = gp.tileSize * 90;
        gp.demon[mapNum][i].worldY = gp.tileSize * 233;
        i++;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_PortalLvl1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 183;
        gp.obj[mapNum][i].worldY = gp.tileSize * 217;
        i++;

        mapNum = 1;
        gp.obj[mapNum][i] = new OBJ_PortalLvl1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 183;
        gp.obj[mapNum][i].worldY = gp.tileSize * 217;
        i++;

        mapNum = 2;
        gp.obj[mapNum][i] = new OBJ_PortalLvl1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 183;
        gp.obj[mapNum][i].worldY = gp.tileSize * 217;
        i++;

    }
}
