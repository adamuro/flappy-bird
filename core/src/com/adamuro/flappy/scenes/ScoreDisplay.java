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
    protected Array<Texture> numbers;
    protected Stage stage;
    protected Table scoreTable;
    protected int score;

    public ScoreDisplay(SpriteBatch batch) {
        FitViewport viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
        this.scoreTable = new Table();
        this.numbers = new Array<>();
        this.stage.addActor(scoreTable);
        this.scoreTable.setFillParent(true);
        for(int i = 0; i < 10; i++) numbers.add(new Texture(numberTexturePath(i)));
    }

    public abstract void update();
    public abstract void draw();
    public void dispose() {
        this.stage.dispose();
    }

    public int getScore() { return score; }
    public void increaseScore() { score += 1; }
    protected void loadScoreToTable() {
        Stack<Integer> scoreDigits = numberToDigitStack(score);
        if(scoreDigits.empty()) { scoreTable.add(numberImage(0)); return; }
        while(!scoreDigits.empty()) scoreTable.add(numberImage(scoreDigits.pop()));
    }

    //Auxiliary score functions
    private Image numberImage(int number) { return new Image(numbers.get(number)); }
    private String numberTexturePath(int number) { return "numbers/" + number + ".png"; }
    private Stack<Integer> numberToDigitStack(int number) {
        Stack<Integer> digits = new Stack<>();
        for(; number > 0; number /= 10) digits.push(number % 10);
        return digits;
    }
}
