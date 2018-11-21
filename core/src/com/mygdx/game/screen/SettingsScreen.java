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
    private Texture backBtn, soundOnBtn;
    private Sprite backBtn_sprite, soundOnBtn_sprite;

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
        soundOnBtn_sprite.setPosition(cam.position.x - soundOnBtn_sprite.getWidth()/2*soundOnBtn_sprite.getScaleX(), cam.position.y -300);
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
    }

    private void handleInput(){
        if(Gdx.input.justTouched())
        {
            Vector3 press = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            Vector3 temp = cam.unproject(press);

            Rectangle textureBounds_backBtn = new Rectangle(backBtn_sprite.getX(),backBtn_sprite.getY(),backBtn_sprite.getWidth(),backBtn_sprite.getHeight());
            if(textureBounds_backBtn.contains(temp.x, temp.y)){
                System.out.println("Back to menu");
                game.setScreen(new MenuScreen(game));
                dispose();
            }

            Rectangle textureBounds_soundOnBtn = new Rectangle(soundOnBtn_sprite.getX(),soundOnBtn_sprite.getY(),soundOnBtn_sprite.getWidth(),soundOnBtn_sprite.getHeight());
            if(textureBounds_soundOnBtn.contains(temp.x, temp.y)){
                System.out.println("Sound toggled");
                game.toggleMusic();
            }

        }
    }
}
