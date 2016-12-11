package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by SHELIVES on 11/3/2016.
 */

public class bansheeShip extends Fighter {
    private TextureRegion fighter;
    private Animation deathAnimation;
    private boolean isDead;
    private float deathTimer;

    public bansheeShip(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 2;
        health  = 10;
        speed = -100;
        fighter = world.getAtlas().findRegion("bansheeShip");
        sprite = new Sprite(fighter);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
        bulletDelay = 1.2f;
        setBadGuyDeath();
    }

    public void update(float delta) {
        move(delta);
        if (isDead) {
            deathTimer += delta;
            sprite.setRegion(deathAnimation.getKeyFrame(deathTimer));
            if (deathAnimation.isAnimationFinished(deathTimer))
            {
                world.enemies.removeValue(this, true);
            }
        }

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
        currentDelay += delta;
        if (currentDelay >= bulletDelay) {
            currentDelay = 0;
            fireBullet();
        }

    }

    @Override
    public void move(float delta) {
        velocity.add(0, speed);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);

    }

    private void fireBullet() {
        if (!isDead) {
            world.bgen.genPattern(sprite.getX() + sprite.getRegionWidth() / 4, sprite.getY(), EnemyBulletGenerator.patternType.HOMING);
        }
    }

    public void die() {
        isDead = true;
        world.hud.addScore(value);

    }

    public void setBadGuyDeath() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(world.getAtlas().getTextures().first(), i * 103 + 11, 82, 103, 84));
        }

        deathAnimation = new Animation(.19f, frames, Animation.PlayMode.NORMAL);

    }
}
