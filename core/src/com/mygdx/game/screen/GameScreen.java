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

/**
 * Created by Thomas Janssen & Jan De Laet
 */

public class GameScreen implements Screen {

    private final JetRaket game;
    public OrthographicCamera cam;
    public Rocket rocket;
    private Meteor meteor;
    public Texture bg;
    private int score;
    private long startTime;
    public Sprite knob, buttonFire0, buttonFire1;
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

        buttonFire0 = new Sprite(new Texture("buttonFire0.png"));
        buttonFire0.setFlip(false,true);
        buttonFire0.setOrigin(0,0);
        buttonFire0.setScale(0.5f);
        buttonFire0.setPosition(JetRaket.WIDTH - buttonFire0.getWidth()*buttonFire0.getScaleX(),JetRaket.HEIGHT/2 - buttonFire0.getHeight()*buttonFire0.getScaleY());

        buttonFire1 = new Sprite(new Texture("buttonFire1.png"));
        buttonFire1.setFlip(false,true);
        buttonFire1.setOrigin(0,0);
        buttonFire1.setScale(0.5f);
        buttonFire1.setAlpha(0f);
        buttonFire1.setPosition(JetRaket.WIDTH - buttonFire0.getWidth()*buttonFire0.getScaleX(),JetRaket.HEIGHT/2 - buttonFire0.getHeight()*buttonFire0.getScaleY());

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
        buttonFire0.draw(game.batch);
        buttonFire1.draw(game.batch);
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

        if(rocket.getBounds().overlaps(meteor.getBounds())) {
            game.setScreen(new EndScreen(game,score));
        }
        if(TimeUtils.timeSinceMillis(startTime) > 1000){
            score += 10;
            startTime = TimeUtils.millis();
        }
//        timer.scheduleTask(new Timer.Task() {
//            @Override
//            public void run() {
//                System.out.println("1 SECOND");
//            }
//        },0,5, 1);

    }
}
