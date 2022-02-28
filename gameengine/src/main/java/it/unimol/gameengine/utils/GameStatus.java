package it.unimol.gameengine.utils;

/**
 * Represent the possible statuses of the game.
 *
 * @author Maurizio Albani
 */
public enum GameStatus {
    /**
     * The game is being played
     */
    PLAYING,

    /**
     * The player won
     */
    WINNER,

    /**
     * The player lost
     */
    GAMEOVER;
}