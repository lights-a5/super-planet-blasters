package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
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
        speed = -91;
        TextureRegion fighter = world.getAtlas().findRegion("dordraxShip"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
        sprite.setScale(.5f, .5f);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
        bulletDelay = 3f;
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
        currentDelay += delta;
        if (currentDelay >= bulletDelay) {
            currentDelay = 0f;
            fireBullet();
        }
    }

    @Override
    public void move(float delta) {
        velocity.add(0, -190);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);

    }

    private void fireBullet() {
        world.bgen.genPattern(sprite.getX() + sprite.getRegionWidth() / 4, sprite.getY(), EnemyBulletGenerator.patternType.DIR8);
    }

    private void die() {
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
