package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Tyler on 11/22/2016.
 */

public class BluePower extends Power {
    public BluePower(GameWorld world){super(world);}

    @Override
    protected void definePower() {
        TextureRegion powerup = world.getAtlas().findRegion("powerupBlue_shield");
        sprite = new Sprite(powerup);
        x = random.nextInt(SuperPlanetBlasters.WIDTH - sprite.getRegionWidth());
        y = random.nextInt(SuperPlanetBlasters.HEIGHT) + SuperPlanetBlasters.HEIGHT ;
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
            Gdx.app.log("BluePower", "activated");
        }
    }

    @Override
    public void move(float delta) {
        velocity.add(0, -500);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
    }
    public void die(){
        //Shields user and ideally activate blue section and draw shield
        world.player.shields++;
        world.powerups.removeValue(this, true);
    }
}
