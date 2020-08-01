package com.adamuro.flappy.sprites;

import com.adamuro.flappy.FlappyBird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground extends Sprite {
    public static final int WIDTH = 336;
    public static final int HEIGHT = 110;
    private Vector2 velocity;
    private Vector2 position;
    private Rectangle bounds;
    private Texture ground;

    public Ground(float x, float y) {
        this.velocity = new Vector2(FlappyBird.SPEED, 0);
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, WIDTH, HEIGHT);
        this.ground = new Texture("ground.png");
    }

    public void update(float delta) {
        this.velocity.scl(delta);
        this.position.add(velocity);
        this.velocity.scl(1 / delta);
    }

    public void dispose() {
        this.ground.dispose();
    }

    public void reposition(float x) {
        this.position.set(x, 0);
    }

    public Vector2 getPosition() { return position; }
    public Rectangle getBounds() { return bounds; }
    public Texture getTexture() { return ground; }
}
