package com.blasters.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blasters.game.SuperPlanetBlasters;

/**
 * This is the HUD. It displays the health, score, and lives on the top of the screen. Currently,
 * it is tightly coupled with the ships on the screen. When we go to clean up the code, we should
 * loosen the coupling.
 */

public class Hud implements Disposable {
    private Stage stage;
    private Viewport viewport;

    private int health;
    private int score;
    private int lives;

    private Label healthLabel;
    private Label scoreLabel;
    private Label livesLabel;

    public Hud(SpriteBatch sb) {
        health = 2;
        score = 0;
        lives = 3;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();

        table.top();

        table.setFillParent(true);

        healthLabel = new Label(String.format("%02d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label(String.format("%02d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(healthLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.add(livesLabel).expandX().padTop(10);

        stage.addActor(table);

    }

    public void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public void draw() {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
