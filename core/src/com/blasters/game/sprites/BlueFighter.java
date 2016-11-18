package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by SHELIVES on 11/3/2016.
 */

public class BlueFighter extends Fighter {
    TextureRegion fighter;

    public BlueFighter(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 2;
        health  = 4;
        fighter = world.getAtlas().findRegion("stingrayShip");
        sprite = new Sprite(fighter);
    }

    public void update(float delta) {
        velocity.add(0, -500);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);

        if (sprite.getY() + sprite.getHeight() < 0) {
            world.enemies.removeValue(this, true);
        }
        else if (sprite.getBoundingRectangle().overlaps(world.player.sprite.getBoundingRectangle())) {
            die();
        }
        for (Bullet bullet : world.bullets) {
            if (sprite.getBoundingRectangle().overlaps(bullet.sprite.getBoundingRectangle())) {
                health--;
                bullet.kill();
                if(health <= 0) {
                    die();
                    Gdx.app.log("BlueFighter", "killed");
                }

            }
        }
    }

    private void die() {
        world.screen.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
