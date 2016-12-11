package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Adam 12/10/16.
 */

public class GreenPower extends Power {
    float x;
    float y;
    public GreenPower(GameWorld world, float x, float y) {
        super(world);
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    protected void definePower() {
        TextureRegion powerup = world.getAtlas().findRegion("powerupGreen");
        sprite = new Sprite(powerup);
    }

    @Override
    public void update(float delta) {
        move(delta);
        if (sprite.getY() + sprite.getHeight() < 0) {
            world.powerups.removeValue(this, true);
        }
        if (sprite.getBoundingRectangle().overlaps(world.player.sprite.getBoundingRectangle())) {
            die();
            Gdx.app.log("GreenPower", "activated");
        }
    }

    @Override
    public void move(float delta) {
        velocity.add(0, -39);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
    }

    public void die() {
        world.player.health++;
        world.powerups.removeValue(this, true);
    }
}