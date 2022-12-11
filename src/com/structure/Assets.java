package com.structure;

import com.components.Image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// klasa zawierająca zasoby gry
public class Assets {
    // hashmapa zawierająca nazwy obrazów oraz pliki, w których są zapisane
    static Map<String, Image> images = new HashMap<>();


    // czy w zasobach znajduje się plik o takiej nazwie
    public static boolean hasImage(String pictureFile) {
        return Assets.images.containsKey(pictureFile);
    }

    // metoda zwracająca adres pliku o podanej nazwie
    public static Image getImage(String pictureFile) {
        File file = new File(pictureFile);
        if (Assets.hasImage(pictureFile)) {
            return Assets.images.get(file.getAbsolutePath().toString());
        } else  {
            Image image = new Image(pictureFile);
            Assets.addImage(pictureFile, image);
            return Assets.images.get(file.getAbsolutePath());
        }
    }

    /**
     *
     * @param pictureFile Absolute path to the picture
     * @param image
     */

    public static void addImage(String pictureFile, Image image) {
        File file = new File(pictureFile);
        if (!Assets.hasImage(file.getAbsolutePath())) {
            Assets.images.put(file.getAbsolutePath(), image);
        } else {
            System.out.println("Assets already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }
}
