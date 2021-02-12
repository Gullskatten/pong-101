package no.ntnu.espegu.observer;

import java.util.Observable;

import no.ntnu.espegu.CurrentDirectionY;

/**
 * Utilizing the java.util.Observable to create a concrete observable which
 * will be updated once the ball changes direction.
 *
 * The AI controlled paddle is only active once the ball changes direction to UP (towards the AI).
 *
 */
public class BallDirectionChangeObserver extends Observable {
    private static final BallDirectionChangeObserver instance = new BallDirectionChangeObserver();

    private BallDirectionChangeObserver() {
    }

    public void notify(CurrentDirectionY currentDirection) {
        setChanged();
        notifyObservers(currentDirection);
    }

    public static BallDirectionChangeObserver getInstance() {
        return instance;
    }
}
