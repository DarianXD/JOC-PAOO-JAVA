package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile; //vector de tile pentru
    public int mapTileNum[][][]; // matrice la care prima dimensiune inseamna nivelul hartii

    public TileManager(GamePanel gp, String path, int mapNum) // constructora
    {
        this.gp = gp;

        tile = new Tile[1000];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap(path, mapNum);
    }

    //incarcare fisier.txt in conformitate cu formatul lui
    public void loadMap(String a, int mapNumber) {
        try {
            InputStream is = getClass().getResourceAsStream(a);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < 250; i++) {
                String s = br.readLine();
                s = s.replaceAll("            ", "");
                s = s.replaceAll(",", "");
                // s = s.replaceAll("  ", "");

                String[] numbers = s.split(" ");
                for (int j = 0; j < 250; j++) {
                    int num;
                    num = Integer.parseInt(numbers[j]) - 1 - mapNumber * 256;
                    mapTileNum[mapNumber][j][i] = num;
                }
            }
            for (int i = 0; i < 250; i++) {
                for (int j = 0; j < 250; j++) {
                    //System.out.print(mapTileNum[i][j]);
                }
                //System.out.println();
            }
            br.close();

        } catch (Exception e) {
        }
    }

    public void getTileImage() { //Incarcare imagini tiles si stabilirea coliziunilor

        switch (gp.currentMap) {
            case 0:
                try {
                    int k = 0;
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HartaNivel1/tiles.png"));
                    for (int i = 0; i < 16; ++i) {
                        for (int j = 0; j < 16; ++j) {
                            if (k == 17 || k == 18 || k == 33 || k == 34 || k == 81 || k == 82 || k == 97 || k == 98 || k == 104 || k == 105 || k == 106 || k == 192 || k == 193 || k == 194 || k == 208 || k == 209 || k == 210 || k == 224 || k == 225 || k == 226) {
                                tile[k] = new Tile(image.getSubimage(j * 48, i * 48, 48, 48));
                                tile[k].collision = false;
                            } else {
                                tile[k] = new Tile(image.getSubimage(j * 48, i * 48, 48, 48));
                                tile[k].collision = true;
                            }
                            k++;
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    int k = 0;
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HartaNivel2/tiles2.png"));
                    for (int i = 0; i < 16; ++i) {
                        for (int j = 0; j < 16; ++j) {
                            if (k == 17 || k == 18 || k == 33 || k == 34 || k == 81 || k == 82 || k == 97 || k == 98 || k == 104 || k == 105 || k == 106 || k == 192 || k == 193 || k == 194 || k == 208 || k == 209 || k == 210 || k == 224 || k == 225 || k == 226) {
                                tile[k] = new Tile(image.getSubimage(j * 48, i * 48, 48, 48));
                                tile[k].collision = false;
                            } else {
                                tile[k] = new Tile(image.getSubimage(j * 48, i * 48, 48, 48));
                                tile[k].collision = true;
                            }
                            k++;
                        }
                    }
                    //System.out.println("GetTileImage 2");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    int k = 0;
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HartaNivel3/tiles3.png"));
                    for (int i = 0; i < 16; ++i) {
                        for (int j = 0; j < 16; ++j) {
                            if (k == 17 || k == 18 || k == 33 || k == 34 || k == 81 || k == 82 || k == 97 || k == 98 || k == 104 || k == 105 || k == 106 || k == 192 || k == 193 || k == 194 || k == 208 || k == 209 || k == 210 || k == 224 || k == 225 || k == 226) {
                                tile[k] = new Tile(image.getSubimage(j * 48, i * 48, 48, 48));
                                tile[k].collision = false;
                            } else {
                                tile[k] = new Tile(image.getSubimage(j * 48, i * 48, 48, 48));
                                tile[k].collision = true;
                            }
                            k++;
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }
    public void draw(Graphics2D g2) { //Desenare Tiles

        int screenX = gp.player.worldX + gp.tileSize / 2 - gp.screenWidth / 2;
        int screenY = gp.player.worldY + gp.tileSize / 2 - gp.screenHeight / 2;
        int renderScreenX1 = (screenX / gp.tileSize) * gp.tileSize - screenX;
        int renderScreenY1 = (screenY / gp.tileSize) * gp.tileSize - screenY;
        int tileX = screenX / gp.tileSize;
        int tileY = screenY / gp.tileSize;
        int tileYCopy = tileY;

        for (int i = renderScreenX1; i < gp.screenWidth; i += gp.tileSize, tileX++, tileY = tileYCopy) {
            for (int j = renderScreenY1; j < gp.screenHeight; j += gp.tileSize, tileY++) {
                if (tileX >= 0 && tileY >= 0 && tileX < gp.maxWorldCol && tileY < gp.maxWorldRow)
                    if (mapTileNum[gp.currentMap][tileX][tileY] != 0)
                        g2.drawImage(tile[mapTileNum[gp.currentMap][tileX][tileY]].image, i, j, null);
            }
        }
    }

}
