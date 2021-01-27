package no.ntnu.espegu.sprites;

import com.badlogic.gdx.Gdx;

import no.ntnu.espegu.CurrentDirectionX;

public class AiController extends Controller {

    private CurrentDirectionX currentDirection = CurrentDirectionX.RIGHT;

    public AiController(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(Sprite... siblings) {
        if(currentDirection == CurrentDirectionX.RIGHT) {
            setX(x + 60);
            if(x >= Gdx.graphics.getWidth() - 300) {
                currentDirection = CurrentDirectionX.LEFT;
            }
        }

        if(currentDirection == CurrentDirectionX.LEFT) {
            setX(x - 60);
            if(x == 0) {
                currentDirection = CurrentDirectionX.RIGHT;
            }
        }
    }
}
