package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Adam on 11/24/2016.
 */

class DordraxShip extends Fighter {
    DordraxShip(GameWorld world) {
        super(world);
    }

    private Animation deathAnimation;
    private boolean isDead;
    private float deathTimer;

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
        if (!isDead) {
            world.bgen.genPattern(sprite.getX() + sprite.getRegionWidth() / 4, sprite.getY(), EnemyBulletGenerator.patternType.DIR8);
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

        deathAnimation = new Animation(.3f, frames, Animation.PlayMode.NORMAL);

    }


}
