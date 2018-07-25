package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JetRaket;
import com.mygdx.game.sprites.Meteor;
import com.mygdx.game.sprites.Rocket;
import com.mygdx.game.sprites.Meteor;

/**
 * Created by Thomas on 14/03/2018.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Rocket rocket;
    private Texture bg;
    private Texture ground;
    private Texture gameoverImg;
    private Vector2 groundPos1, groundPos2;
    private Array<Meteor> meteors;
    private ShapeRenderer sr;
    private boolean gameover;
    private Vector2 movementReference;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        rocket = new Rocket(40,200);
        cam.setToOrtho(false, JetRaket.WIDTH/2, JetRaket.HEIGHT/2);
        bg = new Texture("background.jpg");
        ground = new Texture("ground.png");
        gameoverImg = new Texture("gameover.png");
        gameover = false;
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        meteors = new Array<Meteor>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            meteors.add(new Meteor(i * (TUBE_SPACING + Meteor.METEOR_WIDTH)));
        }
        movementReference = new Vector2(0,0);

//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean touchDown (int x, int y, int pointer, int button) {
//                if (y < JetRaket.HEIGHT) {
//                    movementReference.set(x,y);
//                }
//                else {
//                    rocket.shoot();
//                }
//                return true; // return true to indicate the event was handled
//            }
//
//            @Override
//            public boolean touchUp (int x, int y, int pointer, int button) {
//
//                return true; // return true to indicate the event was handled
//            }
//
//            @Override
//            public boolean touchDragged (int x, int y, int pointer) {
//                if (y < JetRaket.HEIGHT) {
//                    float diffX = x - movementReference.x;
//                    float diffY = y - movementReference.y;
//
//                    rocket.move(diffX * 25, diffY * 25);
//                }
//                return false;
//            }
//        });
    }

    @Override
    protected void handleInput() {
        rocket.move(JetRaket.touchpad.getKnobPercentX()*10000,JetRaket.touchpad.getKnobPercentY()*10000);
        System.out.println(JetRaket.touchpad.getKnobPercentX() + " - " + JetRaket.touchpad.getKnobPercentY());
                //        if(Gdx.input.justTouched())
//            if(gameover)
//                gsm.set(new PlayState(gsm));
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        rocket.update(dt);
        //cam.position.x = rocket.getPosition().x + 80;
        cam.position.x = rocket.getPosition().x;

        for(int i=0; i <meteors.size; i++){
            Meteor meteor = meteors.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > meteor.getPosMeteor().x + meteor.getMeteor().getWidth()) {
                meteor.reposition(meteor.getPosMeteor().x + ((Meteor.METEOR_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(meteor.collides(rocket.getBounds())) {
                rocket.colliding = true;
                gameover = false;
            }
        }
        if(rocket.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            rocket.colliding = true;
            gameover = false;
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        //sb.draw(rocket.getTexture(), rocket.getPosition().x, rocket.getPosition().y);
        rocket.sprite.scale(0.001f);
        rocket.sprite.draw(sb);
        for(Meteor meteor : meteors) {
            sb.draw(meteor.getMeteor(), meteor.getPosMeteor().x, meteor.getPosMeteor().y);
            //sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        if(gameover)
            sb.draw(gameoverImg, cam.position.x - gameoverImg.getWidth()/2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        rocket.dispose();
        ground.dispose();
        gameoverImg.dispose();
        for(Meteor meteor : meteors){
            meteor.dispose();
        }
        System.out.println("Play state disposed");
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
