package com.blasters.game.gameworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.sprites.Player;

/**
 * This is the world. This is where everything happens during the game.
 * It maintains the current state of how the world currently is.
 */

public class GameWorld {
    Player player;
    public Texture playerTexture;

    public GameWorld() {
        playerTexture = new Texture("Blaster_player1.png");
        player = new Player(this);
    }

    public void update(float delta) {

    }

    public void dispose() {

    }
}
