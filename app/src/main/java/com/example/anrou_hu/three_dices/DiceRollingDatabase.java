package com.example.anrou_hu.three_dices;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * @author anrou_hu
 */

@Database(entities = {DiceRollingResult.class}, version = 1)
public abstract class DiceRollingDatabase extends RoomDatabase {
    public abstract DiceRollingDao diceRollingDao();

}
