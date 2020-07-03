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

public class Hud {
    private Stage stage;
    private Table table;
    private Array<Texture> numbers;
    private int score;

    public Hud(SpriteBatch batch) {
        FitViewport viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.numbers = new Array<>();
        this.stage = new Stage(viewport, batch);
        this.table = new Table();
        this.stage.addActor(table);
        this.table.setFillParent(true);
        this.table.top();
        this.score = 0;
        for(int i = 0; i < 10; i++) numbers.add(new Texture(numberTexturePath(i)));
    }

    public void update() {
        this.table.clearChildren();
        Stack<Integer> scoreDigits = numberToDigitStack(score);
        if(scoreDigits.empty()) { table.add(numberImage(0)).padTop(10); return; }
        while(!scoreDigits.empty()) table.add(numberImage(scoreDigits.pop())).padTop(10);
    }

    public void draw() { stage.draw(); }

    public void dispose() { for(Texture number : numbers) number.dispose(); }

    public void addScore() { score += 1; }

    private Image numberImage(int number) { return new Image(numbers.get(number)); }
    private String numberTexturePath(int number) { return "numbers/" + number + ".png"; }
    private Stack<Integer> numberToDigitStack(int number) {
        Stack<Integer> digits = new Stack<>();
        for(; number > 0; number /= 10) digits.push(number % 10);
        return digits;
    }
}
