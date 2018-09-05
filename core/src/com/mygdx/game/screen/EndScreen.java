package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.JetRaket;

public class EndScreen implements Screen{
    private final JetRaket game;
    private OrthographicCamera cam;
    private Texture bg;
    private Texture backBtn;
    private int score;
    private int highscore;

    public EndScreen(final JetRaket game, int score){
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, JetRaket.WIDTH, JetRaket.HEIGHT);
        backBtn = new Texture("playbtn.png");
        bg = new Texture("background.jpg");
        this.score = score;
        highscore = 0;
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
        String string = "Game over!\n\nScore: " + score + "\n\nHighscore: " + highscore;
        game.font.draw(game.batch, string, 0 ,cam.position.y+300, JetRaket.WIDTH, Align.center, false);
        game.batch.end();
        handleInput();
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
