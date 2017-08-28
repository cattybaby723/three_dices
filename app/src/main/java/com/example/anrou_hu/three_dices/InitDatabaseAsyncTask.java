package com.example.anrou_hu.three_dices;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

/**
 * @author anrou_hu
 */

public class InitDatabaseAsyncTask extends AsyncTask<Context, Void, AppDatabase> {

    private AsyncTaskCallback mCallback;

    public InitDatabaseAsyncTask(AsyncTaskCallback callback) {
        mCallback = callback;
    }

    @Override
    protected AppDatabase doInBackground(Context... params) {
        Context context = params[0].getApplicationContext();
        context.deleteDatabase(AppDatabase.DATA_BASE_NAME);

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATA_BASE_NAME).build();
        return db;
    }

    @Override
    protected void onPostExecute(AppDatabase database) {
        mCallback.processFinish(database);
    }
}
