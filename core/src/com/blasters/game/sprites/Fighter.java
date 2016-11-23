package com.blasters.game.sprites;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;


/**
 * Fighter
 * An abstract class that all fighters can follow including our hero ship and enemies.
 */

public abstract class Fighter{
    public int value;
    Vector2 velocity;
    GameWorld world;
    int health;
    public Sprite sprite;
    Random random;
    float x;
    float y;
    
    Fighter(GameWorld world) {
        this.world = world;
        velocity = new Vector2(0,0);
        random = new Random();
        defineFighter();
    }

    protected abstract void defineFighter();
    public abstract void update(float delta);
    public abstract void move(float delta);
}
