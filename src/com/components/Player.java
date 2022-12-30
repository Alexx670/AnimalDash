package com.components;

import com.engine.Component;
import com.engine.Window;
import com.utillity.Constants;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class Player extends Component {
    Image playerImage;
    private int width, height;
    public boolean canJump = true;

    public Player(Image playerImage) {
        this.playerImage = playerImage;
        this.width = Constants.PLAYER_WIDTH;
        this.height = Constants.PLAYER_HEIGHT;
    }

    @Override
    public void update(double dt) {
        if (canJump && Window.getWindow().keyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            addJumpVelocity();
            this.canJump = false;
        }
    }

    private void addJumpVelocity() {
        gameObject.getComponent(Physics.class).velocity.y = Constants.JUMP_VELOCITY;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(playerImage.image, (int)gameObject.location.position.x, (int)gameObject.location.position.y,
                this.width, this.height, null);
    }
}
