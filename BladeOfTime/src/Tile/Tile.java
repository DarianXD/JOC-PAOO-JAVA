package Tile;

import java.awt.image.BufferedImage;
//Clasa pt creeare obiecte de tip tile
public class Tile {
    public BufferedImage image;
    public boolean collision = false;

    public Tile(BufferedImage image) {
        this.image = image;
    }
}
