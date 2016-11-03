package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.gameworld.GameWorld;


/**
 * Created by SHELIVES on 11/2/2016.
 */

public class Bullet {
    TextureRegion bullet;
    protected GameWorld world;
    public Vector2 velocity;
    public Sprite sprite;


    public Bullet(GameWorld world, float x, float y) {
        this.world = world;
        velocity = new Vector2(0, 0);
        bullet = world.getAtlas().findRegion("paper_starfighterFire01");
        sprite = new Sprite(bullet);
        sprite.setRegion(bullet);
        sprite.setPosition(x, y);
        sprite.setScale(.2f, .2f);
    }

    public void kill() {
        world.bullets.removeValue(this, true);
    }

    public void update(float delta) {
        velocity.add(0, 500);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
        if (sprite.getY() > Gdx.graphics.getHeight() + sprite.getRegionHeight()) {
            world.bullets.removeValue(this, true);
        }
    }
}
