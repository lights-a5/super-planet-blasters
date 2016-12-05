package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Adam on 11/27/2016.
 */

public class DeceptaBoss extends Fighter {

    boolean moveRight;

    public DeceptaBoss(GameWorld world) {
        super(world);
    }

    protected void defineFighter() {
        moveRight = false;
        value = 100;
        health  = 600;
        TextureRegion fighter = world.getAtlas().findRegion("deceptaBoss"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        sprite.setPosition(300, 900);
        //sprite.setScale(.5f, .5f);
    }


    public void update(float delta) {
        Gdx.app.log("spriteOriginY: ", Float.toString(sprite.getX()));
        if (sprite.getY() > Gdx.graphics.getHeight() / 2) {
            //move to the top of the screen
            velocity.add(0, -91);
        } else {
            if (!moveRight) {
                velocity.add(-50, 0);
                if (sprite.getX() <= 0) {
                    moveRight = true;
                }

            } else {
                velocity.add(50, 0);
                if (sprite.getX() >= Gdx.graphics.getWidth() - sprite.getWidth()) {
                    moveRight = false;
                }
            }

        }
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);

        for (Bullet bullet : world.bullets) {
            if (sprite.getBoundingRectangle().overlaps(bullet.laserSprite.getBoundingRectangle())) {
                health--;
                bullet.kill();
                if (health <= 0) {
                    die();
                    Gdx.app.log("DeceptaBoss", "killed");
                }

            }

        }
    }

    @Override
    public void move(float delta) {

    }

    private void die() {
        //animate death
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
