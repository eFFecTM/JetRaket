package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.JetRaket;

/**
 * Created by Thomas on 14/03/2018.
 */

public class MenuState extends State{
    Texture background;
    Texture playBtn;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font12;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, JetRaket.WIDTH/2, JetRaket.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/goodtimes.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        parameter.color = Color.RED;


    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y);
        font12 = generator.generateFont(parameter); // font size 12 pixels
        font12.draw(sb, "JetRaket", -cam.position.x, cam.position.y+100, JetRaket.WIDTH, Align.center, false);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        font12.dispose();
        System.out.println("Menu state disposed");
    }
}
