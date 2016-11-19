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
    private GameWorld world;
    private Vector2 velocity;
    public Sprite laserSprite;


    public Bullet(GameWorld world, float x, float y) {
        this.world = world;
        velocity = new Vector2(0, 0);
        TextureRegion bullet = world.getAtlas().findRegion("laserBlue01");
        laserSprite = new Sprite(bullet);
        laserSprite.rotate90(true);
        laserSprite.setScale(.01f,.9f);
        laserSprite.setPosition(x, y);
    }

    /*
     * kill
     * Currently, this only removes the current bullet from the bullet array in the game world.
     * Later on, we can instead play an explosion animation if it hits a ship.
     */
    void kill() {
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
        laserSprite.translate(velocity.x, velocity.y);
        if (laserSprite.getY() > Gdx.graphics.getHeight() + laserSprite.getRegionHeight()) {
            world.bullets.removeValue(this, true);
        }
        System.out.println("Bullet: " + laserSprite.getX());
    }
}
