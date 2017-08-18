package com.example.anrou_hu.three_dices;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

/**
 * @author anrou_hu
 */

public class MainViewModel extends ViewModel implements LifecycleObserver, AsyncTaskCallback {

    private final static int DICE_COUNT = 3;

    private MutableLiveData<Integer> mTotalPoint = new MutableLiveData<>();
    private MutableLiveData<int[]> mDicePoints = new MutableLiveData<int[]>() {};



    public LiveData<int[]> getDicePoints() {
        return mDicePoints;
    }


    void rollDices() {
        DiceRollingAsyncTask task = new DiceRollingAsyncTask(this, DICE_COUNT);
        task.execute();
    }



    @Override
    public void processFinish(Object result) {
        int[] diceList = (int[]) result;
        mDicePoints.setValue(diceList);

        int totalPoint = calculateTotalPoint(diceList);
        mTotalPoint.setValue(totalPoint);
    }

    private int calculateTotalPoint(int[] diceList) {
        int totalPoint = 0;
        for (int dicePoint : diceList) {
            totalPoint += dicePoint;
        }
        return totalPoint;
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


}
