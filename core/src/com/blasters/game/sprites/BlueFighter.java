package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by SHELIVES on 11/3/2016.
 */

public class BlueFighter extends Fighter {
    TextureRegion fighter;
    boolean change;

    public BlueFighter(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 2;
        health  = 4;
        fighter = world.getAtlas().findRegion("stingrayShip");
        sprite = new Sprite(fighter);
        sprite.setRotation(315);
        change = true;
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
            if (sprite.getBoundingRectangle().overlaps(bullet.sprite.getBoundingRectangle())) {
                health--;
                bullet.kill();
                if(health <= 0) {
                    die();
                }
            }
        }
    }

    private void die() {
        world.screen.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }

    public void move(float delta){
        if(sprite.getX() <= 0){
            change = true;
            sprite.rotate(90);
        }
        else if(sprite.getX() >= Gdx.graphics.getWidth()){
            change = false;
            sprite.rotate(-90);
        }

        if(!change) {
            velocity.add(-500, -500);
        }
        else
        {
            velocity.add(500,-500);
        }

        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
    }
}
