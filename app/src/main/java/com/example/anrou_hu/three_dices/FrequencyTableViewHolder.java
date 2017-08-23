package com.example.anrou_hu.three_dices;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @author anrou_hu
 */

public class FrequencyTableViewHolder extends RecyclerView.ViewHolder {

    private TextView mDicePoint;
    private TextView mFrequency;

    public FrequencyTableViewHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mDicePoint = (TextView) itemView.findViewById(R.id.dicePoint);
        mFrequency = (TextView) itemView.findViewById(R.id.frequency);
    }

    public void bindView(DiceRollingResult result) {
        if (result == null) return;
        String dicePoint = String.valueOf(result.getTotalPoint());
        mDicePoint.setText(dicePoint);
        String frequency = String.valueOf(result.getFrequency());
        mFrequency.setText(frequency);
    }
}
