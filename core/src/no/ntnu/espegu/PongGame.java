package no.ntnu.espegu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import no.ntnu.espegu.model.AiController;
import no.ntnu.espegu.model.Ball;
import no.ntnu.espegu.model.DraggableController;

public class PongGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private DraggableController player;
    private AiController ai;
    private Ball ball;
    private BitmapFont winnerText;
    private BitmapFont winsAiText;
    private BitmapFont winsPlayerText;
    private Stage stage;
    private TextButton restartGameButton;

    @Override
    public void create() {
        batch = new SpriteBatch();
        float yMax = Gdx.graphics.getHeight();
        float xMax = Gdx.graphics.getWidth() - 30;
        player = new DraggableController(0, 0);
        ai = new AiController(0, yMax - 30);
        ball = new Ball(xMax, yMax, Math.round(yMax / 2), Math.round(xMax / 2));
        stage = new Stage();

        // Allows multiple input processors to be registered
        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(player);

        Gdx.input.setInputProcessor(inputMultiplexer);

        createMenuTextures();
    }

    private void createMenuTextures() {
        BitmapFont defaultFontSet = new BitmapFont(Gdx.files.internal("arial-64.fnt"),
                Gdx.files.internal("arial-64.png"), false);

        winnerText = defaultFontSet;
        winsPlayerText = defaultFontSet;
        winsAiText = defaultFontSet;

        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
        Skin skin = new Skin();
        skin.addRegions(buttonAtlas);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont(Gdx.files.internal("arial-32.fnt"),
                Gdx.files.internal("arial-32.png"), false);
        textButtonStyle.font.getData().setScale(2f);
        textButtonStyle.up = skin.getDrawable("btn_texture");
        textButtonStyle.down = skin.getDrawable("btn_texture");
        textButtonStyle.checked = skin.getDrawable("btn_texture");
        restartGameButton = new TextButton("Restart game", textButtonStyle);
        stage.addActor(restartGameButton);
        restartGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameState.getInstance().restartGame();
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        winsPlayerText.setColor(Color.GRAY);
        winsAiText.setColor(Color.YELLOW);

        if (GameState.getInstance().isGameFinished()) {
           renderGameFinishedScreen();
        } else {
            if (restartGameButton.isVisible()) {
                restartGameButton.setVisible(false);
            }
            player.render(batch);
            ai.render(batch);
            ball.render(batch);
            ball.checkForCollision(ai, player);

            winsPlayerText.draw(batch, GameState.getInstance().getWinsPlayer() + "", 50, 75);
            winsAiText.draw(batch, GameState.getInstance().getWinsAi() + "", 50, Math.round(Gdx.graphics.getHeight() - 75));
        }

        batch.end();
    }

    private void renderGameFinishedScreen() {
        if (!restartGameButton.isVisible()) {
            restartGameButton.setVisible(true);
        }
        winnerText.getData().setScale(2f);
        winnerText.draw(batch, GameState.getInstance().getWinner() + " WON!", Math.round(Gdx.graphics.getWidth() / 2f - 180), Math.round(Gdx.graphics.getHeight() / 2f));
        winsAiText.setColor(Color.GRAY);
        winsAiText.draw(batch, GameState.getInstance().getWinsPlayer() + " (PLAYER)" + " - " + GameState.getInstance().getWinsAi() + " (AI)", 50, Math.round(Gdx.graphics.getHeight() - 75));
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
