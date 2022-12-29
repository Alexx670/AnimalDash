package com.components;

import com.engine.Component;
import com.utillity.Constants;

import java.awt.Graphics2D;


public class Player extends Component {
    Image playerImage;
    public int width, height;

    public Player(Image playerImage) {
        this.playerImage = playerImage;
        this.width = Constants.PLAYER_WIDTH;
        this.height = Constants.PLAYER_HEIGHT;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(playerImage.image, (int)gameObject.location.position.x, (int)gameObject.location.position.y,
                this.width, this.height, null);
    }
}
