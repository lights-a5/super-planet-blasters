package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Adam on 11/24/2016.
 */

public class DordraxShip extends Fighter {
    public DordraxShip(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 3;
        health  = 12;
        TextureRegion fighter = world.getAtlas().findRegion("dordraxShip"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        sprite.setScale(.5f, .5f);
    }

    public void update(float delta) {
        velocity.add(0, -91);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);

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

    }

    private void die() {
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
