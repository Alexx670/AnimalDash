package com.engine;

import com.components.*;
import com.components.Image;
import com.components.Menu;
import com.structure.Assets;
import com.structure.Location;
import com.utillity.Constants;
import com.utillity.Vector2;

import java.awt.*;

/**
 * Klasa odpowiadająca za generację i obsługę poziomów gry.
 * Dziedziczy po klasie Scene.
 */
public class LevelScene extends Scene{
    // wartości dostępne publicznie jako pola klasy ze względu na ich częste wykorzystanie
    public GameObject player;   // obiekt gracza
    public Box playerBox;   // box gracza
    public int score;   // aktualny wynik
    public int level;   // obecny poziom
    public boolean paused;  // czy gra jest zapauzowana

    // ścieżki do plików z grafikami
    String playerImage1 = "assets/krowcia4.png";
    String groundImage = "assets/ground_4.png";
    String backgroundImage = "assets/background.png";
    String milk = "assets/milk.png";
    String water = "assets/water1.png";
    String meat = "assets/meat.png";
    String hay = "assets/hay.png";
    String grass = "assets/grass1.png";
    String ladybug = "assets/ladybug.png";
    String cloud = "assets/cloud.png";
    String playerImage2 = "assets/wolf1.png";
    String elephant = "assets/elephant.png";
    String deer = "assets/deer.png";
    String forest = "assets/forest.png";
    String desert = "assets/desert.png";


    // ścieżki do napisów
    String scoreText = "assets/score.png";
    String levelText = "assets/level.png";
    String menuImage = "assets/menu.png";
    String question1 = "assets/question1.png";
    String question2 = "assets/question2.png";
    String question3 = "assets/question3.png";
    String question4 = "assets/question4.png";
    String resume = "assets/resume1.png";
    String repeat = "assets/repeat.png";
    String exit = "assets/exit.png";

    // tabela ze ścieżkami do grafik przedmiotów do poziomu 1
    String[] props1 = {milk, water, meat, hay, ladybug, grass};

    // tabela ze ścieżkami do grafik przedmiotów do poziomu 2
    String[] props2 = {grass, meat, elephant, deer, desert, forest};

    // tabela ze ścieżkami do grafik cyfr
    String[] numbers = {"assets/score_0.png", "assets/score_1.png", "assets/score_2.png", "assets/score_3.png"};

    int backgroundNumber = 3;   // ilość instancji tła do pokrycia obszaru gry
    GameObject[] backgroundTable = new GameObject[backgroundNumber];    // tabela z tłami
    GameObject[] groundTable = new GameObject[backgroundNumber];    // tabela z podłożami

