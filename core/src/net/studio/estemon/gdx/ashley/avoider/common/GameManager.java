package net.studio.estemon.gdx.ashley.avoider.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import net.studio.estemon.gdx.ashley.avoider.ObstacleAvoiderGame;
import net.studio.estemon.gdx.ashley.avoider.config.DifficultyLevel;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();
    private static final String HIGH_SCORE_KEY = "highscore";
    private static final String DIFFICULTY_KEY = "difficulty";
    private final Preferences PREFS;
    private int highscore;
    private DifficultyLevel difficultyLevel;
    private int lives = GameConfig.LIVES_START;
    private int score;

    // SINGLETON, not instantiable
    private GameManager() {
        PREFS = Gdx.app.getPreferences(ObstacleAvoiderGame.class.getSimpleName());
        highscore = PREFS.getInteger(HIGH_SCORE_KEY, 0);
        String difficultyName = PREFS.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name());
        difficultyLevel = DifficultyLevel.valueOf(difficultyName);
    }

    public void updateHighscore() {
        if (score < highscore) {
            return;
        }
        highscore = score;
        PREFS.putInteger(HIGH_SCORE_KEY, highscore);
        PREFS.flush();
    }

    public String getHighScoreString() {
        return String.valueOf(highscore);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void updateScore(int amount) { score += amount; }

    public void decrementLives() { lives--; }

    public boolean isGameOver() { return lives <= 0; }

    public void reset() {
        lives = GameConfig.LIVES_START;
        score = 0;
    }

    public int getLives() { return lives; }

    public int getScore() { return score; }

    public void updateDifficulty(DifficultyLevel newDifficultyLevel) {
        if (difficultyLevel == newDifficultyLevel) {
            return;
        }
        difficultyLevel = newDifficultyLevel;
        PREFS.putString(DIFFICULTY_KEY, difficultyLevel.name());
        PREFS.flush();
    }
}
