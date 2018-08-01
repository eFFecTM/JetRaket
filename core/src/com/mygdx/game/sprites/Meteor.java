package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.JetRaket;

import java.util.Random;

/**
 * Created by Thomas on 28/03/2018.
 */

public class Meteor {
    public static final int METEOR_WIDTH = 52;
    private Texture meteor;
    private Sprite sprite;
    private Vector2 posMeteor;
    private Rectangle boundsTop;
    private Random rand;

    public Meteor(float x){
        meteor = new Texture("meteor.png");
        sprite = new Sprite();
        rand = new Random();
        posMeteor = new Vector2(x, rand.nextInt(JetRaket.HEIGHT));
        boundsTop = new Rectangle(posMeteor.x, posMeteor.y, meteor.getWidth(), meteor.getHeight());
    }

    public Texture getMeteor() {
        return meteor;
    }

    public Vector2 getPosMeteor() {
        return posMeteor;
    }

    public void reposition(float x){
        posMeteor.set(x, rand.nextInt(JetRaket.HEIGHT));
        boundsTop.setPosition(x, posMeteor.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop);
    }

    public void dispose(){
        meteor.dispose();
    }
}
