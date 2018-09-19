package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameInput;
import com.mygdx.game.JetRaket;
import com.mygdx.game.sprites.Meteor;
import com.mygdx.game.sprites.Rocket;

public class GameScreen implements Screen {

    private final JetRaket game;
    private OrthographicCamera cam;
    public Rocket rocket;
    private Meteor meteor;
    private Texture bg;
    private int score;
    private long startTime;
    public Sprite knob;
    private InputMultiplexer inputMultiplexer;
    public GameInput gameInput;
    private Vector2 out;


    public GameScreen(final JetRaket game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, JetRaket.WIDTH, JetRaket.HEIGHT);
        rocket = new Rocket();
         meteor = new Meteor();
        bg = new Texture("background.jpg");
        score = 0;
        startTime = TimeUtils.millis();
        knob = new Sprite(new Texture("touchKnob.png"));
        knob.setFlip(false,true);
        knob.setAlpha(0f);
        out = new Vector2(0,0);
		inputMultiplexer = new InputMultiplexer();
        gameInput = new GameInput(this);
		inputMultiplexer.addProcessor(gameInput);
		Gdx.input.setInputProcessor(inputMultiplexer);
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
        knob.draw(game.batch);
        rocket.update(delta);
        meteor.update(delta);
        rocket.sprite.draw(game.batch);
        meteor.sprite.draw(game.batch);
        game.batch.end();
        updateGame();
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
        rocket.dispose();
        meteor.dispose();
        bg.dispose();
        knob.getTexture().dispose();
    }

    private void updateGame() {
        out.x = gameInput.currPos.x - (rocket.getPosition().x+rocket.sprite.getScaleX()*rocket.sprite.getWidth()/2);
        out.y = gameInput.currPos.y - (rocket.getPosition().y+rocket.sprite.getScaleY()*rocket.sprite.getHeight()/2);
        rocket.move(out.x, out.y);
        knob.setPosition(gameInput.currPos.x-knob.getWidth()/2,gameInput.currPos.y-knob.getHeight()/2);
        detectCollision();
        updateScore();
    }

    private void detectCollision(){
        if(rocket.getBounds().overlaps(meteor.getBounds())) {
            game.setScreen(new EndScreen(game,score));
        }


    }

    private void updateScore(){
        if(TimeUtils.timeSinceMillis(startTime) > 1000){
            score += 10;
            startTime = TimeUtils.millis();
        }
    }
}
