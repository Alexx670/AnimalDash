package com.engine;

import com.components.*;
import com.structure.Assets;
import com.structure.Location;
import com.utillity.Constants;
import com.utillity.Vector2;

import java.awt.Graphics2D;

/**
 * Klasa odpowiadająca za generację i obsługę poziomów gry
 */
public class LevelScene extends Scene{
    // wartości dostępne publicznie jako pola klasy ze względu na ich częste wykorzystanie
    public GameObject player;   // obiekt gracza
    public Box playerBox;   // box gracza
    public int score;   // aktualny wynik
    public int level;   // obecny poziom
    public boolean paused;  // czy gra jest zapauzowana

    // ścieżki do plików z grafikami
    String playerImage = "assets/krowcia4.png";
    String groundImage = "assets/ground_4.png";
    String backgroundImage = "assets/background.png";
    String milk = "assets/milk.png";
    String water = "assets/water1.png";
    String meat = "assets/meat.png";
    String hay = "assets/hay.png";
    String grass = "assets/grass1.png";
    String ladybug = "assets/ladybug.png";

    // ścieżki do napisów
    String scoreText = "assets/score.png";
    String levelText = "assets/level.png";
    String menuImage = "assets/menu.png";

    // tabela ze ścieżkami do grafik przedmiotów do poziomu 1
    String[] props1 = {milk, water, meat, hay, ladybug, grass};
    // tabela ze ścieżkami do grafik cyfr
    String[] numbers = {"assets/score_0.png", "assets/score_1.png", "assets/score_2.png", "assets/score_3.png"};

    int backgroundNumber = 3;   // ilość instancji tła do pokrycia obszaru gry
    GameObject[] backgroundTable = new GameObject[backgroundNumber];    // tabela z tłami
    GameObject[] groundTable = new GameObject[backgroundNumber];    // tabela z podłożami

    /**
     * Konstruktor klasy LevelScene
     * @param name nazwa instancji klasy
     * @param level numer generowanego poziomu
     */
    public LevelScene(String name, int level) {
        // konstruktor klasy nadrzędnej
        super.Scene(name);
        // gra domyślnie nie jest zapauzowana
        paused = false;
        // przypisz poziom tylko jeśli jest on większy od 0
        if (level > 0) {
            this.level = level;
        }   // w przeciwnym wypadku ustal poziom na 1
        else this.level = 1;
    }

