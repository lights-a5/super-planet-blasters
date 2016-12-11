package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.gameworld.GameWorld;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Tyler on 11/22/2016.
 */

public abstract class Power {
    Vector2 velocity;
    GameWorld world;
    public Sprite sprite;
    Random random;
    float x;
    float y;

    Power(GameWorld world){
        this.world = world;
        velocity = new Vector2(0,0);
        random = new Random();
        definePower();
    }

    protected abstract void definePower();
    public abstract void update(float delta);
    public abstract void move(float delta);
}