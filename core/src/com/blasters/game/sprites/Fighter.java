package com.blasters.game.sprites;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;
import com.blasters.game.screens.GameScreen;


/**
 * Default fighter class
 */

public abstract class Fighter{
    public int value;
    public Vector2 velocity;
    protected GameWorld world;
    public int health;
    public Sprite sprite;
    Random random;
    float x;
    float y;
    float bulletDelay;
    float currentDelay;
    
    Fighter(GameWorld world) {
        this.world = world;
        currentDelay = 0f;
        velocity = new Vector2(0,0);
        random = new Random();
        defineFighter();
    }

    protected abstract void defineFighter();
    public abstract void update(float delta);
    public abstract void move(float delta);
}
