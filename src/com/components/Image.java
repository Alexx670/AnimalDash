package com.components;

import com.engine.Component;
import com.structure.Assets;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
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
            this.image = ImageIO.read(file);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Image(BufferedImage image, String pictureFile) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pictureFile = pictureFile;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)gameObject.location.position.x, (int)gameObject.location.position.y,
                (int)(width*(this.gameObject.location.scale.x)), (int)(height*(this.gameObject.location.scale.y)), null);
    }

    public Image copy(){
        return (new Image(this.image, this.pictureFile));
    }
}
