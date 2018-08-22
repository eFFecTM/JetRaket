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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JetRaket;
import com.mygdx.game.sprites.Rocket;

public class GameScreen implements Screen {

    private final JetRaket game;
    private OrthographicCamera cam;
    private Stage stage;

    private Rocket rocket;
    private Texture bg;
    private Skin touchpadSkin;
    private Touchpad touchpad;


    public GameScreen(final JetRaket game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, JetRaket.WIDTH, JetRaket.HEIGHT);

        rocket = new Rocket(0,0);
        bg = new Texture("background.jpg");

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
		touchpad = new Touchpad(10, touchpadStyle);
		//setBounds(x,y,width,height)
        System.out.println(JetRaket.screenWidth);
		touchpad.setBounds(0, 0, JetRaket.WIDTH/2*JetRaket.screenWidth/JetRaket.WIDTH,JetRaket.WIDTH/2*JetRaket.screenHeight/JetRaket.HEIGHT);

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
        rocket.sprite.draw(game.batch);
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
        bg.dispose();
        touchpadSkin.dispose();
        stage.dispose();
    }

    private void handleInput() {
        rocket.move(touchpad.getKnobPercentX()*10000,-touchpad.getKnobPercentY()*10000);
        //System.out.println(touchpad.getKnobPercentX() + " - " + touchpad.getKnobPercentY());
        System.out.println(rocket.getPosition().x + " - " + rocket.getPosition().y);
//        if(Gdx.input.justTouched()) {
//            game.setScreen(new MenuScreen(game));
//            dispose();
//        }
    }

    private void updateLogic() {

    }
}
