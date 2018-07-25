package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Thomas on 28/03/2018.
 */

public class Rocket {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    public Sprite sprite;
    private Sound shoot;
    public boolean colliding;

    public Rocket(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        sprite = new Sprite(new Texture("rocket.png"));
        //birdAnimation = new Animation(new TextureRegion(texture), 1, 0.5f);
        shoot = Gdx.audio.newSound(Gdx.files.internal("rocket.wav"));
        colliding = false;
    }

    public void update(float dt){
        //birdAnimation.update(dt);
        velocity.add(1, 0, 0);
        velocity.scl(dt);
        //if(!colliding)
            position.add(0, 0, 0);
        position.add(velocity.x *dt, velocity.y *dt, 0);
//        if(position.y < 82)
//            position.y = 82;
        velocity.scl(1/dt);
        sprite.setPosition(position.x, position.y);
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public void shoot(){
        shoot.play(0.5f);
    }

    public void move(float x, float y){
        velocity.x=x;
        velocity.y=y;
    }

    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void dispose(){
        sprite.getTexture().dispose();
        shoot.dispose();
    }
}
