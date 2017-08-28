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

    private Application mApplication;

    public DiceRollingRepository(Application application) {
        mApplication = application;
        initDb();
    }

    public void initDb() {
        DatabaseCreator.getInstance().createDb(mApplication);
    }


    public LiveData<List<DiceRollingResult>> getResult() {
        AppDatabase db = DatabaseCreator.getInstance().getDb();
        return db.diceRollingDao().load();
    }


    public void saveResult(final DiceRollingResult result) {
        execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = DatabaseCreator.getInstance().getDb();
                db.diceRollingDao().save(result);
            }
        });

    }


    @Override
    public void execute(@NonNull Runnable command) {
        new Thread(command).start();
    }

}
