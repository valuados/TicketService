package com.valuados.tickets.entities;

/**
 * Created by valua on 4/5/2017.
 */
public class Place {
    private int row;
    private int seat;

    public int getRow() {
        return row;
    }

    public Place(int row, int seat) {
        this.row = row;
        this.seat = seat;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
