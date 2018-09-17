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
        currPos.set((float) screenX/JetRaket.screenWidth*JetRaket.WIDTH, (float) screenY/JetRaket.screenHeight*JetRaket.HEIGHT);
        game.knob.setPosition(refPos.x-game.knob.getWidth()/2,refPos.y-game.knob.getHeight()/2);
        game.knob.setAlpha(1f);
        if(screenX < JetRaket.screenWidth/2) {
            //refPos.set((float) screenX/JetRaket.screenWidth*JetRaket.WIDTH, (float) screenY/JetRaket.screenHeight*JetRaket.HEIGHT);

            isMoving = true;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.knob.setAlpha(0f);
        isMoving = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //System.out.println(pointer);
        currPos.set((float) screenX/JetRaket.screenWidth*JetRaket.WIDTH, (float) screenY/JetRaket.screenHeight*JetRaket.HEIGHT);
            //delta.set(currPos.x-refPos.x,currPos.y-refPos.y);
            //System.out.println("delta " + delta.x + " - " + delta.y);
            //System.out.println("touch dragged");


            //System.out.println("ref " + refPos.x + " - " + refPos.y);
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
