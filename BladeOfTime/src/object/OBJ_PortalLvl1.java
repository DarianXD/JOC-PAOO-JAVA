package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PortalLvl1 extends SuperObject {
    public OBJ_PortalLvl1(GamePanel gp) {
        super(gp);
        name = "PortalLvl1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/portal1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
