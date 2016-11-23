package com.blasters.game.screens;

/**
 * Created by Adam on 11/15/2016.
 */
        import com.badlogic.gdx.ApplicationListener;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.audio.Music;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.blasters.game.SuperPlanetBlasters;
        import com.badlogic.gdx.scenes.scene2d.InputListener;



public class MenuScreen implements Screen, InputProcessor,ApplicationListener {

    public SuperPlanetBlasters game;
    private Music menuMusic;
    //private Sound click; //no click sound yet

    private Texture bg;
    private Texture logo;
    private Texture extras;

    private InputListener input;

    private Sprite start;
    private Sprite sound;

    public MenuScreen (SuperPlanetBlasters game) {
        this.game = game;

    }

    @Override
    public void create() {

    }


    @Override
    public void show() {
        menuMusic = game.assetManager.get("bolt.mp3", Music.class);
        if (game.playMusic) {
            menuMusic.play();
        }
        start = new Sprite(game.assetManager.get("StartButn.png", Texture.class));
        start.setScale(.3f, .3f);
        start.setPosition((SuperPlanetBlasters.WIDTH / 2 - start.getWidth() / 2),
                (SuperPlanetBlasters.HEIGHT / 5 - start.getHeight() /2));

        sound = new Sprite(game.assetManager.get("soundOn.png", Texture.class));
        sound.setScale(.5f, .5f);
        sound.setPosition( (SuperPlanetBlasters.WIDTH - sound.getWidth() ),
                (SuperPlanetBlasters.HEIGHT / 5 - sound.getWidth() ));

        bg = game.assetManager.get("menuBg.jpg", Texture.class);
        logo = game.assetManager.get("SPB_logo.png", Texture.class);
        // I'm imagining this will be turned into a sprite later
        extras = game.assetManager.get("paper_planet1.png", Texture.class);

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        //test if touched within coordinates of button
        //game.setScreen(new Screen(GameScreen(game, isSound));
        //test if touched within coordinates of volume button
        //turn on or off sound
        game.sb.begin();
        game.sb.draw(bg, 0,0, SuperPlanetBlasters.WIDTH, SuperPlanetBlasters.HEIGHT);
        sound.draw(game.sb);
        start.draw(game.sb);

        //game.sb.draw(startButton, (Gdx.graphics.getWidth() /2 - 140), (Gdx.graphics.getHeight() / 10), 280, 280);
        //game.sb.draw(soundBtn, (Gdx.graphics.getWidth() /2 - 250), (Gdx.graphics.getHeight() / 11), 105, 105);
        game.sb.draw(logo, (Gdx.graphics.getWidth() / 2 - 240), (Gdx.graphics.getHeight() / 2 -100),500, 500);
        game.sb.draw(extras, (Gdx.graphics.getWidth() /2 - 280), (Gdx.graphics.getHeight() / 14 -12), 130, 130 );
        game.sb.end();

    }



    @Override
    public void resize(int width, int height) {



    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        logo.dispose();
        extras.dispose();
        bg.dispose();
        menuMusic.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (start.getBoundingRectangle().contains(screenX, screenY)) {
            menuMusic.pause();
            game.setScreen(game.gameScreen);
        }
        if (sound.getBoundingRectangle().contains(screenX, screenY)) {
            if (game.playMusic) {
                menuMusic.pause();
                game.playMusic = false;
            } else {
                menuMusic.play();
                game.playMusic = true;
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}