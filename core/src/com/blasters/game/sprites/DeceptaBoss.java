package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Adam on 11/27/2016.
 * Moves down then side to side has it's own fire pattern
 */

class DeceptaBoss extends Fighter {

    private boolean moveRight;
    private boolean isShooting;
    private float shootDelay;
    private float shootPauseTime;
    private Animation deathAnimation;
    private boolean isDead;
    private float deathTimer;

    DeceptaBoss(GameWorld world) {
        super(world);
    }

    protected void defineFighter() {
        shootPauseTime = 0;
        isDead = false;
        deathTimer = 0;
        isShooting = false;
        moveRight = false;
        value = 100;
        health = 570;
        TextureRegion fighter = world.getAtlas().findRegion("deceptaBoss"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        sprite.setPosition(250, 900);
        bulletDelay = .4f;
        shootDelay = 4.0f;
        setBadGuyDeath();
        //sprite.setScale(.5f, .5f);
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

        for (Bullet bullet : world.bullets) {
            if (sprite.getBoundingRectangle().overlaps(bullet.laserSprite.getBoundingRectangle())) {
                health--;
                if(!isDead){
                    bullet.kill();}
                if (health <= 0) {
                    die();
                    Gdx.app.log("DeceptaBoss", "killed");
                }

            }

        }
        shootPauseTime += delta;
        if (shootPauseTime >= shootDelay) {
            isShooting = true;
            shootPauseTime = 0;
        }

        if (isShooting) { //please take a look at my logic, i was trying to make it pause after a bit
            currentDelay += delta;
            if (currentDelay >= bulletDelay) {
                currentDelay = 0;
                fireBullet();
            }
            if (shootPauseTime >= shootDelay * 2) {
                isShooting = false;
                shootPauseTime = 0;
            }
        }


    }

    private void fireBullet() {
        world.bgen.genPattern(sprite.getX() + sprite.getRegionWidth() / 4, sprite.getY(), EnemyBulletGenerator.patternType.DECBOSS);
    }

    @Override
    public void move(float delta) {
        Gdx.app.log("spriteOriginY: ", Float.toString(sprite.getX()));
        if (sprite.getY() > Gdx.graphics.getHeight() * .6) {
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

    }

    public void die() {
        //animate death
        if(!isDead){world.hud.addScore(value);}
       isDead = true;
    }
    public void setBadGuyDeath() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(world.getPlayerAtlas().findRegion("deceptaBoss_die1")));
            frames.add(new TextureRegion(world.getPlayerAtlas().findRegion("deceptaBoss_die2")));
            frames.add(new TextureRegion(world.getPlayerAtlas().findRegion("deceptaBoss_die3")));
        }

        deathAnimation = new Animation(.15f, frames, Animation.PlayMode.NORMAL);

    }
}
