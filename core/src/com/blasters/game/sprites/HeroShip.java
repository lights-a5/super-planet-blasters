package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blasters.game.gameworld.GameWorld;

/**
 * HeroShip
 * A class designed to be the ship that the main player uses. As such, it has a lot of different
 * variables that other fighters don't have. The fighter, if hit, can also become invincible.
 * Currently there is no visible representation of invincibility.
 */

public class HeroShip extends Fighter {
    private final float INVINCIBLETIME = 2f;
    private float timeInvincible;
    private boolean invincible;
    private boolean isDead;
    public boolean bulletMid;
    public boolean bulletSides;
    public int shields;
    public float faster;
    public boolean blue;
    public boolean yellow;
    public boolean red;
    public boolean red2;
    public int maxHP;
    private Animation deathAnimation;
    private float deathTimer;

    public HeroShip(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 999999;
        sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster")));
        maxHP = 5;
        health = maxHP;
        invincible = false;
        timeInvincible = 0;
        isDead = false;
        sprite.setPosition(100, 100);
        sprite.setScale(((float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) / 2);
        bulletMid = true;
        bulletSides = false;
        shields = 0;
        faster = 0;
        blue = false;
        yellow = false;
        red = false;
        red2 = false;
        deathTimer = 0;
        setDeathAnimation();
    }

    private void setDeathAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(world.getAtlas().getTextures().first(), i * 103 + 11, 82, 103, 84));
        }

        deathAnimation = new Animation(.3f, frames, Animation.PlayMode.NORMAL);

    }

    public void update(float delta) {
        if (isDead) {
            deathTimer += delta;
            sprite.setRegion(deathAnimation.getKeyFrame(deathTimer));
        }
        else {
            checkInput();
            if (invincible) {
                timeInvincible += delta;
                if (timeInvincible >= INVINCIBLETIME) {
                    invincible = false;
                    timeInvincible = 0;
                }
            } else {
                for (Fighter enemy : world.enemies) {
                    if (sprite.getBoundingRectangle().overlaps(enemy.sprite.getBoundingRectangle()) && !invincible) {
                        adjustHealth();
                        enemy.die();
                        invincible = true;

                    }
                }
                for (EnemyBullet bullet : world.enemyBullets) {
                    if (sprite.getBoundingRectangle().overlaps(bullet.sprite.getBoundingRectangle()) && !invincible) {
                        adjustHealth();
                        invincible = true;
                        bullet.kill();
                    }
                }
                if (health <= 0) {
                    die();
                }
            }
        }
    }

    private void checkInput() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            sprite.setPosition(Gdx.input.getX() - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight() / 2);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
        }
    }
    public void die() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void move(float delta){

    }
    private void adjustHealth(){
        if(shields > 0){
            shields--;
        }
        else
            health--;
    }

    void determineColor(){
        float x = sprite.getX();
        float y = sprite.getY();
        if(blue){
            if(yellow){
                if(red){
                    sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_YBR1")));
                }
                else if(red2){
                    sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_YBR2")));
                }
                else{
                    sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_YB"))) ;
                }
            }
            else{
                if(red){
                    sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_BR1"))) ;
                }
                else if(red2){
                    sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_BR2")));
                }
                else{
                    sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_Shield1")));
                }
            }
        }
        else if(yellow){
            if(red){
                sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_YR1")));
            }
            else if(red2){
                sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_YR2")));
            }
            else{
                sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_GoldWings")));
            }
        }
        else if(red){
            sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_GunV1")));
        }
        else if(red2){
            sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster_GunV2")));
        }
        else{
            sprite = new Sprite(new TextureRegion(world.getPlayerAtlas().findRegion("PlanetBlaster")));
        }

        sprite.setScale(((float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) / 2);
        sprite.setPosition(x, y);
    }
    void addShield() {
        shields++;
    }

    public boolean isDeathDone() {
        return deathTimer > deathAnimation.getAnimationDuration();
    }

}
