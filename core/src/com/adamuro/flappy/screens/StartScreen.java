package com.adamuro.flappy.screens;

import com.adamuro.flappy.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class StartScreen implements Screen {
    private FlappyBird game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Texture background;
    private Stage stage;
    private Table table;
    private ImageButton playButton;

    // TODO: Create hud scene for play button
    public StartScreen(final FlappyBird game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, camera);
        this.background = new Texture("background.png");
        this.stage = new Stage(viewport);
        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(table);
        Texture playButtonTexture = new Texture("play.png");
        this.playButton = new ImageButton(new TextureRegionDrawable(playButtonTexture));
        this.table.add(playButton);
        this.playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    public void update(float delta) {
        stage.act(delta);
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

        this.game.batch.setProjectionMatrix(stage.getCamera().combined);
        this.game.batch.begin();
        this.game.batch.draw(background, 0, 0);
        this.game.batch.end();
        this.stage.draw();
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
        this.background.dispose();
        this.stage.dispose();
    }
}
