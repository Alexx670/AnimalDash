package com.components;

import com.engine.Component;
import com.structure.Assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image extends Component {
    public String pictureFile;
    public int width, height;
    public BufferedImage image;

    public Image (String pictureFile) {
        this.pictureFile = pictureFile;

        try {
            File file = new File(pictureFile);
            if (Assets.hasImage(pictureFile)) {
                throw new Exception("Asset already exists: "+pictureFile);
            }
            this.image = ImageIO.read(file);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        //g2.drawImage(image, gameObject.position.position.x, gameObject.position.position.y, width, height, null);
    }
}
