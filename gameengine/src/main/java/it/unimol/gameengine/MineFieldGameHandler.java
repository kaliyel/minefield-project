package it.unimol.gameengine;

import it.unimol.gameengine.components.Cell;
import it.unimol.gameengine.components.MineFieldBoard;
import it.unimol.gameengine.exceptions.BoardCoordinatesOutOfBoundException;
import it.unimol.gameengine.exceptions.WrongBoardSizeException;
import it.unimol.gameengine.exceptions.WrongBombQuantityException;
import it.unimol.gameengine.utils.CellContentID;
import it.unimol.gameengine.utils.GameStatus;

/**
 * Class that handles the logic of MineField
 *
 * @author Maurizio Albani
 */
public class MineFieldGameHandler {
    private static final MineFieldGameHandler instance = new MineFieldGameHandler();
    public static MineFieldGameHandler getInstance(){
        return MineFieldGameHandler.instance;
    }

    private static MineFieldBoard gameBoard;

    private MineFieldGameHandler() {
    }

    /**
     * Initializes the game with a ({@code rows}x{@code columns}) board, with {@code bombs} bombs
     * @param rows Number of rows of the MineField board
     * @param columns Number of columns of the MineField board
     * @param bombs Number of total bombs in the MineField board
     * @throws WrongBoardSizeException If {@code rows} or {@code columns} are less than 0 or greater than 8
     * @throws WrongBombQuantityException if {@code bombs} is less or equal to 0 or
     * if it's greater then 2/3 of the number of cells rounded down
     */
    public void initializeGame(int rows, int columns, int bombs) throws
            WrongBoardSizeException,
            WrongBombQuantityException {
        gameBoard = new MineFieldBoard(rows, columns, bombs);
    }

    /**
     * Marks a cell positioned at {@code row} row and {@code column} column
     * @param row The index of the Row
     * @param column The index of the Column
     * @throws BoardCoordinatesOutOfBoundException if {@code row} or {@code column} are less then 0 or greater then
     * (respectively) the number of Rows or the number of Columns of the board
     */
    public void markCell(int row, int column) throws BoardCoordinatesOutOfBoundException {
        assert gameBoard != null;
        Cell cell = gameBoard.getCell(row, column);
        if (cell.isMarked())
            return;
        cell.markCell();
    }

    /**
     * Returns the status of the Game
     * @return {@code GameStatus.WINNER} if the player won,
     * {@code GameStatus.GAMEOVER} if the player lost,
     * {@code GameStatus.PLAYING} otherwise
     */
    public GameStatus getGameStatus(){
        assert gameBoard != null;
        if (this.checkIfWinner()){
            return GameStatus.WINNER;
        } else if(this.checkIfLoser()) {
            return GameStatus.GAMEOVER;
        } else{
            return GameStatus.PLAYING;
        }
    }

    /**
     * Returns the content of the Cell positioned at {@code row} row and {@code column} column
     * @param row the index of the row
     * @param column the index of the column
     * @return {@code CellContentID.BOMB} if the Cell contains a bomb, {@code CellContentID.SAFESPACE} otherwise
     * @throws BoardCoordinatesOutOfBoundException if {@code row} or {@code column} are less then 0 or greater then
     * (respectively) the number of Rows or the number of Columns of the board
     */
    public CellContentID getCellContent(int row, int column) throws BoardCoordinatesOutOfBoundException {
        assert gameBoard != null;
        return gameBoard.getCell(row, column).getContentID();
    }

    /**
     * Calculates the number of bombs contained in the raw with index {@code row}
     * @param row the index of the row
     * @return the number of bombs
     * @throws BoardCoordinatesOutOfBoundException if {@code row} is less than 0
     * or greater than the number of Rows
     */
    public int getBombsByRow(int row) throws BoardCoordinatesOutOfBoundException {
        assert gameBoard != null;
        return gameBoard.getBombQuantityByRow(row);
    }

    /**
     * Calculates the number of bombs contained in the column with index {@code column}
     * @param column the index of the column
     * @return the number of bombs
     * @throws BoardCoordinatesOutOfBoundException if {@code column} is less than 0
     * or greater than the number of Columns
     */
    public int getBombsByColumn(int column) throws BoardCoordinatesOutOfBoundException {
        assert gameBoard != null;
        return gameBoard.getBombQuantityByColumn(column);
    }

    /**
     * @return the number of Rows of the board
     */
    public int getGameBoardRows(){
        assert gameBoard != null;
        return gameBoard.getRows();
    }

    /**
     * @return the number of Columns of the board
     */
    public int getGameBoardColumns(){
        assert gameBoard != null;
        return gameBoard.getColumns();
    }

    private boolean checkIfWinner() {
        return gameBoard.isAllMarked();
    }

    private boolean checkIfLoser() {
        Cell cell;
        int rows = gameBoard.getRows();
        int columns = gameBoard.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                try {
                    cell =gameBoard.getCell(i, j);
                    if(cell.isMarked()) {
                        if (cell.getContentID() == CellContentID.BOMB) {
                            return true;
                        }
                    }
                } catch (BoardCoordinatesOutOfBoundException e) {
                    assert false;
                }
            }
        }
        return false;
    }
}