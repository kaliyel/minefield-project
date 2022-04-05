package it.unimol.gameengine.components;

import it.unimol.gameengine.exceptions.WrongBoardSizeException;
import it.unimol.gameengine.exceptions.WrongBombQuantityException;

/**
 * Class representation of a generic Minefield Board
 *
 * @author Maurizio Albani
 */
public class MineFieldBoard extends Board {

    public MineFieldBoard(int rows, int columns, int bombsQuantity) throws
            WrongBoardSizeException,
            WrongBombQuantityException {
        super(rows, columns, bombsQuantity);
    }
}
