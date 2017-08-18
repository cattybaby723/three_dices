package com.example.anrou_hu.three_dices;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends LifecycleActivity implements View.OnClickListener {

    private TextView mFirstDice;
    private TextView mSecondDice;
    private TextView mThirdDice;
    private TextView mPoint;
    private Button mRoll;
    private RecyclerView mResult;

    private MainViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initViewModel();
        initObserver();
    }

    private void initViews() {
        mFirstDice = (TextView) findViewById(R.id.firstDice);
        mSecondDice = (TextView) findViewById(R.id.secondDice);
        mThirdDice = (TextView) findViewById(R.id.thirdDice);
        mPoint = (TextView) findViewById(R.id.point);
        mRoll = (Button) findViewById(R.id.roll);
        mResult = (RecyclerView) findViewById(R.id.frequencyTable);
        mRoll.setOnClickListener(this);
    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void initObserver() {
        Lifecycle lifecycle = getLifecycle();
        lifecycle.addObserver(mViewModel);

        mViewModel.getDicePoints().observe(this, new Observer<int[]>() {
            @Override
            public void onChanged(@Nullable int[] ints) {
                updateDicePoints(ints);
            }
        });
    }

    private void updateDicePoints(int[] dicePoints) {
        int dice1 = dicePoints[0];
        int dice2 = dicePoints[1];
        int dice3 = dicePoints[2];
        int totalPoints = dice1 + dice2 + dice3; //do it in view model or Live data
        mFirstDice.setText(String.valueOf(dice1));
        mSecondDice.setText(String.valueOf(dice2));
        mThirdDice.setText(String.valueOf(dice3));
        mPoint.setText(String.valueOf(totalPoints));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roll:
                rollDices();
                break;
        }
    }

    private void rollDices() {
        mViewModel.rollDices();
    }
}
