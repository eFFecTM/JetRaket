package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screen.GameScreen;

public class GameInput implements InputProcessor {
    public Vector2 refPos,currPos,delta;
    private final GameScreen game;
    private boolean isMoving;
    public Vector2 velocity;
    public int limit;

    public GameInput(final GameScreen game) {
        this.game = game;
        refPos = new Vector2();
        currPos = new Vector2();
        delta = new Vector2();
        velocity = new Vector2();
        isMoving = false;
        limit = 80;
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
        //currPos.set((float) screenX/JetRaket.screenWidth*JetRaket.WIDTH, (float) screenY/JetRaket.screenHeight*JetRaket.HEIGHT);
        //game.knob.setAlpha(1f);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.knob.setAlpha(0f);
        currPos.set(game.rocket.getPosition().x+game.rocket.sprite.getWidth()*game.rocket.sprite.getScaleX()/2, game.rocket.getPosition().y+game.rocket.sprite.getHeight()*game.rocket.sprite.getScaleY()/2);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        currPos.set((float) screenX/JetRaket.screenWidth*JetRaket.WIDTH, (float) screenY/JetRaket.screenHeight*JetRaket.HEIGHT);
        game.knob.setAlpha(1f);
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
