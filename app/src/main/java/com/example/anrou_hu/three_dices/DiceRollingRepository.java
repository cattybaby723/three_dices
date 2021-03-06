package com.example.anrou_hu.three_dices;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;


/**
 * @author anrou_hu
 */

public class DiceRollingRepository implements Executor {

    interface Callback {
        void onResultLoaded(Object data);
    }

    private Application mApplication;
    private AppDatabase mDatabase;

    public DiceRollingRepository(Application application) {
        mApplication = application;
        initDb();
    }

    public void initDb() {
        mDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "database-name").build();
    }



    public void getResult(final Callback callback) {
        execute(new Runnable() {
            @Override
            public void run() {
                List<DiceRollingResult> resultList = mDatabase.diceRollingDao().load();
                callback.onResultLoaded(resultList);
            }
        });
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
