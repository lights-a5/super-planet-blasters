package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
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
        laserSprite.setScale(.01f,.8f);
        laserSprite.setPosition(x, y);
    }


    /*
     * kill
     * Currently, this only removes the current bullet because it is a
     * sprite I don't know how to make it
     */
    void kill()
    {
        TextureRegion hit1 = world.getAtlas().findRegion("laserBlueHit1");
        TextureRegion hit2 = world.getAtlas().findRegion("laserBlueHit2");
        TextureRegion[] bulletHit = { hit1, hit2};

        Animation hitAnimation = new Animation(0.01f, bulletHit);
        hitAnimation.setPlayMode(Animation.PlayMode.NORMAL);

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
