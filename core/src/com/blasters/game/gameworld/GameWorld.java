package com.blasters.game.gameworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This is the world. This is where everything happens during the game.
 * It maintains the current state of how the world currently is.
 */

public class GameWorld {
    public Sprite player;

    public GameWorld() {
        player = new Sprite(new Texture("Blaster_player1.png"));
        player.setPosition(100, 100);
    }

    public void update(float delta) {

    }

    public void dispose() {

    }
}
