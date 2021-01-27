package no.ntnu.espegu.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

@AwardsPoints
public class DraggableController extends Controller implements InputProcessor {

    public DraggableController(float x, float y) {
        super(x, y);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        setX(Math.min(screenX, Gdx.graphics.getWidth() - 300));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        setX(Math.min(screenX, Gdx.graphics.getWidth() - 300));
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
