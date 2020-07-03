package com.adamuro.flappy.screens;

import com.adamuro.flappy.FlappyBird;
import com.adamuro.flappy.scenes.Hud;
import com.adamuro.flappy.sprites.Bird;
import com.adamuro.flappy.sprites.Ground;
import com.adamuro.flappy.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PlayScreen implements Screen {
    private static final int TUBES = 4;
    private static final int TUBE_SPACING = 150;
    private FlappyBird game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Hud hud;
    private Bird bird;
    private Texture background;
    private Array<Ground> grounds;
    private Array<Tube> tubes;

    public PlayScreen(FlappyBird game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, camera);
        this.hud = new Hud(game.batch);
        this.bird = new Bird(40, (float)FlappyBird.HEIGHT / 2);
        this.background = new Texture("background.png");
        this.grounds = new Array<>();
        this.grounds.add(new Ground(0, 0));
        this.grounds.add(new Ground(Ground.WIDTH, 0));
        this.tubes = new Array<>();
        for(int i = 0; i < TUBES; i++) tubes.add(new Tube(FlappyBird.WIDTH + i * TUBE_SPACING));
    }

    public void handleInput(float delta) {
        if(Gdx.input.justTouched()) bird.jump();
    }

    public void update(float delta) {
        handleInput(delta);

        bird.update(delta);
        camera.update();
        hud.update();

        for(Ground ground : grounds) {
            ground.update(delta);
            if(bird.collides(ground.getBounds())) {
                game.setScreen(new StartScreen(game));
                dispose();
                break;
            }
            if(ground.getPosition().x < -Ground.WIDTH) {
                ground.reposition(ground.getPosition().x + Ground.WIDTH * 2);
            }
        }
        for(Tube tube : tubes) {
            tube.update(delta);
            if(bird.passes(tube) && !tube.passed) {
                hud.addScore();
                tube.passed = true;
            }
            if(bird.collides(tube.getBotBounds()) || bird.collides(tube.getTopBounds())) {
                game.setScreen(new StartScreen(game));
                dispose();
                break;
            }
            if(tube.getBotPosition().x < -Tube.WIDTH) {
                tube.reposition(tube.getBotPosition().x + TUBES * TUBE_SPACING);
            }
        }
    }

    @Override
    public void show() {
        this.camera.position.x = (float)FlappyBird.WIDTH / 2;
        this.camera.position.y = (float)FlappyBird.HEIGHT / 2;
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.game.batch.setProjectionMatrix(camera.combined);
        this.game.batch.begin();
        this.game.batch.draw(background, 0, 0);
        this.game.batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            game.batch.draw(tube.getBotTexture(), tube.getBotPosition().x, tube.getBotPosition().y);
            game.batch.draw(tube.getTopTexture(), tube.getTopPosition().x, tube.getTopPosition().y);
        }
        for(Ground ground : grounds) {
            game.batch.draw(ground.getTexture(), ground.getPosition().x, ground.getPosition().y);
        }
        this.game.batch.end();
        this.hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(Tube tube : tubes) tube.dispose();
        for(Ground ground : grounds) ground.dispose();
        this.background.dispose();
        this.hud.dispose();
    }
}
