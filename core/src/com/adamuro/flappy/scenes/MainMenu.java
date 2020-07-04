package com.adamuro.flappy.scenes;

import com.adamuro.flappy.FlappyBird;
import com.adamuro.flappy.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu {
    private FitViewport viewport;
    private Stage stage;
    private Table table;
    private ImageButton playButton;

    public MainMenu(final FlappyBird game) {
        this.viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport);
        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(table);
        Texture playButtonTexture = new Texture("play.png");
        this.playButton = new ImageButton(new TextureRegionDrawable(playButtonTexture));
        this.table.add(playButton);
        this.playButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getScreen().dispose();
                game.setScreen(new PlayScreen(game));
            }});
        Gdx.input.setInputProcessor(stage);
    };

    public void draw() { stage.draw(); }

    public void dispose() { stage.dispose(); }
}
