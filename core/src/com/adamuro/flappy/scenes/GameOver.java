package com.adamuro.flappy.scenes;

import com.adamuro.flappy.FlappyBird;
import com.adamuro.flappy.screens.PlayScreen;
import com.adamuro.flappy.screens.StartScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Stack;

public class GameOver extends ScoreDisplay {
    private FlappyBird game;
    private Table gameOverTable;
    private ImageButton restartButton;
    private Image gameOver;

    public GameOver(final int score, final FlappyBird game) {
        super(game.batch);
        this.gameOverTable = new Table();
        Texture restartButtonTexture = new Texture("play.png");
        this.restartButton = new ImageButton(new TextureRegionDrawable(restartButtonTexture));
        this.gameOver = new Image(new Texture("game_over.png"));
        this.gameOverTable.setFillParent(true);
        this.gameOverTable.top();
        this.gameOverTable.padTop(40);
        this.gameOverTable.add(gameOver).row();
        this.stage.addActor(gameOverTable);
        this.game = game;
        this.score = score;
        this.gameOverTable.add(restartButton).padTop(240);
        this.restartButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getScreen().dispose();
                game.setScreen(new PlayScreen(game));
            }});
        Gdx.input.setInputProcessor(stage);
        loadScoreToTable();
        this.scoreTable.padBottom(220);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() { stage.draw(); }

    @Override
    public void dispose() {

    }
}
