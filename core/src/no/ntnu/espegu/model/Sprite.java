package no.ntnu.espegu.model;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.UUID;

public abstract class Sprite {
    UUID uuid;
    float x;
    float y;
    int height;
    int width;
    TextureRegion texture;

    public Sprite(float x, float y, int height, int width, String assetImage) {
        uuid = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.texture = new TextureRegion(new Texture(assetImage), width, height);
    }

    public Sprite() {
        // Empty default constructor
        uuid = UUID.randomUUID();
    }

    public abstract void render(SpriteBatch batch);

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public UUID getUuid() {
        return uuid;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
