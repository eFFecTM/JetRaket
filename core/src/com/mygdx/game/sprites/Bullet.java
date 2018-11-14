package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

public class Bullet extends Entity {

    public Bullet(float x, float y, Texture texture) {
        super(x,y,texture,0.03f);
    }

    public void update(float dt) {
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
        sprite.setPosition(position.x, position.y);
    }
}
