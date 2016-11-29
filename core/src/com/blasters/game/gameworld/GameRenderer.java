package com.blasters.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.screens.GameScreen;
import com.blasters.game.sprites.Bullet;
import com.blasters.game.sprites.Fighter;
import com.blasters.game.sprites.Power;

/*******************************************************
 * This Class holds the renderer for the Game World. It holds
 * all the data necessary to draw everything on the screen while
 * in the game world.
 ********************************************************/

public class GameRenderer {
    private GameWorld myWorld; //takes a gameworld so renderer knows what to draw
    private SuperPlanetBlasters game;
    private GameScreen screen;
    /*
     * Constructor
    */
    public GameRenderer(GameWorld world, GameScreen screen) {
        this.screen = screen;
        game = screen.game;
        myWorld = world;
    }

    /*
     * render
     * This is the loop that renders everything. Note that we must draw the background first
     * so that it doesn't cover up anything.
     */
    public void render() {
        game.sb.begin();
        drawBackground();
        drawBullets();
        drawFighters();
        drawPowers();
        myWorld.player.sprite.draw(game.sb);
        game.sb.end();
        myWorld.hud.draw();
    }

    /*
     * drawBackground
     * draws the background texture that is bg in the world class.
     */
    private void drawBackground() {
        game.sb.draw(myWorld.bg, 0, 0, 0, myWorld.rateOfBackground, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        myWorld.rateOfBackground -= 5;
    }

    /*
     * drawFighters
     * draws every fighter in the enemies array
     */
    private void drawFighters() {
        for (Fighter fighter: myWorld.enemies) {
            fighter.sprite.draw(game.sb);
        }
    }

    /*
     * drawBullets
     * draws every bullet in the bullets array
     */
    private void drawBullets() {
        for (Bullet bullet : myWorld.bullets) {
            bullet.laserSprite.draw(game.sb);
        }
    }

    /*
     * dispose
     * When we move the spritebatch to the main class, we honestly won't need this function anymore.
     */

    private void drawPowers(){
        for(Power power : myWorld.powerups){
            power.sprite.draw(game.sb);
        }
    }
    public void dispose() {

    }
}
