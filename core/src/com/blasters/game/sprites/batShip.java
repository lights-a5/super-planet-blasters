package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.blasters.game.gameworld.GameWorld;

/**
 * The basic red fighter.
 */

public class batShip extends Fighter {
    public batShip(GameWorld world) {
        super(world);
    }
    private Animation deathAnimation;
    private boolean isDead;
    private float deathTimer;


    public void defineFighter() {
        value = 1;
        health  = 4;
        speed = -181;
        TextureRegion fighter = world.getAtlas().findRegion("morcegoShip"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        sprite.setScale(.5f, .5f);
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

    private void fireBullet() {
        if (!isDead) {
            world.bgen.genPattern(sprite.getX() + sprite.getRegionWidth() / 4, sprite.getY(), EnemyBulletGenerator.patternType.FORWARD);
        }
    }

    @Override
    public void move(float delta) {

        velocity.add(0, speed);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
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

        deathAnimation = new Animation(.1f, frames, Animation.PlayMode.NORMAL);

    }
}
