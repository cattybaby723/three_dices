package com.example.anrou_hu.three_dices;

/**
 * @author anrou_hu
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DiceRollingResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static String DATA_BASE_NAME = "dice_rolling_db";

    public abstract DiceRollingDao diceRollingDao();

}
