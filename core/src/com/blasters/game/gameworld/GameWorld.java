package com.blasters.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.blasters.game.sprites.BlueFighter;
import com.blasters.game.sprites.Bullet;
import com.blasters.game.sprites.Fighter;
import com.blasters.game.sprites.HeroShip;
import com.blasters.game.sprites.RedFighter;

import java.util.Random;

/**
 * This is the world. This is where everything happens during the game.
 * It maintains the current state of how the world currently is.
 */

public class GameWorld {
    public HeroShip player;
    private TextureAtlas atlas;
    private int level;
    public Array<Fighter> enemies;
    public Array<Bullet> bullets;
    public Texture bg;
    public int srcy;
    public static final int BULLETDELAY = 10;
    private int currentDelay;

    public GameWorld() {
        atlas = new TextureAtlas("ships_and_bullets.pack");
        player = new HeroShip(this);
        enemies  = new DelayedRemovalArray<Fighter>();
        bullets = new DelayedRemovalArray<Bullet>();
        bg = new Texture("black.png");
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        currentDelay = 0;
    }

    public void update(float delta) {
        checkForSpawn();
        for (Fighter enemy : enemies) {
            enemy.update(delta);
        }
        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }
        player.update(delta);
    }

    private void checkForSpawn() {
        if (enemies == null || enemies.size == 0) {
            level++;
            spawnEnemies();
        }
        if(currentDelay >= BULLETDELAY) {
            Bullet temp = new Bullet(this, player.sprite.getX() - player.sprite.getRegionWidth() / 4,
                    player.sprite.getY() - player.sprite.getRegionHeight() / 2);
            bullets.add(temp);
            currentDelay = 0;
        }
        else {
            currentDelay++;
        }
    }

    private void spawnEnemies() {
        int spawnValue = 0;
        enemies = new DelayedRemovalArray<Fighter>();
        while (spawnValue < level) {
            Fighter fighter = spawnAnEnemy(spawnValue);
            spawnValue += fighter.value;
            enemies.add(fighter);
        }
    }

    private Fighter spawnAnEnemy(int spawnValue) {
        Fighter returnFighter;
        Random random = new Random();
        if (level - spawnValue >= 5) {
            if (random.nextInt() % 2 == 0) {
                returnFighter = new BlueFighter(this);
            }
            else {
                returnFighter = new RedFighter(this);
            }
        }
        else {
            returnFighter = new RedFighter(this);
        }
        float x = random.nextInt(Gdx.graphics.getWidth() - returnFighter.sprite.getRegionWidth());
        float y = random.nextInt(Gdx.graphics.getHeight()) + Gdx.graphics.getHeight();
        returnFighter.sprite.setPosition(x, y);
        return returnFighter;
    }

    public void dispose() {
        atlas.dispose();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
}
