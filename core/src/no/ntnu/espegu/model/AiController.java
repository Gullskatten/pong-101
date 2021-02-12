package no.ntnu.espegu.model;

import java.util.Observable;
import java.util.Observer;

import no.ntnu.espegu.Coordinate;
import no.ntnu.espegu.CurrentDirectionY;
import no.ntnu.espegu.observer.BallDirectionChangeObserver;
import no.ntnu.espegu.observer.BallPositionChangeObserver;

public class AiController extends Controller implements Observer {
    boolean isInactive = false;

    public AiController(float x, float y) {
        super(x, y);
        BallPositionChangeObserver.getInstance().addObserver(this);
        BallDirectionChangeObserver.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof BallDirectionChangeObserver) {
            // If it's players turn, inactivate self
           isInactive = arg == CurrentDirectionY.DOWN;
        }

        if(o instanceof BallPositionChangeObserver && !isInactive) {
            Coordinate ballCoordinates = (Coordinate) arg;
            if (ballCoordinates.getX() > getX()) {
                setX(x + 25);
            } else if (ballCoordinates.getX() < getX()) {
                setX(x - 25);
            }
        }
    }
}
