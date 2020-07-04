package com.adamuro.flappy.screens;

import com.adamuro.flappy.FlappyBird;
import com.adamuro.flappy.scenes.MainMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class StartScreen implements Screen {
    private FlappyBird game;
    private FitViewport viewport;
    private Texture background;
    private Music soundtrack;
    private MainMenu menu;

    public StartScreen(final FlappyBird game) {
        this.game = game;
        this.viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.soundtrack = Gdx.audio.newMusic(Gdx.files.internal("audio/MASNO - BANIA ale to klasyk polskiego hip-hopu (jak zapomnieć przeróbka powered by GBS).mp3"));
        this.background = new Texture("background.png");
        this.menu = new MainMenu(game);
    }

    public void update(float delta) {

    }

    @Override
    public void show() {
        this.viewport.getCamera().position.x = (float)FlappyBird.WIDTH / 2;
        this.viewport.getCamera().position.y = (float)FlappyBird.HEIGHT / 2;
        this.soundtrack.setLooping(true);
        this.soundtrack.play();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.game.batch.setProjectionMatrix(viewport.getCamera().combined);
        this.game.batch.begin();
        this.game.batch.draw(background, 0, 0);
        this.game.batch.end();
        this.menu.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void pause() {
        this.soundtrack.pause();
    }

    @Override
    public void resume() {
        this.soundtrack.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.soundtrack.dispose();
        this.menu.dispose();
    }
}
