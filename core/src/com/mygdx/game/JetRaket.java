package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.screen.MenuScreen;

/**
 * Created by Thomas Janssen & Jan De Laet
 */

public class JetRaket extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static int screenWidth;
	public static int screenHeight;
	public static final String TITLE = "JetRaket";

	public SpriteBatch batch;
	private FreeTypeFontGenerator generator;
	public BitmapFont font;
	private Music music;

	@Override
	public void create () {
		screenWidth = Gdx.app.getGraphics().getWidth();
		screenHeight = Gdx.app.getGraphics().getHeight();
		batch = new SpriteBatch();
		font = new BitmapFont();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/goodtimes.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 25;
		parameter.color = Color.RED;
		font = generator.generateFont(parameter);

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.25f);
		music.play();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		generator.dispose();
		music.dispose();
		screen.dispose();
	}

	public static Sprite convertTextureToSprite(Texture texture, Float scale){
		Sprite sprite = new Sprite(texture);
		sprite.setFlip(false,true);
		sprite.setOrigin(0,0);
		sprite.setScale(scale);

		return sprite;
	}

	public void toggleMusic(){
		if(music.isPlaying())
			music.pause();
		else{
			music.play();
		}
	}
}
