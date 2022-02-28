package it.unimol.gameengine.components;

import it.unimol.gameengine.exceptions.BoardCoordinatesOutOfBoundException;
import it.unimol.gameengine.exceptions.WrongBoardSizeException;
import it.unimol.gameengine.exceptions.WrongBombQuantityException;
import it.unimol.gameengine.utils.CellContentID;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    //I created a class that extends Board just for Test, to check if board methods work properly
    static class EXBoard extends Board{
        EXBoard(int row, int col, int bombs) throws WrongBoardSizeException, WrongBombQuantityException {
            super(row, col, bombs);
        }
    }
    //some variables i'll use inside the test cases
    private boolean wrongBoardSize;
    private boolean wrongBombQuantity;
    private boolean wrongBoardCoordinates;
    private boolean allMarkStatus;
    private EXBoard tempBoard;
    private Cell cell;

    //some methods i'll use inside the test cases
    void resetCheckers(){
        wrongBoardSize = false;
        wrongBombQuantity = false;
        wrongBoardCoordinates = false;
        allMarkStatus = false;

        tempBoard = null;
        cell = null;
    }
    private void newBoard(int rows, int cols, int bombs){
        try {
            tempBoard = new EXBoard(rows, cols, bombs);
        } catch (WrongBoardSizeException e) {
            wrongBoardSize = true;
        } catch (WrongBombQuantityException e) {
            wrongBombQuantity = true;
        }
    }

    private void getACell(int row, int col){
        try {
            cell = tempBoard.getCell(row, col);
        } catch (BoardCoordinatesOutOfBoundException e) {
            wrongBoardCoordinates = true;
        }
    }

    @Test
    void testCreateBoardObject() {
        //Case 1: all parameters are correct
        resetCheckers();
        newBoard(3, 3, 5);
        assertFalse(wrongBoardSize);
        assertFalse(wrongBombQuantity);
        assertNotNull(tempBoard);

        //Case 2: Row Parameter or column or both are Incorrect
        resetCheckers();
        newBoard(-5, 0, 2);
        assertTrue(wrongBoardSize);
        assertFalse(wrongBombQuantity);
        assertNull(tempBoard);

        //Case 3: Row and Column are correct, Number of Bombs incorrect;
        //I expect the maximum to be (5*10) = 50 / 3 = 16 * 2 = 38, so i'll insert 39
        resetCheckers();
        newBoard(5, 8, 39);
        assertFalse(wrongBoardSize);
        assertTrue(wrongBombQuantity);
        assertNull(tempBoard);
    }

    @Test
    void testGetCell() {
        //Case 1: correct Row, Correct Column
        resetCheckers();
        newBoard(5, 5, 5);
        getACell(2, 3);
        assertFalse(wrongBoardCoordinates);
        assertNotNull(cell);

        //Case 2: incorrect Row or Column
        resetCheckers();
        newBoard(5, 5, 5);
        getACell(7, 4);
        assertTrue(wrongBoardCoordinates);
        assertNull(cell);
    }


    private int getRowBombs(int row){
        int bombs = -1;
        try {
            bombs = tempBoard.getBombQuantityByRow(row);
        } catch (BoardCoordinatesOutOfBoundException e) {
            wrongBoardCoordinates =true;
        }
        assertNotEquals(-1, bombs);
        return bombs;
    }

    @Test
    void testGetBombQuantityByRow() {
        int bombs = 20;
        int rows = 8;
        int columns = 8;
        int totalBombsFound = 0;

        resetCheckers();

        newBoard(rows, columns, bombs);

        List<Integer> list = new LinkedList<>();
        int tempBombCounter;

        newBoard(rows, columns, bombs);

        for (int i = 0; i < rows; i++){
            tempBombCounter = getRowBombs(i);
            list.add(tempBombCounter);
            assertFalse(wrongBoardCoordinates);
        }

        for (Integer bombsInRaw: list) {
            totalBombsFound += bombsInRaw;
        }

        assertEquals(bombs, totalBombsFound);
    }

    private int getColumnBombs(int column){
        int bombs = -1;
        try {
            bombs = tempBoard.getBombQuantityByColumn(column);
        } catch (BoardCoordinatesOutOfBoundException e) {
            wrongBoardCoordinates =true;
        }
        assertNotEquals(-1, bombs);
        return bombs;
    }

    @Test
    void testGetBombQuantityByColumn() {
        int bombs = 20;
        int rows = 8;
        int columns = 8;
        int totalBombsFound = 0;

        resetCheckers();

        newBoard(rows, columns, bombs);

        List<Integer> list = new LinkedList<>();
        int tempBombCounter;

        for (int i = 0; i < columns; i++){
            tempBombCounter = getColumnBombs(i);
            list.add(tempBombCounter);
            assertFalse(wrongBoardCoordinates);
        }

        for (Integer bombsInColumn: list) {
            totalBombsFound += bombsInColumn;
        }

        assertEquals(bombs, totalBombsFound);
    }

    private void markSafespaces(int rows, int columns){

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                try {
                    this.cell = this.tempBoard.getCell(i, j);
                    if (!this.cell.isMarked())
                        if(this.cell.getContentID() == CellContentID.SAFESPACE)
                            this.cell.markCell();
                } catch (BoardCoordinatesOutOfBoundException e) {
                    wrongBoardCoordinates = true;
                }
            }
        }
    }

    @Test
    void testIsAllMarked() {
        int bombs = 10;
        int rows = 5;
        int columns = 6;

        resetCheckers();

        newBoard(rows, columns, bombs);

        allMarkStatus = this.tempBoard.isAllMarked();
        assertFalse(allMarkStatus);

        markSafespaces(rows, columns);
        allMarkStatus = this.tempBoard.isAllMarked();
        assertTrue(allMarkStatus);
    }

}