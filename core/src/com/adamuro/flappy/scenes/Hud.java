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

public class Hud {
    private SpriteBatch batch;
    private FitViewport viewport;
    private Array<Image> numbers;
    private Stage stage;
    private Table table;
    private int score;

    public Hud(SpriteBatch batch) {
        this.batch = batch;
        this.score = 0;
        this.viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.numbers = new Array<>();
        this.table = new Table();
        this.stage = new Stage(viewport, batch);
        for(int i = 0; i < 10; i++)
            numbers.add(numberImage(i));
        this.table.setFillParent(true);
        this.table.top();
        this.table.add(numbers.get(0)).padTop(10);
        this.stage.addActor(table);
    }

    public void draw() { stage.draw(); }

    private Image numberImage(int number) {
        return new Image(new Texture("./numbers/".concat(String.valueOf(number)).concat(".png")));
    }
}
