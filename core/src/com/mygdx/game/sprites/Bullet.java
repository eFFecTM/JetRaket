package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.JetRaket;

public class Bullet {
    public Vector3 position;
    private Vector3 velocity;
    public Sprite sprite;
    private float scalebullet = 0.03f;

    public Bullet(float x, float y, Texture texture) {
        scalebullet = 0.03f;
        sprite = JetRaket.convertTextureToSprite(texture,scalebullet);
        sprite.setPosition(x,y);
        position = new Vector3(x+sprite.getWidth()*scalebullet/2, y-sprite.getHeight()*scalebullet, 0);
        velocity = new Vector3(0, -200, 0);
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
