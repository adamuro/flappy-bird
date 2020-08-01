package com.adamuro.flappy.scenes;

import com.adamuro.flappy.FlappyBird;
import com.adamuro.flappy.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenu extends ScoreDisplay {
    public MainMenu(final FlappyBird game) {
        super(game.batch);
        Texture playButtonTexture = new Texture("play.png");
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(playButtonTexture));
        playButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getScreen().dispose();
                game.setScreen(new PlayScreen(game));
            }});
        this.scoreTable.add(playButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        stage.draw();
    }
}