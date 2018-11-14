package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.JetRaket;

public abstract class Entity {
    public Vector3 position;
    public Vector3 velocity;
    public Sprite sprite;
    public float scale;

    public Entity(float x, float y, Texture texture, float scale) {
        this.scale = scale;
        sprite = JetRaket.convertTextureToSprite(texture,scale);
        position = new Vector3(x-sprite.getWidth()*scale/2, y-sprite.getHeight()*scale, 0);
        velocity = new Vector3(0, -200, 0);
        sprite.setPosition(position.x,position.y);
    }

    public void setPosition(float x, float y) {
        this.position = position;
    }

    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }
}
