package com.utillity;

import com.components.Image;

public class Constants {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final String SCREEN_TITLE = "Animal Dash";

    public static final int PLAYER_WIDTH = 150;
    public static final int PLAYER_HEIGHT = 100;
    public static final int PLAYER_OVERLAP = 0;

    public static final int GROUND_Y = 500;     // 700
    public static final int CAMERA_OFFSET_X = 250;  // 300
    public static final int CAMERA_OFFSET_Y = 450;  //325
    public static final int CAMERA_OFFSET_GROUND_Y = 150;   // 150

    public static final float GRAVITY = 2000;   // 2850
    public static final float MAX_VELOCITY = 1800;  //1900
    public static final float X_VELOCITY = 290;
    public static final float JUMP_VELOCITY = -1000;

    // stałe dotyczące poziomu
    public static final int LEVEL_COUNT = 2;
    public static final int LEVEL_SIZE = 10;
    public static final int LEVEL_LENGTH = 6000;
    public static final int LEVEL_MAX_POINTS = 3;
    public static final int PROP_COUNT = 6;
    public static final int PROP_DIMENSION = 80;
    public static final float PROP_SCALE = (float)(80.0/500.0);

    public static final int TEXT_WIDTH = 160;
    public static final int TEXT_HEIGHT = 80;
    public static final int NUMBER_OFFSET_X= 100;

    // pozycje napisów podczas trwania poziomu
    public static final int SCORE_OFFSET_X = 1000;
    public static final int SCORE_OFFSET_Y = 50;
    public static final int LEVEL_OFFSET_X = 40;
    public static final int LEVEL_OFFSET_Y = 50;
    public static final int MENU_OFFSET_X = 1000;
    public static final int MENU_OFFSET_Y = 600;
    public static final int QUESTION_OFFSET_X = 100;
    public static final int QUESTION_OFFSET_Y = 150;

    // pozycje napisów na ekranie startowym
    public static final int START_OFFSET_X = 570;
    public static final int START_OFFSET_Y = 30;
    public static final int PLAY_OFFSET_Y = 220;
    public static final int EXIT_OFFSET_Y = 350;

    // pozycje napisów menu podczas wstrzymanej gry
    public static final int MENU_ITEM_OFFSET_X = 550;
    public static final int MENU_ITEM_OFFSET_Y = 150;
    public static final int MENU_ITEM_DISTANCE_X = 45;
    public static final int MENU_ITEM_DISTANCE_Y = 90;

    // pozycje elementów sceny końca poziomu
    public static final int LEVEL_END_OFFSET_X = 400;
    public static final int LEVEL_END_OFFSET_Y = 150;
    public static final int LEVEL_END_DISTANCE_Y = 130;


}
