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

public abstract class ScoreDisplay {
    protected static Array<Texture> numbers;
    protected Stage stage;
    protected Table scoreTable;
    protected int score;

    public ScoreDisplay(SpriteBatch batch) {
        FitViewport viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.numbers = new Array<>();
        this.stage = new Stage(viewport, batch);
        this.scoreTable = new Table();
        this.stage.addActor(scoreTable);
        this.scoreTable.setFillParent(true);
        for(int i = 0; i < 10; i++) numbers.add(new Texture(numberTexturePath(i)));
    }

    public abstract void update();
    public abstract void draw();
    public abstract void dispose();

    public int getScore() { return score; }
    public void addScore() { score += 1; }
    protected Image numberImage(int number) { return new Image(numbers.get(number)); }
    protected String numberTexturePath(int number) { return "numbers/" + number + ".png"; }
    protected Stack<Integer> numberToDigitStack(int number) {
        Stack<Integer> digits = new Stack<>();
        for(; number > 0; number /= 10) digits.push(number % 10);
        return digits;
    }

    protected void loadScoreToTable() {
        Stack<Integer> scoreDigits = numberToDigitStack(score);
        if(scoreDigits.empty()) { scoreTable.add(numberImage(0)); return; }
        while(!scoreDigits.empty()) scoreTable.add(numberImage(scoreDigits.pop()));
    }
}
