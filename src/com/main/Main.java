package com.main;


import com.engine.Music;
import com.engine.Window;

/**
 * Klasa główna programu. Odpowiedzialna za rozpoczęcie dwóch wątków - gry i muzyki.
 */
public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0"); // wyłączenie skalowania aplikacji przez Windows

        Window window = Window.getWindow();     // utworzenie okna gry
        window.init();      // inicjalizacja okna gry

        Thread mainThread = new Thread(window); // utworzenie głównego wątku gry
        mainThread.start();     // rozpoczęcie głównego wątku gry

        // muzyka w tle
        String musicFile = "assets/music.wav";  // ścieźka do pliku z muzyką
        Music music = new Music(musicFile);
        Thread musicThread = new Thread(music);     // wątek grający muzykę
        musicThread.start();    // rozpoczęcie wątku z muzyką
    }
}

/*
***** Zasoby użyte w grze: *****

Tło: background.png
https://opengameart.org/content/backgrounds-for-2d-platformers

Mleko: milk.png
https://www.cleanpng.com/png-milk-drawing-carton-cartoon-4589829/

trawa: grass.png
https://www.kindpng.com/downpng/mwoTmb_pixel-ground-png-transparent-png/

krowcia: krowcia4.png
https://pngtree.com/freepng/cute-white-hand-painted-cartoon-cows_4183257.html

mięso: meat.png
https://www.pngaaa.com/detail/97407

siano: hay.png
https://www.citypng.com/png-download/stock/13320

woda: water1.png
glass of water clipart PNG Designed By nickfz from https://pngtree.com/freepng/glass-of-water-vector-illustration_5554624.html?sol=downref&id=bef

ziemia: ground_4.png
https://www.freepik.com/free-vector/computer-games-seamless-layers-background-set_4282636.htm#query=grasy%20game%20platform%20isolated&position=35&from_view=search&track=ais - Image by macrovector on Freepik
"http://www.freepik.com" - Designed by macrovector / Freepik

trawa: grass1.png
"https://lovepik.com/images/png-grass.html" - Grass Png vectors by Lovepik.com

biedronka: ladybug.png
https://www.pngwing.com/en/free-png-bbwkt/download?height=500

chmurka: cloud.png
https://www.pngwing.com/en/free-png-bzgkn

las: forest.png
https://www.clipartmax.com/download/m2i8G6A0d3H7H7d3_cartoonforestpicture3-forest-cartoon-no-background/

pustynia: desert.png
https://www.subpng.com/png-aofm8e/

sarenka: deer.png
https://www.subpng.com/png-ipwlzs/

słoń: elephant.png
https://www.pngfind.com/mpng/xJbhhR_cartoon-elephant-vector-elephant-cartoon-vector-png/

wilk: wolf.png
https://www.cleanpng.com/png-gray-wolf-wolf-walking-pack-clip-art-wolf-362319/preview.html

muzyka: music.wav
 	Journey by Roa | https://soundcloud.com/roa_music1031/
	Music promoted by https://www.chosic.com/free-music/all/
	Creative Commons CC BY 3.0
	https://creativecommons.org/licenses/by/3.0/

napisy:
Stworzone przy użyciu edytora Pixlr X na stronie: https://pixlr.com/pl/x/

 */
