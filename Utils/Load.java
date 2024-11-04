package JumpOrRunLevelbuilder.Utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Load {
    public static BufferedImage GetImage(String src){
        BufferedImage img = null;
        InputStream is = Load.class.getResourceAsStream("../Resources/" + src);
        try{
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
}