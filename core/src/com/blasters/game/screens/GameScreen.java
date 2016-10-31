package com.blasters.game.screens;

import com.badlogic.gdx.Screen;
import com.blasters.game.gameworld.GameRenderer;
import com.blasters.game.gameworld.GameWorld;

/**
 * Created by SHELIVES on 10/31/2016.
 */

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
