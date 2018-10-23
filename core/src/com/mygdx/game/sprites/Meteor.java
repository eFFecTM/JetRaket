package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.JetRaket;

import java.util.Random;

/**
 * Created by Thomas Janssen & Jan De Laet
 */

public class Meteor {
    public static final int METEOR_WIDTH = 52;
    private Vector3 position;
    private Vector3 velocity;
    public Sprite sprite;
    private Vector2 posMeteor;
    private Rectangle boundsTop;
    private Random rand;

    public Meteor(){
        rand = new Random();
        int x = rand.nextInt(JetRaket.WIDTH);
        position = new Vector3(x,100,0);
        velocity = new Vector3(0,0,0);
        Texture texture = new Texture("meteor.png");
        sprite = new Sprite(texture);
        sprite.setOrigin(0,0);
        sprite.rotate(-90);
        sprite.setPosition(x,100);
        sprite.setScale(0.1f);
        //rand = new Random();
        //posMeteor = new Vector2(x, rand.nextInt(JetRaket.HEIGHT));
        //boundsTop = new Rectangle(posMeteor.x, posMeteor.y, meteor.getWidth(), meteor.getHeight());
    }

    public void update(float dt){
        velocity.add(1,0,0);
        velocity.scl(dt);
//        if(!colliding)
//            position.add(0, 0, 0);
        position.add(velocity.x, velocity.y, 0);
        if(position.y > 800 + sprite.getHeight() + 10){
            reset();
        }

        velocity.scl(1/dt);
        move(0,200);
        sprite.setPosition(position.x, position.y);
    }

    public void reset()
    {
        Random random = new Random();
        position.x = random.nextInt(JetRaket.WIDTH);
        position.y = 0;
    }

    public void move(float x, float y){
        velocity.x = x;
        velocity.y = y;
    }

    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }
}
