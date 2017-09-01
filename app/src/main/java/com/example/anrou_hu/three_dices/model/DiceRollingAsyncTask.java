package com.example.anrou_hu.three_dices.model;

import android.os.AsyncTask;

import com.example.anrou_hu.three_dices.db.DiceRollingRepository;
import com.example.anrou_hu.three_dices.db.entity.DiceRollingResult;

import java.util.Random;

/**
 * @author anrou_hu
 */

public class DiceRollingAsyncTask extends AsyncTask<Integer, Void, int[]> {

    AsyncTaskCallback mCallback;
    DiceRollingRepository mDiceRollingRepo;

    private int[] mDicePoints;


    public DiceRollingAsyncTask(AsyncTaskCallback callback, DiceRollingRepository diceRollingRepo, int diceCount) {
        mCallback = callback;
        mDiceRollingRepo = diceRollingRepo;
        mDicePoints = new int[diceCount];
    }


    public void rollDices() {
        Random random = new Random();

        for (int i = 0; i < mDicePoints.length; i++) {
            mDicePoints[i] = random.nextInt(5) + 1;
        }
    }

    @Override
    protected int[] doInBackground(Integer... params) {
        rollDices();
        saveResult();
        return mDicePoints;
    }

    private void saveResult() {
        int totalPoints = calculateTotalPoint();
        DiceRollingResult result = generateResult(totalPoints);
        mDiceRollingRepo.saveResult(result);
    }

    private int calculateTotalPoint() {
        int sum = 0;
        for (int value : mDicePoints) {
            sum += value;
        }
        return sum;
    }

    private DiceRollingResult generateResult(int totalPoints) {
        DiceRollingResult result = new DiceRollingResult();
        result.setTotalPoint(totalPoints);
        result.setFrequency(1);
        result.setAmountOfDice(mDicePoints.length);

        return result;
    }

    @Override
    protected void onPostExecute(int[] dicePoints) {
        mCallback.processFinish(dicePoints);
    }
}