    /**
     * Konstruktor parametryczny klasy LevelScene
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

        Window.getWindow().currentLevel = this.level;
    }

    /**
     * Metoda inicjalizująca warunki początkowe klasy, jest odpowiedzialna za inicjalizację zasobów i obiektów gry
     */
    @Override
    public void init() {
        this.score = 0; // wynik początkowy to 0

        initBacground();    // inicjalizacja tła i podłoża

        generateLevel(this.level);  // generowanie poziomu

        // dodanie wyświetlania aktualnego wyniku
        GameObject score = new GameObject("Score",
                new Location(new Vector2(Constants.SCORE_OFFSET_X, Constants.LEVEL_OFFSET_Y), new Vector2(1.0f, 1.0f)));
        score.addComponent(new Score(scoreText, numbers));
        addGameObject(score);

        // dodanie wyświetlania aktualnego poziomu
        GameObject levelGameObject = new GameObject("Level",
                new Location(new Vector2(Constants.LEVEL_OFFSET_X, Constants.LEVEL_OFFSET_Y), new Vector2(1.0f, 1.0f)));
        levelGameObject.addComponent(new Level(levelText, numbers));
        levelGameObject.getComponent(Level.class).setCurrentLevel(level);
        addGameObject(levelGameObject);

        // dodanie wyświetlania przycisku menu
        GameObject menuGameObject = new GameObject("Menu",
                new Location(new Vector2(Constants.MENU_OFFSET_X, Constants.MENU_OFFSET_Y), new Vector2(1.0f, 1.0f)));
        menuGameObject.addComponent(new Menu(menuImage));
        addGameObject(menuGameObject);

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
        levelMatrix[3] = new Vector2(2900, 300);
        levelMatrix[4] = new Vector2(2900, 50);
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
        propMatrix[2] = new Vector2(3000, 200);
        propMatrix[3] = new Vector2(3000, -30);
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

        // utworzenie obiektu gracza i wysłanie go do renderowania
        player = new GameObject("Player", new Location(new Vector2(100,200), new Vector2(1.0f, 1.0f)));
        Player playerComp = new Player(Assets.getImage(playerImage1).copy());
        player.addComponent(playerComp);
        player.addComponent(new Physics(new Vector2(Constants.X_VELOCITY, 0)));  // 395
        player.addComponent(new Box(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        playerBox = player.getComponent(Box.class);

        rendering.send(player);

        // dodanie wyświetlania pytania
        GameObject questionGameObject = new GameObject("Question1",
                new Location(new Vector2(Constants.QUESTION_OFFSET_X, Constants.QUESTION_OFFSET_Y), new Vector2(0.3f, 0.3f)));
        questionGameObject.addComponent(new Question(cloud, question1));
        addGameObject(questionGameObject);
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
        levelMatrix[1] = new Vector2(1400, 300);
        levelMatrix[2] = new Vector2(1940, 50);
        levelMatrix[3] = new Vector2(2440, 300);
        levelMatrix[4] = new Vector2(2840, 50);
        levelMatrix[5] = new Vector2(2840, 300);
        levelMatrix[6] = new Vector2(4000, 300);
        levelMatrix[7] = new Vector2(4500, 300);
        levelMatrix[8] = new Vector2(5000, 50);
        levelMatrix[9] = new Vector2(5500, 300);
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
        propMatrix[0] = new Vector2(1500, 400);
        propMatrix[1] = new Vector2(1500, 200);
        propMatrix[2] = new Vector2(2940, 200);
        propMatrix[3] = new Vector2(2940, -30);
        propMatrix[4] = new Vector2(5100, -30);
        propMatrix[5] = new Vector2(5100, 400);
        for (int i = 0; i < Constants.PROP_COUNT; i++) {
            prop = new GameObject(("Prop" + i), new Location(propMatrix[i], new Vector2(Constants.PROP_SCALE, Constants.PROP_SCALE)));
            prop.addComponent(Assets.getImage(props2[i]).copy());
            prop.addComponent(new Box(Constants.PROP_DIMENSION, Constants.PROP_DIMENSION));
            prop.getComponent(Box.class).setTransparent(true);
            if (i % 2 == 1) {
                prop.getComponent(Box.class).setGoodProp(true);
            }
            addGameObject(prop);
        }

        // utworzenie obiektu gracza i wysłanie go do renderowania
        player = new GameObject("Player", new Location(new Vector2(100,200), new Vector2(1.0f, 1.0f)));
        Player playerComp = new Player(Assets.getImage(playerImage2).copy());
        player.addComponent(playerComp);
        player.addComponent(new Physics(new Vector2(Constants.X_VELOCITY, 0)));  // 395
        player.addComponent(new Box(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        playerBox = player.getComponent(Box.class);

        rendering.send(player);

        // dodanie wyświetlania pytania
        GameObject questionGameObject = new GameObject("Question2",
                new Location(new Vector2(Constants.QUESTION_OFFSET_X, Constants.QUESTION_OFFSET_Y), new Vector2(0.3f, 0.3f)));
        questionGameObject.addComponent(new Question(cloud, question2));
        addGameObject(questionGameObject);
    }

    /**
     * Metoda wybierająca poziom do generacji.
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
        Camera previousCamera = camera.copy();  // pozycja kamery w poprzedniej iteracji
        Window currentWindow = Window.getWindow();  // okno, w którym wyświetlana jest scena

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

            // dla każdego obiektu gry
            for (GameObject g : gameObjectList) {
                // zaktualizuj parametry obiektów gry
                g.update(dt);

                // sprawdź kolizje gracza z obiektami gry posiadającymi komponent Box
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

                // jeśli to poziom 2 to w określonych momentach nastąpi zmiana treści pytania
                if (level == 2 && g.getComponent(Question.class) != null) {
                    if (player.location.position.x > 1550 && player.location.position.x < 3000) {
                        g.getComponent(Question.class).setQuestion(Assets.getImage(question3).copy());
                    } else {
                        if (player.location.position.x > 3000) {
                            g.getComponent(Question.class).setQuestion(Assets.getImage(question4).copy());
                        }
                    }
                }

            }

            // czy naciśnięto przycisk "Menu"
            if (currentWindow.mouseListener.getMouseButton()) {
                // jeśli mysz jest na polu "Menu"
                if ((currentWindow.mouseListener.getMousePosition().x > Constants.MENU_OFFSET_X) &&
                        (currentWindow.mouseListener.getMousePosition().x < Constants.MENU_OFFSET_X + Constants.TEXT_WIDTH) &&
                        (currentWindow.mouseListener.getMousePosition().y > Constants.MENU_OFFSET_Y) &&
                        (currentWindow.mouseListener.getMousePosition().y < Constants.MENU_OFFSET_Y + Constants.TEXT_HEIGHT)) {
                    // jeśli tak - zapauzuj grę
                    this.paused = true;
                }
            }

            // warunek zakończenia poziomu
            if (player.location.position.x > Constants.LEVEL_LENGTH) {
                currentWindow.scores[this.level - 1] = this.score;  // przepisz wynik do głównej tabeli
                currentWindow.changeScene(Constants.LEVEL_COUNT + 2);

            }

        } else {    // jeśli gra jest zapauzowana

            if (currentWindow.mouseListener.getMouseButton()) {
                // ustaw flagę wciśnięcia przycisku na false (zapobiega podwójnym kliknięciom)
                currentWindow.mouseListener.setMouseButton(false);
                // jeśli mysz kliknięto pole "Wznów"
                if ((currentWindow.mouseListener.getMousePosition().x > Constants.MENU_ITEM_OFFSET_X + 30) &&
                        (currentWindow.mouseListener.getMousePosition().x < Constants.MENU_ITEM_OFFSET_X + Constants.TEXT_WIDTH + 70) &&
                        (currentWindow.mouseListener.getMousePosition().y > Constants.MENU_ITEM_OFFSET_Y + 20) &&
                        (currentWindow.mouseListener.getMousePosition().y < Constants.MENU_ITEM_OFFSET_Y + Constants.TEXT_HEIGHT)) {
                    // odpauzuj grę
                    this.paused = false;
                }
                else {
                    // jeśli kliknięto pole "Powtórz"
                    if ((currentWindow.mouseListener.getMousePosition().x > Constants.MENU_ITEM_OFFSET_X + 25) &&
                            (currentWindow.mouseListener.getMousePosition().x < Constants.MENU_ITEM_OFFSET_X + Constants.TEXT_WIDTH + 75) &&
                            (currentWindow.mouseListener.getMousePosition().y >
                                    Constants.MENU_ITEM_OFFSET_Y + Constants.MENU_ITEM_DISTANCE_Y + 40) &&
                            (currentWindow.mouseListener.getMousePosition().y <
                                    Constants.MENU_ITEM_OFFSET_Y + Constants.MENU_ITEM_DISTANCE_Y + Constants.TEXT_HEIGHT + 20)) {
                        // ponownie włącz obecny poziom
                        currentWindow.changeScene(this.level);
                    }
                    else {
                        // jeśli kliknięto pole "Wyjdź"
                        if ((currentWindow.mouseListener.getMousePosition().x > Constants.MENU_ITEM_OFFSET_X + 45) &&
                                (currentWindow.mouseListener.getMousePosition().x < Constants.MENU_ITEM_OFFSET_X + Constants.TEXT_WIDTH + 50) &&
                                (currentWindow.mouseListener.getMousePosition().y >
                                        Constants.MENU_ITEM_OFFSET_Y + 2*Constants.MENU_ITEM_DISTANCE_Y + 40) &&
                                (currentWindow.mouseListener.getMousePosition().y <
                                        Constants.MENU_ITEM_OFFSET_Y + 2*Constants.MENU_ITEM_DISTANCE_Y + Constants.TEXT_HEIGHT + 20)) {
                            // przejdź do sceny startowej
                            currentWindow.changeScene(0);
                        }
                    }
                }
            }
        }
    }

    /**
     * Metoda rysująca grafiki w oknie poziomu
     */
    @Override
    public void draw(Graphics2D g2) {
        // jeśli gra nie jest zapauzowana
        if (!paused) {
            // renderuj grę
            rendering.render(g2);
        }
        else {
            // w przeciwnym wypadku rysuj menu gry
            g2.setColor(Color.getHSBColor(100.0f, 100.0f, 100.0f));
            g2.fillRect(Constants.MENU_ITEM_OFFSET_X, Constants.MENU_ITEM_OFFSET_Y, 255, 300);
            g2.drawImage(Assets.getImage(resume).image, Constants.MENU_ITEM_OFFSET_X, Constants.MENU_ITEM_OFFSET_Y, null);
            g2.drawImage(Assets.getImage(repeat).image, Constants.MENU_ITEM_OFFSET_X,
                    Constants.MENU_ITEM_OFFSET_Y + Constants.MENU_ITEM_DISTANCE_Y, null);
            g2.drawImage(Assets.getImage(exit).image, Constants.MENU_ITEM_OFFSET_X + Constants.MENU_ITEM_DISTANCE_X,
                    Constants.MENU_ITEM_OFFSET_Y + 2*Constants.MENU_ITEM_DISTANCE_Y, null);
        }
    }
}
