package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.gameworld.GameWorld;


/**
 * Bullet
 * This class is used to hold the main bullets that the ship fires. Later on, I expect that
 * we will make other ways for the ships to be hit. Whether or not they will use the bullet
 * class is up in the air.
 */

public class Bullet {
    TextureRegion bullet;
    protected GameWorld world;
    public Vector2 velocity;
    public Sprite sprite;


    public Bullet(GameWorld world, float x, float y) {
        this.world = world;
        velocity = new Vector2(0, 0);
        bullet = world.getAtlas().findRegion("paper_starfighterFire01");
        sprite = new Sprite(bullet);
        sprite.setRegion(bullet);
        sprite.setPosition(x, y);
        sprite.setScale(.2f, .2f);
    }

    /*
     * kill
     * Currently, this only removes the current bullet from the bullet array in the game world.
     * Later on, we can instead play an explosion animation if it hits a ship.
     */
    public void kill() {
        world.bullets.removeValue(this, true);
    }

    /*
     * update
     * This allows the bullet to know how to move itself given it's current velocity. Currently,
     * it is adding a velocity of 500. Honestly, if the velocity is always going to be the same,
     * we should probably move it into it's own variable so that it is easier to change in the
     * future.
     */
    public void update(float delta) {
        velocity.add(0, 500);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
        if (sprite.getY() > Gdx.graphics.getHeight() + sprite.getRegionHeight()) {
            world.bullets.removeValue(this, true);
        }
    }
}
