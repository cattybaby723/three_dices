package com.example.anrou_hu.three_dices.db;

/**
 * @author anrou_hu
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.anrou_hu.three_dices.db.dao.DiceRollingDao;
import com.example.anrou_hu.three_dices.db.entity.DiceRollingResult;

@Database(entities = {DiceRollingResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DiceRollingDao diceRollingDao();

}
