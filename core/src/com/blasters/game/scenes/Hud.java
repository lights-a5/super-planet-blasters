package com.blasters.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;

/**
 * This is the HUD. It displays the health, score, and lives on the top of the screen. Currently,
 * it is tightly coupled with the ships on the screen. When we go to clean up the code, we should
 * loosen the coupling.
 */

public class Hud implements Disposable {
    private Stage stage;
    private Viewport viewport;
    private GameWorld game;
    private ShapeRenderer shapeRenderer;

    private int score;

    private Label scoreLabel;

    public Hud(GameWorld game, SpriteBatch sb) {
        this.game = game;
        score = 0;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        shapeRenderer = new ShapeRenderer();

        Table table = new Table();

        table.top();

        table.setFillParent(true);
        Label scoreText = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreText).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX().padTop(2);

        stage.addActor(table);

    }

    public void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public int getScore() {
        return score;
    }

    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 16);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(0, 0, ((float)game.player.health/(float)game.player.maxHP) * Gdx.graphics.getWidth(), 16);
        shapeRenderer.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
