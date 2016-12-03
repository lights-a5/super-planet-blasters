package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.gameworld.GameWorld;

/**
 * Contains the logic for the Enemy Bullet
 */

public class EnemyBullet {
    private GameWorld world;
    private Vector2 velocity;
    public Sprite sprite;
    private float changeInX;
    private float changeInY;

    EnemyBullet(GameWorld world, float posX, float posY, float destX, float destY) {
        this.world = world;
        velocity = new Vector2(0, 0);
        sprite = new Sprite(world.getAtlas().findRegion("badLaserBall"));
        sprite.setPosition(posX, posY);
        changeInX = destX - posX;
        changeInY = destY - posY;
    }

    void kill() {
        world.enemyBullets.removeValue(this, true);
    }

    public void update(float delta) {
        velocity.add(changeInX, changeInY);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
        if(sprite.getX() + sprite.getWidth() < 0 || sprite.getX() > Gdx.graphics.getWidth() ||
                sprite.getY() + sprite.getHeight() < 0 || sprite.getY() > Gdx.graphics.getHeight()) {
            world.enemyBullets.removeValue(this, true);
        }
    }
}
