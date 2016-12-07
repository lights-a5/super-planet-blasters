package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private int shields;
    public boolean faster;
    public int maxHP;

    private enum State{ BLUE, GREEN, ORANGE, RED}
    State colorState;

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
        sprite.setScale(((float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight()));
        bulletMid = true;
        bulletSides = false;
        shields = 0;
        faster = false;
    }
    /*
    public void changeColor() {
        switch (colorState) {
            case BLUE:
                sprite.setRegion(redHero);
                colorState = State.RED;
                break;
            case RED:
                sprite.setRegion(orangeHero);
                colorState  = State.ORANGE;
                break;
            case ORANGE:
                sprite.setRegion(greenHero);
                colorState = State.GREEN;
                break;
            case GREEN:
            default:
                sprite.setRegion(hero);
                colorState = State.BLUE;
                break;
        }
    }
    */

    public void update(float delta) {
        checkInput();
        if (invincible) {
            timeInvincible += delta;
            if (timeInvincible >= INVINCIBLETIME) {
                invincible = false;
                timeInvincible = 0;
            }
        }
        else {
            for(Fighter enemy : world.enemies) {
                if (sprite.getBoundingRectangle().overlaps(enemy.sprite.getBoundingRectangle()) && !invincible) {
                    adjustHealth();
                    invincible = true;
                }
            }
            for(EnemyBullet bullet : world.enemyBullets) {
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

    private void checkInput() {
        Gdx.app.log("Player Health: ", Integer.toString(health));
        Gdx.app.log("Invincible: ", Boolean.toString(invincible));
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            sprite.setPosition(Gdx.input.getX() - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight() / 2);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
        }
    }
    private void die() {
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

    public void addShield() {
        shields++;
    }

}
