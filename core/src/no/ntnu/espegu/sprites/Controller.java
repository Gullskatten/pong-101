package no.ntnu.espegu.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controller extends Sprite {
    private static final int DEFAULT_HEIGHT = 30;
    private static final int DEFAULT_WIDTH = 300;
    private static final String CONTROLLER_SPRITE = "block_controller.png";

    public Controller(float x, float y) {
        super(x, y, DEFAULT_HEIGHT, DEFAULT_WIDTH, CONTROLLER_SPRITE);
    }

    @Override
    public void update(Sprite... siblings) {
    }

    @Override
    public void render(SpriteBatch batch, float elapsedTime) {
        batch.draw(
                texture.getTexture(),
                x,
                y,
                300,
                15);
    }
}
