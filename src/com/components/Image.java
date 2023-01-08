package com.components;

import com.engine.Component;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Klasa przechowująca obraz, który może być dołączony do dowolnego obiektu gry (Game Object), wraz z jego parametrami i ścieżką do niego
 */
public class Image extends Component {
    public String pictureFile;  // ścieżka do obrazu
    public int width, height;   // szerokość i wysokość obrazu
    public BufferedImage image;     // plik z obrazem

    /**
     * Konstruktor parametryczny klasy Image
     * @param pictureFile ciąg znaków będący ścieżką do pliku z grafiką
     */
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

    /**
     * Konstruktor parametryczny klasy Image zawierający w sobie nie tylko ścieżkę, ale także obraz typu BufferedImage
     * @param image obraz typu BufferedImage
     * @param pictureFile ścieżka do pliku z obrazem
     */
    public Image(BufferedImage image, String pictureFile) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pictureFile = pictureFile;
    }

    /**
     * Metoda odpowiedzialna za rysowanie obrazu skojarzonego z obiektem gry (Game Object)
     * @param g2 grafika okna
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)gameObject.location.position.x, (int)gameObject.location.position.y,
                (int)(width*(this.gameObject.location.scale.x)), (int)(height*(this.gameObject.location.scale.y)), null);
    }

    /**
     * Metoda kopiująca obraz
     * @return nowy obraz taki sam, jak ten kopiowany
     */
    public Image copy(){
        return (new Image(this.image, this.pictureFile));
    }
}
