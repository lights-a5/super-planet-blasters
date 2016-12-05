package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by SHELIVES on 11/3/2016.
 */

public class bansheeShip extends Fighter {
    private TextureRegion fighter;

    public bansheeShip(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 2;
        health  = 10;
        fighter = world.getAtlas().findRegion("bansheeShip");
        sprite = new Sprite(fighter);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
    }

    public void update(float delta) {
        velocity.add(0, -100);
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
                bullet.kill();
                if(health <= 0) {
                    die();
                    Gdx.app.log("bansheeShip", "killed");
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
