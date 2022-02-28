package it.unimol.gameengine.components;

import it.unimol.gameengine.exceptions.BoardCoordinatesOutOfBoundException;
import it.unimol.gameengine.exceptions.WrongBoardSizeException;
import it.unimol.gameengine.exceptions.WrongBombQuantityException;
import it.unimol.gameengine.utils.CellContentID;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static it.unimol.gameengine.utils.UtilStrings.*;

/**
 * Class implementation of a generic (n x m) Board.
 *
 * @author Maurizio Albani
 */
public abstract class Board {
    private final int rows;
    private final int columns;
    private final Cell[][] board;
    private Random random;
    private final int bombs;

    /**
     * Class constructor that creates a new ({@code rows}x{@code columns}) Board and
     * sets n.{@code bombs} bombs in random positions.<br>
     * @param rows Number of rows of the Board
     * @param columns Number of columns of the Board
     * @param bombs Number of bombs inside the Board
     * @throws WrongBoardSizeException If {@code rows} or {@code columns} are less than 0 or greater than 8
     * @throws WrongBombQuantityException if {@code bombs} is less or equal to 0 or
     * if it's greater then 2/3 of the number of cells rounded down
     */
    protected Board(int rows, int columns, int bombs) throws
            WrongBoardSizeException,
            WrongBombQuantityException {

        final int maxBombs = 2 * ((rows * columns) / 3);
        if ((rows <= 0) || (rows > 8) || (columns <= 0) || (columns > 8))
            throw new WrongBoardSizeException();
        if (bombs <= 0 || bombs > maxBombs){
            throw new WrongBombQuantityException(
                    BOMBSINSERTED + bombs + MAXBOMBS + maxBombs);
        }

        this.rows = rows;
        this.columns = columns;
        this.bombs = bombs;
        this.board = new Cell[rows][columns];

        this.initializeBoardContent();
    }

    /**
     * Returns the Cell located at the coordinates specified by the parameters passed as input
     * @param row Row index (from 0 to the number of rows -1)
     * @param column Column index (from 0 to the number of columns -1)
     * @return the cell at coordinates ({@code row}, {@code column})
     * @throws BoardCoordinatesOutOfBoundException if {@code row} or {@code column} are less then 0 or
     * if {@code row} or {@code column} are greater or equal to (respectively) the number of rows or the number of columns
     */
    public Cell getCell(int row, int column) throws BoardCoordinatesOutOfBoundException {
        if (row < 0 || row >= this.rows)
            throw new BoardCoordinatesOutOfBoundException();
        if (column < 0 || column >= this.columns)
            throw new BoardCoordinatesOutOfBoundException();

        return this.board[row][column];
    }

    /**
     * Returns the number of bombs inside the row with the index passed as parameter
     * @param row index of the row
     * @return the number of bombs found inside the specified row (from 0 to the number of columns)
     * @throws BoardCoordinatesOutOfBoundException if {@code row} is less then 0 or greater than the number
     * of rows of the board
     */
    public int getBombQuantityByRow(int row) throws BoardCoordinatesOutOfBoundException {
        if (row < 0 || row >= this.rows)
            throw new BoardCoordinatesOutOfBoundException();
        int bombQuantity = 0;

        for(int j = 0; j < this.columns; j++){
            if (this.board[row][j].getContentID() == CellContentID.BOMB)
                bombQuantity++;
        }

        return bombQuantity;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getBombs(){
        return this.bombs;
    }

    /**
     * Returns the number of bombs inside the row with the index passed as parameter
     * @param column index of the column
     * @return the number of bombs found inside the specified column (from 0 to the number of rows)
     * @throws BoardCoordinatesOutOfBoundException if {@code column} is less then 0 or greater than the number
     * of columns of the board
     */
    public int getBombQuantityByColumn(int column) throws BoardCoordinatesOutOfBoundException {
        if (column < 0 || column >= this.columns)
            throw new BoardCoordinatesOutOfBoundException();
        int bombs = 0;

        for (int i = 0; i < this.rows; i++) {
            if (this.board[i][column].getContentID() == CellContentID.BOMB)
                bombs++;
        }

        return bombs;
    }

    /**
     * Checks if all the Cells that contain Safespaces are marked
     * @return {@code true} if all the Cells that contain Safespaces are marked, {@code false} otherwise
     */
    public boolean isAllMarked(){
        int safeSpaces = (this.rows * this.columns) - this.bombs;

        for (int i = 0; i <  this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.board[i][j].isMarked())
                    safeSpaces--;
            }
        }
        return (safeSpaces == 0);
    }

    private void initializeBoardContent() {
        this.random = new Random();
        List<Point> allBombCoordinates = this.generateBombCoordinates();
        Point bombCoordinate;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.board[i][j] = new Cell();
                bombCoordinate = new Point(i, j);
                if (allBombCoordinates.contains(bombCoordinate)) {
                    this.board[i][j].setContentID(CellContentID.BOMB);
                } else {
                    this.board[i][j].setContentID(CellContentID.SAFESPACE);
                }
            }
        }
    }

    private List<Point> generateBombCoordinates(){
        int x, y;
        int lastingBombs = this.bombs;

        Point bombCoordinate;
        List<Point> allBombCoordinates = new LinkedList<>();

        while(lastingBombs > 0) {
            x = random.nextInt(this.rows);
            y = random.nextInt(this.columns);
            bombCoordinate = new Point(x, y);

            if (!allBombCoordinates.contains(bombCoordinate)) {
                allBombCoordinates.add(bombCoordinate);
                lastingBombs--;
            }
        }
        return allBombCoordinates;
    }
}
