package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * The basic red fighter.
 */

public class morcegoShip extends Fighter {
    public morcegoShip(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 1;
        health  = 7;
        TextureRegion fighter = world.getAtlas().findRegion("morcegoShip"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        sprite.setScale(.5f);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
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
        velocity.add(0, -250);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
    }

    private void die() {
        world.powerups.add(new GreenPower(world, sprite.getX(), sprite.getY()));
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
