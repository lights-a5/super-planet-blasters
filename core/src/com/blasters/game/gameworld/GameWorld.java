package com.blasters.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.scenes.Hud;
import com.blasters.game.screens.GameScreen;
import com.blasters.game.sprites.Bullet;
import com.blasters.game.sprites.Fighter;
import com.blasters.game.sprites.FighterGenerator;
import com.blasters.game.sprites.HeroShip;
import com.blasters.game.sprites.Power;
import com.blasters.game.sprites.PowerUpGenerator;

import java.util.Random;

/**
 * This is the world. This is where everything happens during the game.
 * It maintains the current state of how the world currently is.
 */

public class GameWorld {
    public HeroShip player; // the player
    /*
     * TextureAtlas
     * TextureAtlas holds a big image file and uses a .pack to index them. But simply using
     * the getRegion("regionName") function, one has easy access to all regions in the texture
     * atlas.
     */
    private TextureAtlas atlas;
    private TextureAtlas playerAtlas;
    private int level; //what level we are on
    public Array<Fighter> enemies; //An array that holds all the enemies on the screen
    public Array<Bullet> bullets; //Holds all bullets fired by the main ship
    public Array<Power> powerups;
    Texture bg; //background texture
    int rateOfBackground; //NOT SURE WHAT THIS IS FOR. EXPLAIN PLZ TYLER?
    private static final float BULLETDELAY = .15f; //Delay between bullets. Increase for more bullets.
    private float currentDelay;
    public GameScreen screen;
    public Texture playerTexture;
    public FighterGenerator fgen;
    public PowerUpGenerator pgen;
    public Hud hud;

    public GameWorld(GameScreen screen) {
        this.screen = screen;
        atlas = new TextureAtlas("generalAtlas.pack");
        playerAtlas = new TextureAtlas("PlayerAtlas.pack");
        // we might want to make two
        player = new HeroShip(this);
        enemies  = new DelayedRemovalArray<Fighter>();
        bullets = new DelayedRemovalArray<Bullet>();
        powerups = new DelayedRemovalArray<Power>();
        bg = new Texture("black.png");
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat); //Not entirely sure what this is
        currentDelay = 0;

        fgen = new FighterGenerator();
        pgen = new PowerUpGenerator();
        hud = new Hud(screen.game.sb);
    }

    /* update
     * libgdx will look for this function. Essentially, this will advance the game state every time
     * it is used. This is how we move the game along.
     */
    public void update(float delta) {
        checkForSpawn(delta); //checks if we need to spawn
        for (Fighter enemy : enemies) { //for every fighter
            enemy.update(delta);        //update it
        }
        for (Bullet bullet : bullets) { //for every bullet
            bullet.update(delta);       //update it
        }
        for(Power power : powerups){
            power.update(delta);
        }
        player.update(delta);           //then update the player
    }

    /* checkForSpawn
     * This function checks if spawns need to happen with enemies and bullets. If there are no
     * more enemies, we need to go up a level and spawn more. Also, we add bullets.
     */
    private void checkForSpawn(float delta) {
        if (enemies == null || enemies.size == 0) { //if no enemies
            level++;                                //increase level
            spawnEnemies();                         //spawn enemies
        }
        if(currentDelay >= BULLETDELAY) {
            if(player.bulletMid){
                Bullet one = new Bullet(this, (player.sprite.getX() + player.sprite.getWidth() / 2.1f), (player.sprite.getY() + player.sprite.getHeight() / 2.5f ));
                bullets.add(one);
            }
            if(player.bulletSides) {
                Bullet two = new Bullet(this, (player.sprite.getX() + player.sprite.getWidth() / 1.79f), (player.sprite.getY() + player.sprite.getHeight() / 2.5f));
                bullets.add(two);
                Bullet three = new Bullet(this, (player.sprite.getX() + player.sprite.getWidth() / 2.49f), (player.sprite.getY() + player.sprite.getHeight() / 2.5f));
                bullets.add(three);
            }

            currentDelay = player.faster;
        }
        else {
            currentDelay += delta;
        }
    }

    /*
     * spawnEnemies
     * This function provides the functionality to spawn enemies at random places. It works for now,
     * but later we will need to make something like spawn squadron.
     */
    private void spawnEnemies() {
        int spawnValue = 0;
        enemies = new DelayedRemovalArray<Fighter>();
        while (spawnValue < level) {
            Fighter fighter = fgen.generate(level, spawnValue, this);
            spawnValue += fighter.value;
            enemies.add(fighter);
        }
        powerups.add(pgen.generate(this));
    }


    /*
     * dispose
     * this function disposes of all our resources. Currently, only our atlas needs to be disposed.
     */
    public void dispose() {
        atlas.dispose();
    }

    /*
     * Texture getAtlas
     * Returns the gameworld's texture atlas.
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }
    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }
}
