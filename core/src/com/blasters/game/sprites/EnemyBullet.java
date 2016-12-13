package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.gameworld.GameWorld;

import static com.badlogic.gdx.math.MathUtils.atan2;
import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;


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
        sprite.setScale(.6f,.6f);
        sprite.setPosition(posX, posY);
        float X = (destX - posX) * -1;
        float Y =(destY - posY)* -1;

        changeInX = destX - posX ; //(float) (sin((float) Math.atan(X/Y))* (Math.sqrt(Math.pow(X ,2)+ Math.pow(Y ,2))));
        changeInY = destY - posY; //(float) (sin((float) Math.atan(X/Y))* (Math.sqrt(Math.pow(X ,2)+ Math.pow(Y ,2))));
        //cos((float) Math.pow(destX, 2)) / posX
        //sin((float) Math.pow(destY,2)) / posY
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