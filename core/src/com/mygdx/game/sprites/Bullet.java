package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bullet {
    public Vector3 position;
    private Vector3 velocity;
    public Sprite sprite;
    private float scale;

    public Bullet(float x, float y, Texture texture) {
        scale = 0.03f;
        sprite = new Sprite(texture);
        sprite.setFlip(false,true);
        sprite.setRotation(90);
        sprite.setPosition(x,y);
        sprite.setOrigin(0,0);
        sprite.setScale(scale);
        position = new Vector3(x+sprite.getWidth()*scale/2, y-sprite.getHeight()*scale, 0);
        velocity = new Vector3(0, -100, 0);
    }

    public void update(float dt) {
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
        sprite.setPosition(position.x, position.y);
    }

    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }

}
