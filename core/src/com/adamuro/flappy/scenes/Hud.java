package com.adamuro.flappy.scenes;

import com.adamuro.flappy.FlappyBird;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.Stack;

public class Hud extends ScoreDisplay {
    public Hud(SpriteBatch batch) {
        super(batch);
        this.scoreTable.top();
        this.score = 0;
    }

    @Override
    public void update() {
        this.scoreTable.clearChildren();
        loadScoreToTable();
        this.scoreTable.padTop(20);
    }

    @Override
    public void draw() { stage.draw(); }

    @Override
    public void dispose() { for(Texture number : numbers) number.dispose(); }
}
