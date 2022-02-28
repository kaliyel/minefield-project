package it.unimol.gameengine.components;

import it.unimol.gameengine.utils.CellContentID;

/**
 * Class implementation of a single Cell of a Board ({@link Board})
 *
 * @author Maurizio Albani
 */
public class Cell {
    private CellContentID contentID;
    private boolean cellStatus;

    Cell(){
        this.cellStatus = false;
    }

    /**
     * Marks the cell, setting It's status to {@code true}
     */
    public void markCell() {
        this.cellStatus = true;
    }

    /*
     * public void unmarkCell(){
     *     this.cellStatus = false;
     * }
     */

    /**
     * Checks the status of the Cell
     * @return {@code true} if It's marked, {@code false} otherwise
     */
    public boolean isMarked() {
        return this.cellStatus;
    }

    /**
     * Returns the ID of the content of the Cell
     * @return the content of the cell (as defined in {@link CellContentID})
     */
    public CellContentID getContentID() {
        assert this.contentID != null;
        return this.contentID;
    }

    void setContentID(CellContentID contentID) {
        assert this.contentID == null;
        assert contentID != null;
        this.contentID = contentID;
    }
}