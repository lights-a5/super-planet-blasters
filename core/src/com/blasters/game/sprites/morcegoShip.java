package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
        sprite.setScale(.5f, .5f);
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
        Sprite s = sprite; //Get current enemy's sprite
        float targetX = Gdx.graphics.getWidth() / 2; //Player's X
        float targetY = -200; //Player's Y
        float spriteX = s.getX(); //Enemy's X
        float spriteY = s.getY(); //Enemy's Y
        float x2 = s.getX(); //Enemy's new X
        float y2 = s.getY(); //Enemy's new Y
        float angle; // We use a triangle to calculate the new trajectory
        angle = (float) Math
                .atan2(targetY - spriteY, targetX - spriteX);
        sprite.setRotation(angle);
        x2 += (float) Math.cos(angle) * 400
                * Gdx.graphics.getDeltaTime();
        y2 += (float) Math.sin(angle) * 400
                * Gdx.graphics.getDeltaTime();
        s.setPosition(x2, y2); //Set enemy's new positions.
    }

    private void die() {
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
