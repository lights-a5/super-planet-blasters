package com.blasters.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*******************************************************
 * This Class holds the renderer for the Game World. It holds
 * all the data necessary to draw everything on the screen while
 * in the game world.
 ********************************************************/

public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private SpriteBatch sb;
    private float accum;

    public GameRenderer(GameWorld world) {
        sb = new SpriteBatch();
        myWorld = world;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        myWorld.player.draw(sb);
        sb.end();
        accum += Gdx.graphics.getDeltaTime();
    }

    public void dispose() {

        sb.dispose();
    }
}
