package com.example.anrou_hu.three_dices;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author anrou_hu
 */

//TODO: test indices later
@Entity
public class DiceRollingResult {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo
    private int totalPoint;

    @ColumnInfo
    private int amountOfDice;

    @ColumnInfo
    private int frequency;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getAmountOfDice() {
        return amountOfDice;
    }

    public void setAmountOfDice(int amountOfDice) {
        this.amountOfDice = amountOfDice;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}
