package it.unimol.gameengine.utils;

/**
 * Utility Class that contains the texts used in the Game
 *
 * @author Maurizio Albani
 */
public final class UtilStrings {
    private UtilStrings() { }

    public static final String MINEFIELD = "Minefield";

    public static final String BYAUTHOR = "by Maurizio Albani";

    public static final String NROWS = "N. Rows (max 8):";

    public static final String NCOLUMNS = "N. Columns (max 8):";

    public static final String NBOMBS = "N. Bombs:";

    public static final String BOMBSINSERTED = "Bombs inserted: ";

    public static final String MAXBOMBS = " | Max Bombs allowed: ";

    public static final String VICTORY = "CONGRATULATIONS!";

    public static final String WINNER = "You won the Game!!";

    public static final String DEFEAT = "OUCH...";

    public static final String LOSER = "It seams that you lost...";

    public static final String WANNAQUIT = "Are You sure You want to quit the game?";

    public static final String QUIT = "Quit the Game";

    public static final String INFO = "About the Game";

    public static final String NEWGAME = "New Game";

    public static final String MAINMENU = "Main Menu";

    public static final String EASY = "Easy";

    public static final String NORMAL = "Normal";

    public static final String HARD = "Hard";

    public static final String CUSTOM = "Custom";

    public static final String CONFIRM = "Confirm";

    public static final String CHOSEDIFFICULTY = "Chose the difficulty of the Game";

    public static final String TUTORIAL = "<html>" + "The objective of the game is to find all the Safe spaces"
            + "<br/>" + "inside of the Board." + "<html>";

    public static final String INDICATOR_SCENE = "<html>" + "The board will show the number"
            + "<br/>" + "of bombs inside each row"
            + "<br/>" + "and each column."
            + "<br/>" + "(As shown in the picture)" + "<html>";

    public static final String LOSING_SCENE = "<html>" + "If you stumble upon a bomb"
            + "<br/>" + "You lose the game." + "<html>";

    public static final String WRONGINPUTTITLE = "Wrong Input Format";

    public static final String WRONGINPUT = "ERROR: you inserted an incorrect number" + "\n"
            + "The inserted Parameters have to be positive Integers." + "\n"
            + "Rows and Columns can only be less or equal to 8.";

    public static final String WRONGBOARDSIZETITLE = "Wrong Board Size";

    public static final String WRONGBOARDSIZE = "ERROR: you inserted an incorrect board size" + "\n"
            + "Rows and Columns must be positive integers.";

    public static final String WRONGBOMBNUMBERTITLE = "Wrong Bomb Number";

    public static final String WRONGBOMBNUMBER = "ERROR: wrong number of Bombs!" + "\n";
}
