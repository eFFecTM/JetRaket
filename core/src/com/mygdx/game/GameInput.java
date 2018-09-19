package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Thomas Janssen & Jan De Laet
 */

public class GameInput implements InputProcessor {
    public Vector2 refPos,currPos,delta;
    private final GameScreen game;
    private boolean isMoving;
    public Vector2 velocity;
    public int limit;
    private Rectangle movementRec;
    private int shootPointer;

    public GameInput(final GameScreen game) {
        this.game = game;
        refPos = new Vector2();
        currPos = new Vector2();
        delta = new Vector2();
        velocity = new Vector2();
        isMoving = false;
        limit = 80;
        shootPointer = -1;
        movementRec = new Rectangle(0,0,JetRaket.WIDTH-game.buttonFire0.getWidth()*game.buttonFire0.getScaleX(),JetRaket.HEIGHT);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 converted = game.cam.unproject(new Vector3(screenX,screenY,0));

        // button shoot
        if(game.buttonFire0.getBoundingRectangle().contains(converted.x,converted.y)) {
            game.rocket.shoot();
            shootPointer = pointer;
            game.buttonFire0.setAlpha(0f);
            game.buttonFire1.setAlpha(1f);
        } else if(movementRec.contains(converted.x,converted.y)) { // movement
            currPos.set(converted.x,converted.y);
            game.knob.setAlpha(1f);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 converted = game.cam.unproject(new Vector3(screenX,screenY,0));

        // button shoot
        if(shootPointer == pointer) {
            game.buttonFire0.setAlpha(1f);
            game.buttonFire1.setAlpha(0f);
            shootPointer = -1;
        } else if(movementRec.contains(converted.x,converted.y)) { // movement
            currPos.set(game.rocket.getPosition().x+game.rocket.sprite.getWidth()*game.rocket.sprite.getScaleX()/2, game.rocket.getPosition().y+game.rocket.sprite.getHeight()*game.rocket.sprite.getScaleY()/2);
            game.knob.setAlpha(0f);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 converted = game.cam.unproject(new Vector3(screenX,screenY,0));

        /*if(game.buttonFire0.getBoundingRectangle().contains(converted.x,converted.y)) {
        } else */if(movementRec.contains(converted.x,converted.y)) { // movement
            currPos.set(converted.x,converted.y);
            game.knob.setAlpha(1f);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
