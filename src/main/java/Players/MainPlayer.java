package Players;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MainPlayer {
    public BufferedImage playerDown1,playerDown2,playerUp1,playerUp2,playerLeft1,playerLeft2,playerRight1,playerRight2;

    public MainPlayer(){
        loadPlayerImage();
    }

    public void loadPlayerImage() {
        try{
            playerDown1 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/down1.png"));
            playerDown2 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/down2.png"));
            playerUp1 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/up1.png"));
            playerUp2 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/up2.png"));
            playerRight1 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/right1.png"));
            playerRight2 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/right2.png"));
            playerLeft1 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/left1.png"));
            playerLeft2 = ImageIO.read(getClass().getResourceAsStream("/Image/Character/left2.png"));


        }catch( Exception e){
            e.printStackTrace();
        }

    }
}
