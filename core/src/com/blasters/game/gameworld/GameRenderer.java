package com.blasters.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blasters.game.sprites.Bullet;
import com.blasters.game.sprites.Fighter;

/*******************************************************
 * This Class holds the renderer for the Game World. It holds
 * all the data necessary to draw everything on the screen while
 * in the game world.
 ********************************************************/

public class GameRenderer {
    private GameWorld myWorld; //takes a gameworld so renderer knows what to draw
    private OrthographicCamera cam;
    /*
     * SpriteBatch needs to be held in the SuperPlanetBlasters class so different screens can have
     * it.
     */
    public SpriteBatch sb;

    /*
     * Constructor
    */
    public GameRenderer(GameWorld world) {
        sb = new SpriteBatch();
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /*
     * render
     * This is the loop that renders everything. Note that we must draw the background first
     * so that it doesn't cover up anything.
     */
    public void render() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined); //only draw what the camera can see
        sb.begin();
        drawBackground();
        drawBullets();
        drawFighters();
        myWorld.player.sprite.draw(sb);
        sb.end();
        cam.update();
    }

    /*
     * drawBackground
     * draws the background texture that is bg in the world class.
     */
    private void drawBackground() {
        sb.draw(myWorld.bg, 0, 0, 0, myWorld.srcy, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        myWorld.srcy -= 15;
    }

    /*
     * drawFighters
     * draws every fighter in the enemies array
     */
    private void drawFighters() {
        for (Fighter fighter: myWorld.enemies) {
            fighter.sprite.draw(sb);
        }
    }

    /*
     * drawBullets
     * draws every bullet in the bullets array
     */
    private void drawBullets() {
        for (Bullet bullet : myWorld.bullets) {
            bullet.sprite.draw(sb);
        }
    }

    /*
     * dispose
     * When we move the spritebatch to the main class, we honestly won't need this function anymore.
     */
    public void dispose() {
        sb.dispose();
    }
}
