package com.adamuro.flappy.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud extends ScoreDisplay {
    public Hud(SpriteBatch batch) {
        super(batch);
        this.scoreTable.top();
        this.score = 0;
    }

    @Override
    public void update() {
        this.scoreTable.clearChildren();
        this.loadScoreToTable();
        this.scoreTable.padTop(20);
    }

    @Override
    public void draw() {
        stage.draw();
    }
}
