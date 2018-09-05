package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JetRaket;
import com.mygdx.game.sprites.Meteor;
import com.mygdx.game.sprites.Rocket;

public class GameScreen implements Screen {

    private final JetRaket game;
    private OrthographicCamera cam;
    private Stage stage;
    private Rocket rocket;
    private Meteor meteor;
    private Texture bg;
    private Skin touchpadSkin;
    private Touchpad touchpad;
    private int score;
    private long startTime;


    public GameScreen(final JetRaket game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, JetRaket.WIDTH, JetRaket.HEIGHT);
        rocket = new Rocket();
        meteor = new Meteor();
        bg = new Texture("background.jpg");
        score = 0;
        startTime = TimeUtils.millis();

        touchpadSkin = new Skin();
		//Set background image
		touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
		//Set knob image
		touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
		//Create TouchPad Style
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
		//Create Drawable's from TouchPad skin
		Drawable touchBackground = touchpadSkin.getDrawable("touchBackground");
		Drawable touchKnob = touchpadSkin.getDrawable("touchKnob");
		//Apply the Drawables to the TouchPad Style
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		//Create new TouchPad with the created style
		touchpad = new Touchpad(0, touchpadStyle);
		//setBounds(x,y,width,height)
		touchpad.setBounds(JetRaket.WIDTH/3*JetRaket.screenWidth/JetRaket.WIDTH, 0, JetRaket.WIDTH/3*JetRaket.screenWidth/JetRaket.WIDTH,JetRaket.WIDTH/3*JetRaket.screenHeight/JetRaket.HEIGHT);

		stage = new Stage(new ScreenViewport());
		stage.addActor(touchpad);
		Gdx.input.setInputProcessor(stage);

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
        rocket.update(delta);
        meteor.update(delta);
        rocket.sprite.draw(game.batch);
        meteor.sprite.draw(game.batch);
        game.batch.end();
        stage.draw();
        handleInput();
        updateLogic();
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
        touchpadSkin.dispose();
        stage.dispose();
    }

    private void handleInput() {
        rocket.move(touchpad.getKnobPercentX()*10,-touchpad.getKnobPercentY()*10);
    }

    private void updateLogic() {
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
