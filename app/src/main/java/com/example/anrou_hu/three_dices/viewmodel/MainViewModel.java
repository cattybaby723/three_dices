package com.example.anrou_hu.three_dices.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.anrou_hu.three_dices.model.AsyncTaskCallback;
import com.example.anrou_hu.three_dices.model.DiceRollingAsyncTask;
import com.example.anrou_hu.three_dices.db.DiceRollingRepository;
import com.example.anrou_hu.three_dices.db.entity.DiceRollingResult;

import java.util.List;

/**
 * @author anrou_hu
 */

public class MainViewModel extends AndroidViewModel implements LifecycleObserver, AsyncTaskCallback {

    private final static int DICE_COUNT = 3;

    private MutableLiveData<Integer> mTotalPoint = new MutableLiveData<>();
    private MutableLiveData<int[]> mDicePoints = new MutableLiveData<int[]>() {
    };
    private MediatorLiveData<List<DiceRollingResult>> mDiceRollingResultList;

    private DiceRollingRepository mDiceRollingRepo;


    public MainViewModel(Application application) {
        super(application);
        mDiceRollingRepo = new DiceRollingRepository(getApplication());

        initDb();
    }

    private void initDb() {
        mDiceRollingRepo.initDb();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void create() {
        Log.d("Lifecycle", "Test Lifecycle : on create~");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        Log.d("Lifecycle", "Test Lifecycle : on start~");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        Log.d("Lifecycle", "Test Lifecycle : on stop~");
    }


    public LiveData<int[]> getDicePoints() {
        return mDicePoints;
    }

    public LiveData<List<DiceRollingResult>> getDiceRollingResultList() {
        if (mDiceRollingResultList == null) {
            mDiceRollingResultList = new MediatorLiveData<>();
        }
        return mDiceRollingResultList;
    }


    public void rollDices() {
        DiceRollingAsyncTask task = new DiceRollingAsyncTask(this, mDiceRollingRepo, DICE_COUNT);
        task.execute();
    }


    @Override
    public void processFinish(Object result) {
        int[] diceList = (int[]) result;
        mDicePoints.setValue(diceList);

        int totalPoint = calculateTotalPoint(diceList);
        mTotalPoint.setValue(totalPoint);
    }

    public void observeDbData() {
        mDiceRollingResultList.addSource(mDiceRollingRepo.getResult(), new Observer<List<DiceRollingResult>>() {
            @Override
            public void onChanged(@Nullable List<DiceRollingResult> diceRollingResults) {
                mDiceRollingResultList.setValue(diceRollingResults);
            }
        });
    }

    public int calculateTotalPoint(int[] diceList) {
        int totalPoint = 0;
        for (int dicePoint : diceList) {
            totalPoint += dicePoint;
        }
        return totalPoint;
    }
}
