package no.ntnu.espegu.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controller extends Sprite {
    private static final int DEFAULT_HEIGHT = 30;
    private static final int DEFAULT_WIDTH = 300;
    private static final String CONTROLLER_SPRITE = "block_controller.png";

    public Controller(float x, float y) {
        super(x, y, DEFAULT_HEIGHT, DEFAULT_WIDTH, CONTROLLER_SPRITE);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                texture.getTexture(),
                x,
                y,
                300,
                15);
    }
}
