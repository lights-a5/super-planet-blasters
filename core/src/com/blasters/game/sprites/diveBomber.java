package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Adam on 12/8/2016.
 * I wanted to make the angle similar to a bullet firing
 * I haven't had enough time to make the angle right. my commented
 * out things show what I was doing.
 */

public class diveBomber extends Fighter {
    public diveBomber(GameWorld world) {
        super(world);
    }

    //Random randomOffset = new Random();
   // float angle;

    public void defineFighter() {
        value = 1;
        health  = 1;
        speed = -480;
        //angle = randomOffset.nextFloat();
        TextureRegion fighter = world.getAtlas().findRegion("draxbomberShip"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
        sprite.setScale(.5f, .5f);

    }

    public void update(float delta) {
        move(delta);

        if (sprite.getY() + sprite.getHeight() < 0) {
            world.enemies.removeValue(this, true);
        }
        else if (sprite.getBoundingRectangle().overlaps(world.player.sprite.getBoundingRectangle())) {
            die();
        }
        for (Bullet bullet : world.bullets) {
            if (sprite.getBoundingRectangle().overlaps(bullet.laserSprite.getBoundingRectangle())) {
                health--;
                //bullet
                bullet.kill();
                if(health <= 0) {
                    die();
                }
            }
        }

    }

    @Override
    public void move(float delta) {
        velocity.add(0, speed);
        velocity.scl(delta);

        sprite.translate(velocity.x, velocity.y);
    }

    private void fireBullet() {
        //will not fire just fly toward player
    }

    public void die() {
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
