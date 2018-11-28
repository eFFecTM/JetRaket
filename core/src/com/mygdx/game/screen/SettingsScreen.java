package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.JetRaket;

/**
 * Created by Thomas Janssen & Jan De Laet
 */
public class SettingsScreen implements Screen {
    private final JetRaket game;
    public OrthographicCamera cam;
    private Texture bg;
    private Texture backBtn, soundOnBtn, soundOffBtn, vibrateOnBtn, vibrateOffBtn;
    private Sprite backBtn_sprite, soundOnBtn_sprite, soundOffBtn_sprite, vibrateOnBtn_sprite, vibrateOffBtn_sprite;

    public SettingsScreen(JetRaket game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, JetRaket.WIDTH, JetRaket.HEIGHT);

        bg = new Texture("background.jpg");

        backBtn = new Texture("backBtn.png");
        backBtn_sprite = JetRaket.convertTextureToSprite(backBtn, 0.2f);
        backBtn_sprite.setPosition(cam.position.x - backBtn_sprite.getWidth()/2, cam.position.y);

        soundOnBtn = new Texture("soundOn.png");
        soundOnBtn_sprite = JetRaket.convertTextureToSprite(soundOnBtn, 0.05f);
        soundOnBtn_sprite.setPosition(cam.position.x - soundOnBtn_sprite.getWidth()/2*soundOnBtn_sprite.getScaleX(), cam.position.y -150);

        soundOffBtn = new Texture("soundOff.png");
        soundOffBtn_sprite = JetRaket.convertTextureToSprite(soundOffBtn, 0.05f);
        soundOffBtn_sprite.setPosition(cam.position.x - soundOffBtn_sprite.getWidth()/2*soundOffBtn_sprite.getScaleX(), cam.position.y -150);
        soundOffBtn_sprite.setAlpha(0f);

        vibrateOnBtn = new Texture("vibrateOn.png");
        vibrateOnBtn_sprite = JetRaket.convertTextureToSprite(vibrateOnBtn, 0.05f);
        vibrateOnBtn_sprite.setPosition(cam.position.x - vibrateOnBtn_sprite.getWidth()/2*vibrateOnBtn_sprite.getScaleX(), cam.position.y -300);

        vibrateOffBtn = new Texture("vibrateOff.png");
        vibrateOffBtn_sprite = JetRaket.convertTextureToSprite(vibrateOffBtn, 0.05f);
        vibrateOffBtn_sprite.setPosition(cam.position.x - vibrateOffBtn_sprite.getWidth()/2*vibrateOffBtn_sprite.getScaleX(), cam.position.y -300);
        vibrateOffBtn_sprite.setAlpha(0f);
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
        game.font.draw(game.batch,"Settings", 0 ,JetRaket.HEIGHT-100, JetRaket.WIDTH, Align.center, false);
        backBtn_sprite.draw(game.batch);
        soundOnBtn_sprite.draw(game.batch);
        soundOffBtn_sprite.draw(game.batch);
        vibrateOnBtn_sprite.draw(game.batch);
        vibrateOffBtn_sprite.draw(game.batch);
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
        soundOnBtn.dispose();
        soundOffBtn.dispose();
        vibrateOnBtn.dispose();
        vibrateOffBtn.dispose();
    }

    private void handleInput(){
        if(Gdx.input.justTouched())
        {
            Vector3 press = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            Vector3 temp = cam.unproject(press);

            Rectangle textureBounds_backBtn = new Rectangle(backBtn_sprite.getX(),backBtn_sprite.getY(),backBtn_sprite.getWidth(),backBtn_sprite.getHeight());
            Rectangle textureBounds_soundOnBtn = new Rectangle(soundOnBtn_sprite.getX(),soundOnBtn_sprite.getY(),soundOnBtn_sprite.getWidth()*soundOnBtn_sprite.getScaleX(),soundOnBtn_sprite.getHeight()*soundOnBtn_sprite.getScaleY());
            Rectangle textureBounds_soundOffBtn = new Rectangle(soundOffBtn_sprite.getX(),soundOffBtn_sprite.getY(),soundOffBtn_sprite.getWidth()*soundOffBtn_sprite.getScaleX(),soundOffBtn_sprite.getHeight()*soundOffBtn_sprite.getScaleY());
            Rectangle textureBounds_vibrateOnBtn = new Rectangle(vibrateOnBtn_sprite.getX(),vibrateOnBtn_sprite.getY(),vibrateOnBtn_sprite.getWidth()*vibrateOnBtn_sprite.getScaleX(),vibrateOnBtn_sprite.getHeight()*vibrateOnBtn_sprite.getScaleY());
            Rectangle textureBounds_vibrateOffBtn = new Rectangle(vibrateOffBtn_sprite.getX(),vibrateOffBtn_sprite.getY(),vibrateOffBtn_sprite.getWidth()*vibrateOffBtn_sprite.getScaleX(),vibrateOffBtn_sprite.getHeight()*vibrateOffBtn_sprite.getScaleY());

            if(textureBounds_backBtn.contains(temp.x, temp.y)){
                System.out.println("Back to menu");
                game.setScreen(new MenuScreen(game));
                dispose();
            }

            if(textureBounds_soundOnBtn.contains(temp.x, temp.y) && soundOnBtn_sprite.getColor().a == 0.99607843f){
                System.out.println("Sound off");
                game.toggleMusic();
                soundOnBtn_sprite.setAlpha(0f);
                soundOffBtn_sprite.setAlpha(1f);
            }
            else if(textureBounds_soundOffBtn.contains(temp.x, temp.y) && soundOffBtn_sprite.getColor().a == 0.99607843f){
                System.out.println("Sound on");
                game.toggleMusic();
                soundOnBtn_sprite.setAlpha(1f);
                soundOffBtn_sprite.setAlpha(0f);
            }

            if(textureBounds_vibrateOnBtn.contains(temp.x, temp.y) && vibrateOnBtn_sprite.getColor().a == 0.99607843f){
                System.out.println("Vibration off");
                vibrateOnBtn_sprite.setAlpha(0f);
                vibrateOffBtn_sprite.setAlpha(1f);
            }
            else if(textureBounds_vibrateOffBtn.contains(temp.x, temp.y) && vibrateOffBtn_sprite.getColor().a == 0.99607843f){
                System.out.println("Vibration on");
                vibrateOnBtn_sprite.setAlpha(1f);
                vibrateOffBtn_sprite.setAlpha(0f);
            }

        }
    }
}
