package com.example.anrou_hu.three_dices;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author anrou_hu
 */

public class DatabaseCreator implements AsyncTaskCallback {

    public static DatabaseCreator sInstance;

    public MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    private AppDatabase mDb;

    private AtomicBoolean mInitializing = new AtomicBoolean(true);

    public static synchronized DatabaseCreator getInstance() {
        if (sInstance == null) {
            sInstance = new DatabaseCreator();
        }

        return sInstance;
    }


    public void createDb(Context context) {
        if (!mInitializing.compareAndSet(true, false)) {
            return;
        }

        mIsDatabaseCreated.setValue(false);
        InitDatabaseAsyncTask task = new InitDatabaseAsyncTask(this);
        task.execute(context);
    }

    public MutableLiveData<Boolean> isDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    public AppDatabase getDb() {
        return mDb;
    }

    @Override
    public void processFinish(Object result) {
        if (result instanceof AppDatabase) {
            mDb = (AppDatabase) result;
            mIsDatabaseCreated.setValue(true);
        }
    }
}
