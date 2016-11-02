package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Tamara on 11/1/2016.
 */

public class Player extends Ship {
    public enum State {IDLE, LEFT, RIGHT}
    private Texture left;
    private Texture right;
    private Texture idle;
    public Player(GameWorld world) {
        idle = world.playerTexture;
        setRegion(idle);
        setPosition(100, 100);
    }

}
