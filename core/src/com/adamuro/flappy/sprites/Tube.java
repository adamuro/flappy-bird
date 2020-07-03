package com.adamuro.flappy.sprites;

import com.adamuro.flappy.FlappyBird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube extends Sprite {
    public static final int HEIGHT = 320;
    public static final int WIDTH = 52;
    private static final int MIN_Y = -150;
    private static final int MAX_Y = 220;
    private static final int GAP = 100;
    private Random rand;
    private Vector2 velocity;
    private Vector2 topPosition;
    private Vector2 botPosition;
    private Rectangle botBounds;
    private Rectangle topBounds;
    private Texture botTube;
    private Texture topTube;

    public Tube(float x) {
        this.rand = new Random();
        this.velocity = new Vector2(FlappyBird.SPEED, 0);
        this.botPosition = new Vector2(x, rand.nextInt(MAX_Y) + MIN_Y);
        this.topPosition = new Vector2(x, botPosition.y + HEIGHT + GAP);
        this.botBounds = new Rectangle(botPosition.x, botPosition.y, WIDTH, HEIGHT);
        this.topBounds = new Rectangle(topPosition.x, topPosition.y, WIDTH, HEIGHT);
        this.botTube = new Texture("bot_tube.png");
        this.topTube = new Texture("top_tube.png");
    }

    public void update(float delta) {
        this.velocity.scl(delta);
        this.botPosition.add(velocity);
        this.topPosition.add(velocity);
        this.botBounds.setPosition(botPosition);
        this.topBounds.setPosition(topPosition);
        this.velocity.scl(1 / delta);
    }

    public void reposition(float x) {
        this.botPosition.set(x, rand.nextInt(MAX_Y) + MIN_Y);
        this.topPosition.set(x, botPosition.y + HEIGHT + GAP);
    }

    public void dispose() {
        botTube.dispose();
        topTube.dispose();
    }

    public Vector2 getBotPosition() { return botPosition; }
    public Vector2 getTopPosition() { return topPosition; }

    public Rectangle getBotBounds() { return botBounds; }
    public Rectangle getTopBounds() { return topBounds; }

    public Texture getBotTexture() { return botTube; }
    public Texture getTopTexture() { return topTube; }
}
