package no.ntnu.espegu.observer;

import java.util.Observable;

import no.ntnu.espegu.Coordinate;

/**
 * Utilizing the java.util.Observable to create a concrete observable which
 * will be updated when the ball changes its position.
 *
 */
public class BallPositionChangeObserver extends Observable {
    private static final BallPositionChangeObserver instance = new BallPositionChangeObserver();
    private BallPositionChangeObserver() { }

    public void notify(Coordinate coordinate) {
        setChanged();
        notifyObservers(coordinate);
    }

    public static BallPositionChangeObserver getInstance() {
        return instance;
    }
}
