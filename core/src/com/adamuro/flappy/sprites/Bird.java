package com.adamuro.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bird {
    private static final int WIDTH = 34;
    private static final int HEIGHT = 24;
    private static final Vector2 GRAVITY = new Vector2(0, -15);
    private float stateTime;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
    private Animation<TextureRegion> flyAnimation;

    public Bird(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        this.stateTime = 0;
        Texture bird = new Texture("bird.png");
        TextureRegion[] frames = TextureRegion.split(bird, WIDTH, HEIGHT)[0];
        this.flyAnimation = new Animation<>(0.5f, frames);
    }

    public void update(float delta) {
        this.velocity.add(GRAVITY);
        this.velocity.scl(delta);
        this.position.add(velocity);
        this.bounds.setPosition(position);
        this.stateTime += delta;
        this.velocity.scl(1 / delta);
    }

    public boolean collide(Rectangle bounds) { return this.bounds.overlaps(bounds); }

    public void jump() { this.velocity.y = 250; }

    public Vector2 getPosition() { return position; }

    public TextureRegion getTexture() { return flyAnimation.getKeyFrame(stateTime, true); }
}
