package com.example.anrou_hu.three_dices;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * @author anrou_hu
 */

@Dao
public interface DiceRollingDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void save(DiceRollingResult diceRollingResult);


    @Query("SELECT * FROM diceRollingResult GROUP BY totalPoint")
    LiveData<List<DiceRollingResult>> load();


    @Query("SELECT * FROM diceRollingResult Group By totalPoint")
    List<DiceRollingResult> loadSync();

}
