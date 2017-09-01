package com.example.anrou_hu.three_dices.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anrou_hu.three_dices.R;
import com.example.anrou_hu.three_dices.db.entity.DiceRollingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anrou_hu
 */

public class FrequencyTableAdapter extends RecyclerView.Adapter {

    private List<DiceRollingResult> resultList = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem_result, parent, false);

        return new FrequencyTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FrequencyTableViewHolder) holder).bindView(resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public void setResultList(List<DiceRollingResult> resultList) {
        this.resultList = resultList;
    }
}
