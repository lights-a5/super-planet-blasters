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
    private GameWorld myWorld;
    private OrthographicCamera cam;
    public SpriteBatch sb;

    public GameRenderer(GameWorld world) {
        sb = new SpriteBatch();
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        myWorld.player.sprite.draw(sb);
        drawBackground();
        drawBullets();
        drawFighters();
        sb.end();
        cam.update();
    }

    private void drawBackground() {
        sb.draw(myWorld.bg, 0, 0, 0, myWorld.srcy, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        myWorld.srcy -= 15;
    }

    private void drawFighters() {
        for (Fighter fighter: myWorld.enemies) {
            fighter.sprite.draw(sb);
        }
    }

    private void drawBullets() {
        for (Bullet bullet : myWorld.bullets) {
            bullet.sprite.draw(sb);
        }
    }

    public void dispose() {
        sb.dispose();
    }
}
