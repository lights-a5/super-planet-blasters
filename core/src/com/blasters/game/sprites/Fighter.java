package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
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
    
    Fighter(GameWorld world) {
        this.world = world;
        velocity = new Vector2(0,0);
        defineFighter();
    }

    protected abstract void defineFighter();
    public abstract void update(float delta);
}
