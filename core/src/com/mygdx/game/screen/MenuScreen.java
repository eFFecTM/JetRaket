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

public class MenuScreen implements Screen {
    private final JetRaket game;
    private OrthographicCamera cam;
    private Texture bg;
    private Texture playBtn;
    private Texture settings;
    private Sprite settings_sprite;
    private Sprite playBtn_sprite;

    public MenuScreen(final JetRaket game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, JetRaket.WIDTH, JetRaket.HEIGHT);

        playBtn = new Texture("playbtn.png");
        playBtn_sprite = JetRaket.convertTextureToSprite(playBtn, 1f);
        playBtn_sprite.setPosition(cam.position.x - playBtn_sprite.getWidth()/2, cam.position.y);

        bg = new Texture("background.jpg");
        settings = new Texture("settings.png");
        settings_sprite = JetRaket.convertTextureToSprite(settings, 0.1f);
        settings_sprite.setPosition(JetRaket.WIDTH - settings_sprite.getWidth()*settings_sprite.getScaleX() - 10, JetRaket.HEIGHT - settings_sprite.getHeight()*settings_sprite.getScaleY() - 10);
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
        //game.batch.draw(playBtn,cam.position.x - playBtn.getWidth()/2, cam.position.y);
        game.font.draw(game.batch,"JetRaket", 0 ,cam.position.y+100, JetRaket.WIDTH, Align.center, false);
        settings_sprite.draw(game.batch);
        playBtn_sprite.draw(game.batch);
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
        playBtn.dispose();
    }

    private void handleInput() {
        if(Gdx.input.isTouched())
        {
            Vector3 press=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            Vector3 temp = cam.unproject(press);
            //Rectangle textureBounds_settings = playBtn_sprite.getBoundingRectangle();
            Rectangle textureBounds_settings=new Rectangle(settings_sprite.getX(),settings_sprite.getY(),settings_sprite.getWidth(),settings_sprite.getHeight());
            Rectangle textureBounds_playBtn=new Rectangle(playBtn_sprite.getX(),playBtn_sprite.getY(),playBtn_sprite.getWidth(),playBtn_sprite.getHeight());
            if(textureBounds_settings.contains(temp.x,temp.y))
            {
                System.out.println("Settings");
            }
            else if(textureBounds_playBtn.contains(press.x, press.y)){
                System.out.println("Play");
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }
    }
}
