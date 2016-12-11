package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Tyler on 11/22/2016.
 * yeah man
 */

public class RedPower extends Power {
    public RedPower(GameWorld world) {super(world);}

    @Override
    protected void definePower() {
        TextureRegion powerup = world.getAtlas().findRegion("powerupRed_star");
        sprite = new Sprite(powerup);
        x = random.nextInt(Gdx.graphics.getWidth() - sprite.getRegionWidth());
        y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        sprite.setPosition(x, y);
    }

    @Override
    public void update(float delta) {
        move(delta);
        if (sprite.getY() + sprite.getHeight() < 0) {
            world.powerups.removeValue(this, true);
        }
        if (sprite.getBoundingRectangle().overlaps(world.player.sprite.getBoundingRectangle())) {
            die();
            Gdx.app.log("RedPower", "activated");
        }
    }

    @Override
    public void move(float delta) {
        velocity.add(0, -400);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
    }

    public void die(){
        //increase number of cannons
        if(world.player.bulletMid && !world.player.bulletSides){
            world.player.bulletMid = false;
            world.player.bulletSides = true;
            world.player.red = true;
        }
        else if(!world.player.bulletMid && world.player.bulletSides){
            world.player.bulletMid = true;
            world.player.red = false;
            world.player.red2 = true;
        }
        world.player.determineColor();
        world.powerups.removeValue(this, true);
    }
}