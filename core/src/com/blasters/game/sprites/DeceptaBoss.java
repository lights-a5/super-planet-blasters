package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by Adam on 11/27/2016.
 */

public class DeceptaBoss extends Fighter {

    public DeceptaBoss(GameWorld world) {
        super(world);
    }

    Sprite bossSprite;

    protected void defineFighter() {
        value = 100;
        health  = 120;
        TextureRegion fighter = world.getAtlas().findRegion("deceptaBoss"); //updated to reflect new atlas
        bossSprite = new Sprite(fighter);
        //sprite.setScale(.5f, .5f);
    }


    public void update(float delta) {

    }

    @Override
    public void move(float delta) {

    }

    private void die() {
        //animate death
        world.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }
}
