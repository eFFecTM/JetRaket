package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.JetRaket;

/**
 * Created by Thomas Janssen & Jan De Laet
 */

public class EndScreen implements Screen{
    private final JetRaket game;
    private OrthographicCamera cam;
    private Texture bg;
    private Texture backBtn;
    private int score;
    private int highscore;
    private Preferences prefs;

    public EndScreen(final JetRaket game, int score){
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, JetRaket.WIDTH, JetRaket.HEIGHT);
        backBtn = new Texture("playbtn.png");
        bg = new Texture("background.jpg");
        prefs = Gdx.app.getPreferences("game_preferences");
        this.score = score;
        highscore = getHighscore();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.batch.draw(backBtn,cam.position.x - backBtn.getWidth()/2, cam.position.y);
        checkHighscore();
        String string = "Game over!\n\nScore: " + score + "\n\nHighscore: " + getHighscore();
        game.font.draw(game.batch, string, 0 ,cam.position.y+300, JetRaket.WIDTH, Align.center, false);
        game.batch.end();

        handleInput();
    }

    public void checkHighscore() {
        if (!prefs.contains("highscore")) {
            prefs.putInteger("highScore", 0);
        }
        if (score > getHighscore()) {
            prefs.putInteger("highscore", score); //save the new high score
            prefs.flush();
        }
    }

    public int getHighscore(){
        if (!prefs.contains("highscore")) {
            prefs.putInteger("highScore", 0);
        }
        highscore = prefs.getInteger("highscore");
        return highscore;
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
        backBtn.dispose();
    }

    private void handleInput() {
        if(Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
}
