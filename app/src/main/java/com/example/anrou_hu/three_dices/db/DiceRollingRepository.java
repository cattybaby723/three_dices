package com.example.anrou_hu.three_dices.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.example.anrou_hu.three_dices.db.AppDatabase;
import com.example.anrou_hu.three_dices.db.entity.DiceRollingResult;

import java.util.List;
import java.util.concurrent.Executor;


/**
 * @author anrou_hu
 */

public class DiceRollingRepository implements Executor {

    private Application mApplication;
    private AppDatabase mDatabase;

    public DiceRollingRepository(Application application) {
        mApplication = application;
        initDb();
    }

    public void initDb() {
        mDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "database-name").build();
    }



    public LiveData<List<DiceRollingResult>> getResult() {

        return mDatabase.diceRollingDao().load();
    }


    public void saveResult(final DiceRollingResult result) {
        execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.diceRollingDao().save(result);
            }
        });

    }


    @Override
    public void execute(@NonNull Runnable command) {
        new Thread(command).start();
    }

}
