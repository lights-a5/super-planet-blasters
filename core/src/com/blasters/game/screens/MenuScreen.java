package com.blasters.game.screens;

/**
 * Created by Adam on 11/15/2016.
 */
        import com.badlogic.gdx.ApplicationListener;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.Screen;
        import com.blasters.game.screens.GameScreen;
        import com.badlogic.gdx.audio.Music;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.files.FileHandle;
        import com.badlogic.gdx.graphics.OrthographicCamera;//
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.blasters.game.SuperPlanetBlasters;
        import com.badlogic.gdx.scenes.scene2d.InputListener;



public class MenuScreen implements Screen, InputProcessor,ApplicationListener {

    public SuperPlanetBlasters game;
    private Texture startButton;
    private Texture logo;
    private Texture soundBtn;
    private Texture extras;
    private Texture bg;

    private Music menuMusic;
    private Sound click; //no click sound yet
    private boolean playing;

    private InputListener input;

    private Sprite start;
    private Sprite sound;

    public MenuScreen (SuperPlanetBlasters game) {

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("bolt.mp3"));

        OrthographicCamera camera = new OrthographicCamera();
        startButton = new Texture("StartButn.png");
        logo = new Texture("SPB_logo.png");
        soundBtn = new Texture("soundOn.png");
        extras = new Texture("paper_planet1.png");
        bg = new Texture("menuBg.jpg");
        this.game = game;

        start = new Sprite(startButton);
        start.setScale(.3f, .3f);
        start.setPosition((Gdx.graphics.getWidth() /2 - start.getWidth() / 2), (Gdx.graphics.getHeight() / 5 - start.getHeight() /2));

        sound = new Sprite(soundBtn);
        sound.setScale(.5f, .5f);
        sound.setPosition( (Gdx.graphics.getWidth() - sound.getWidth() ), (Gdx.graphics.getHeight() / 5 - sound.getWidth() ));

        menuMusic.play();
        playing = true;


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //test if touched within coordinates of button
        //game.setScreen(new Screen(GameScreen(game, isSound));
        //test if touched within coordinates of volume button
        //turn on or off sound
        game.sb.begin();
        game.sb.draw(bg, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sound.draw(game.sb);
        start.draw(game.sb);

        //game.sb.draw(startButton, (Gdx.graphics.getWidth() /2 - 140), (Gdx.graphics.getHeight() / 10), 280, 280);
        //game.sb.draw(soundBtn, (Gdx.graphics.getWidth() /2 - 250), (Gdx.graphics.getHeight() / 11), 105, 105);
        game.sb.draw(logo, (Gdx.graphics.getWidth() / 2 - 240), (Gdx.graphics.getHeight() / 2 -100),500, 500);
        game.sb.draw(extras, (Gdx.graphics.getWidth() /2 - 280), (Gdx.graphics.getHeight() / 14 -12), 130, 130 );
        game.sb.end();
        Gdx.input.setInputProcessor(this);

    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {



    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {
        Gdx.app.log("MenuScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("MenuScreen", "resume called");
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

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
            game.setScreen(new GameScreen(game,playing));
            Gdx.app.log("MenuScreen", "start Pressed");
        }
        if (sound.getBoundingRectangle().contains(screenX, screenY)) {
            if (playing) {
                menuMusic.pause();
                playing = false;
            } else {
                menuMusic.play();
                playing = true;
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