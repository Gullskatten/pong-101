package no.ntnu.espegu.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import no.ntnu.espegu.Coordinate;
import no.ntnu.espegu.CurrentDirectionX;
import no.ntnu.espegu.CurrentDirectionY;
import no.ntnu.espegu.GameState;
import no.ntnu.espegu.observer.BallDirectionChangeObserver;
import no.ntnu.espegu.observer.BallPositionChangeObserver;

public class Ball extends Sprite {

    private final float DEFAULT_SPEED = 9f;
    private static final int DEFAULT_HEIGHT = 30;
    private static final int DEFAULT_WIDTH = 30;
    private float speed = DEFAULT_SPEED;
    private final float xMax;
    private final float yMax;
    private CurrentDirectionY currentDirectionY;
    private CurrentDirectionX currentDirectionX;

    public Ball(float xMax, float yMax, float x, float y) {
        super(x, y, DEFAULT_HEIGHT, DEFAULT_WIDTH, "ball.png");
        this.xMax = xMax;
        this.yMax = yMax;
        Random random = new Random();
        currentDirectionX = random.nextInt(2) >= 1 ? CurrentDirectionX.RIGHT : CurrentDirectionX.LEFT;
        currentDirectionY = random.nextInt(2) >= 1 ? CurrentDirectionY.DOWN : CurrentDirectionY.UP;
        BallDirectionChangeObserver.getInstance().notify(currentDirectionY);
    }

    @Override
    public void render(SpriteBatch batch) {
        changePosition();
        batch.draw(
                texture.getTexture(),
                x,
                y,
                15,
                15,
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT);
    }

    private void onHit() {
        currentDirectionY = currentDirectionY == CurrentDirectionY.UP ? CurrentDirectionY.DOWN : CurrentDirectionY.UP;
        speed += 2;
        BallDirectionChangeObserver.getInstance().notify(currentDirectionY);
    }

    private void changePosition() {
        switch (currentDirectionX) {
            case LEFT:
                if (x <= 0) {
                    x += speed;
                    currentDirectionX = CurrentDirectionX.RIGHT;
                } else {
                    x -= speed;
                }
                break;
            case RIGHT:
                if (x >= xMax) {
                    x -= speed;
                    currentDirectionX = CurrentDirectionX.LEFT;
                } else {
                    x += speed;
                }
                break;
        }

        switch (currentDirectionY) {
            case DOWN:
                if (y <= -10) {
                    GameState.getInstance().incrementWinsAi();
                    softReset();
                } else {
                    y -= speed;
                }
                break;
            case UP:
                if (y >= yMax + 10) {
                    GameState.getInstance().incrementWinsPlayer();
                    softReset();
                } else {
                    y += speed;
                }
                break;
        }
        BallPositionChangeObserver.getInstance().notify(new Coordinate(x, y));
    }

    public void softReset() {
        speed = DEFAULT_SPEED;
        setX(Math.round(xMax / 2));
        setY(Math.round(yMax / 2));
        Random random = new Random();
        currentDirectionX = random.nextInt(2) >= 1 ? CurrentDirectionX.RIGHT : CurrentDirectionX.LEFT;
        currentDirectionY = random.nextInt(2) >= 1 ? CurrentDirectionY.DOWN : CurrentDirectionY.UP;
    }

    public void checkForCollision(Sprite... sprites) {
        for(Sprite sprite : sprites) {
            Rectangle rectOfSpriteWithUpdatedPosition = new Rectangle(sprite.x, sprite.y, sprite.width, sprite.height);
            Rectangle thisRect = new Rectangle(x, y, width, height);
            if (thisRect.overlaps(rectOfSpriteWithUpdatedPosition)) {
                onHit();
            }
        }
    }
}