    /**
     * metoda inicjalizująca warunki początkowe klasy, jest odpowiedzialna za inicjalizację zasobów i obiektów gry
     */
    @Override
    public void init() {
        this.score = 0; // wynik początkowy to 0

        initAssets();
        initBacground();

        generateLevel(this.level);

        // gracz
        player = new GameObject("Player", new Location(new Vector2(100,200), new Vector2(1.0f, 1.0f)));
        Player playerComp = new Player(Assets.getImage(playerImage).copy());
        player.addComponent(playerComp);
        player.addComponent(new Physics(new Vector2(Constants.X_VELOCITY, 0)));  // 395
        player.addComponent(new Box(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        playerBox = player.getComponent(Box.class);

        rendering.send(player);

        GameObject score = new GameObject("Score",
                new Location(new Vector2(Constants.SCORE_OFFSET_X, Constants.LEVEL_OFFSET_Y), new Vector2(1.0f, 1.0f)));
        score.addComponent(new Score(scoreText, numbers));
        addGameObject(score);

        GameObject levelGameObject = new GameObject("Level",
                new Location(new Vector2(Constants.LEVEL_OFFSET_X, Constants.LEVEL_OFFSET_Y), new Vector2(1.0f, 1.0f)));
        levelGameObject.addComponent(new Level(levelText, numbers));
        levelGameObject.getComponent(Level.class).setCurrentLevel(level);
        addGameObject(levelGameObject);
    }

    /**
     * Inicjalizacja zasobów gry
     */
    public void initAssets() {
        Assets.addImage(playerImage, new Image(playerImage));
        Assets.addImage(groundImage, new Image(groundImage));
    }

    /**
     * Inicjalizacja tła i podłoża gry
     */
    public void initBacground() {
        // podłoże
        GameObject ground;
        ground = new GameObject("Ground", new Location(new Vector2(0, Constants.GROUND_Y), new Vector2(1.0f, 1.0f)));
        ground.addComponent(new Ground());
        addGameObject(ground);

        for (int i = 0; i < backgroundNumber; i++) {
            // tworzenie instancji tła
            MovingBackground movingBackground = new MovingBackground(backgroundImage, backgroundTable, ground.getComponent(Ground.class),
                    false);
            int x0 = i * movingBackground.width;
            int y0 = -220;

            GameObject gameObject = new GameObject("Background" + i, new Location(new Vector2(x0, y0), new Vector2(1.0f, 1.0f)));
            gameObject.setUI(true);
            gameObject.addComponent(movingBackground);
            backgroundTable[i] = gameObject;

            // tworzenie instancji podłoża
            MovingBackground movingGround = new MovingBackground(groundImage, groundTable, ground.getComponent(Ground.class), true);
            x0 = i * movingGround.width;
            y0 = movingBackground.height-220;
            GameObject groundGameObject = new GameObject("Ground" + 1, new Location(new Vector2(x0, y0), new Vector2(1.0f, 1.0f)));
            groundGameObject.addComponent(movingGround);
            groundGameObject.setUI(true);
            groundTable[i] = groundGameObject;

            // dodaj utworzone podłoże i tło
            addGameObject(gameObject);
            addGameObject(groundGameObject);
        }

    }

    /**
     *  Metoda generująca poziom 1
     */
    public void generateLevelOne() {
        GameObject platform;
        float platformWidth;
        float platformHeight;
        Vector2[] levelMatrix = new Vector2[Constants.LEVEL_SIZE];

        // platformy
        levelMatrix[0] = new Vector2(1000, 300);
        levelMatrix[1] = new Vector2(1500, 50);
        levelMatrix[2] = new Vector2(2500, 300);
        levelMatrix[3] = new Vector2(2950, 300);
        levelMatrix[4] = new Vector2(2950, 50);
        levelMatrix[5] = new Vector2(3500, 300);
        levelMatrix[6] = new Vector2(4000, 50);
        levelMatrix[7] = new Vector2(4600, 50);
        levelMatrix[8] = new Vector2(5000, 300);
        levelMatrix[9] = new Vector2(5500, 50);
        for (int i = 0; i < Constants.LEVEL_SIZE; i++) {
            platform = new GameObject(("Platform" + i), new Location(levelMatrix[i], new Vector2(0.25f, 0.25f)));
            platform.addComponent(Assets.getImage(groundImage).copy());
            platformWidth  = platform.getComponent(Image.class).width * platform.location.scale.x;
            platformHeight = platform.getComponent(Image.class).height * platform.location.scale.y;
            platform.addComponent(new Box(platformWidth, platformHeight));
            addGameObject(platform);
        }

        // przedmioty
        GameObject prop;
        Vector2[]  propMatrix = new Vector2[Constants.PROP_COUNT];
        propMatrix[0] = new Vector2(1600, -30);
        propMatrix[1] = new Vector2(1600, 400);
        propMatrix[2] = new Vector2(3050, 200);
        propMatrix[3] = new Vector2(3050, -30);
        propMatrix[4] = new Vector2(5100, 200);
        propMatrix[5] = new Vector2(5100, 400);
        for (int i = 0; i < Constants.PROP_COUNT; i++) {
            prop = new GameObject(("Prop" + i), new Location(propMatrix[i], new Vector2(Constants.PROP_SCALE, Constants.PROP_SCALE)));
            prop.addComponent(Assets.getImage(props1[i]).copy());
            prop.addComponent(new Box(Constants.PROP_DIMENSION, Constants.PROP_DIMENSION));
            prop.getComponent(Box.class).setTransparent(true);
            if (i % 2 == 1) {
                prop.getComponent(Box.class).setGoodProp(true);
            }
            addGameObject(prop);
        }
    }

    /**
     * Metoda generująca drugi poziom
     */
    public void generateLevelTwo() {
        GameObject platform;
        float platformWidth;
        float platformHeight;
        Vector2[] levelMatrix = new Vector2[Constants.LEVEL_SIZE];

        // platformy
        levelMatrix[0] = new Vector2(1000, 300);
        levelMatrix[1] = new Vector2(1500, 50);
        levelMatrix[2] = new Vector2(2500, 300);
        levelMatrix[3] = new Vector2(2950, 300);
        levelMatrix[4] = new Vector2(2950, 50);
        levelMatrix[5] = new Vector2(3500, 300);
        levelMatrix[6] = new Vector2(4000, 50);
        levelMatrix[7] = new Vector2(4600, 50);
        levelMatrix[8] = new Vector2(5000, 300);
        levelMatrix[9] = new Vector2(5500, 50);
        for (int i = 0; i < Constants.LEVEL_SIZE; i++) {
            platform = new GameObject(("Platform" + i), new Location(levelMatrix[i], new Vector2(0.25f, 0.25f)));
            platform.addComponent(Assets.getImage(groundImage).copy());
            platformWidth  = platform.getComponent(Image.class).width * platform.location.scale.x;
            platformHeight = platform.getComponent(Image.class).height * platform.location.scale.y;
            platform.addComponent(new Box(platformWidth, platformHeight));
            addGameObject(platform);
        }

        // przedmioty
        GameObject prop;
        Vector2[]  propMatrix = new Vector2[Constants.PROP_COUNT];
        propMatrix[0] = new Vector2(1600, -30);
        propMatrix[1] = new Vector2(1600, 400);
        propMatrix[2] = new Vector2(3050, 200);
        propMatrix[3] = new Vector2(3050, -30);
        propMatrix[4] = new Vector2(5100, 200);
        propMatrix[5] = new Vector2(5100, 400);
        for (int i = 0; i < Constants.PROP_COUNT; i++) {
            prop = new GameObject(("Prop" + i), new Location(propMatrix[i], new Vector2(Constants.PROP_SCALE, Constants.PROP_SCALE)));
            prop.addComponent(Assets.getImage(props1[i]).copy());
            prop.addComponent(new Box(Constants.PROP_DIMENSION, Constants.PROP_DIMENSION));
            prop.getComponent(Box.class).setTransparent(true);
            if (i % 2 == 1) {
                prop.getComponent(Box.class).setGoodProp(true);
            }
            addGameObject(prop);
        }
    }

    /**
     * Metoda wybierająca poziom do generacji
     * @param levelNumber   numer generowanego poziomu
     */
    public void generateLevel (int levelNumber) {

        if (levelNumber == 1) {
            // generuj poziom 1
            generateLevelOne();
        }
        else if (levelNumber == 2) {
            // generuj Poziom 2
            generateLevelTwo();
        }
        else {
            // błąd, nie powinno dojść do takiej sytuacji
            System.exit(-1);
        }
    }

    /**
     * Metoda aktualizująca dane wszystkich wyświetlanych obiektów
     * @param dt odstęp czasowy między kolejnymi wywołaniami funkcji
     */
    @Override
    public void update(double dt) {
        // jeśli gra nie jest zapauzowana
        if (!paused) {
            // kamera śledzi gracza podążając za nim po osi x tak, żeby nie był on bliżej krawedzi ekranu niż wynosi CAMERA_OFFSET_X
            if (player.location.position.x - camera.position.x > Constants.CAMERA_OFFSET_X) {
                camera.position.x = player.location.position.x - Constants.CAMERA_OFFSET_X;
            }

            // kamera śledzi gracza podążając za nim po osi y tak, żeby gracz był stale na tej samej wysokości CAMERA_OFFSET_Y na ekranie
            camera.position.y = player.location.position.y - Constants.CAMERA_OFFSET_Y;

            // kamera nie może zjechać niżej na osi y niż wynosi CAMERA_OFFSET_GROUND_Y
            if (camera.position.y > Constants.CAMERA_OFFSET_GROUND_Y) {
                camera.position.y = Constants.CAMERA_OFFSET_GROUND_Y;
            }

            // zaktualizuj pozycję gracza i domyślnie ustaw, że nie może on skakać
            player.update(dt);
            player.getComponent(Player.class).canJump = false;

            score = 0;  // zerowanie wyniku do jego ponownego obliczenia

            for (GameObject g : gameObjectList) {
                // zaktualizuj parametry obiektów gry
                g.update(dt);

                Box box = g.getComponent(Box.class);
                if (box != null) {
                    // sprawdzanie kolizji gracza z obiektami w grze
                    if (Box.checkCollision(playerBox, box)) {
                        box.handleCollision(player, backgroundTable, groundTable, backgroundNumber, dt);
                    }
                    // sprawdzenie, ile razy gracz zderzył się z punktowanymi obiektami
                    if (box.getCollided()) {
                        score += 1;     // ustalenie aktualnego wyniku
                    }
                }

                // zaktualizuj stan wyświetlania wyniku gry
                Score score1 = g.getComponent(Score.class);
                if (score1 != null) {
                    score1.setScore(score);
                }

            }
        }
        else {

        }
    }

    /**
     * Metoda rysująca grafiki w oknie poziomu
     */
    @Override
    public void draw(Graphics2D g2) {
        // jeśli gra nie jest zapauzowana
        if (!paused) {
            // renderuj
            rendering.render(g2);
        }
        else {
            // w przeciwnym wypadku rysuj menu gry
            //g2.drawImage(Assets.getImage(menuImage).copy().image, Constants.MENU_OFFSET_X, Constants.MENU_OFFSET_Y, Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
        }
    }
}
