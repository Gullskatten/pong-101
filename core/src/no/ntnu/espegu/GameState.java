package no.ntnu.espegu;

/**
 * Implements the Singleton-pattern to ensure that all objects
 * interact with the same instance of the GameState.
 */
public class GameState {
    private static final GameState singleton = new GameState();
    private int winsPlayer = 0;
    private int winsAi = 0;
    private boolean isGameFinished = false;
    private String winner = "";

    private GameState() {
    }

    public static GameState getInstance() {
        return singleton;
    }

    public int getWinsPlayer() {
        return winsPlayer;
    }

    public int getWinsAi() {
        return winsAi;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public String getWinner() {
        return winner;
    }

    public void incrementWinsAi() {
        winsAi++;
        checkForWinner();
    }

    public void incrementWinsPlayer() {
        winsPlayer++;
        checkForWinner();
    }

    public void restartGame() {
        winner = null;
        isGameFinished = false;
        winsAi = 0;
        winsPlayer = 0;
    }

    private void checkForWinner() {
        int WIN_POINTS_REQUIRED = 5;

        if(winsPlayer == WIN_POINTS_REQUIRED) {
            winner = "PLAYER";
            isGameFinished = true;
        } else if (winsAi == WIN_POINTS_REQUIRED) {
            winner = "AI";
            isGameFinished = true;
        }
    }
}

