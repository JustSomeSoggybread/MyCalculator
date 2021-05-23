package com.example.mycalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

public class CalculatorHistoryFragment extends Fragment{

    private RecyclerView mCalcHisView;
    private CalcAdapter mAdapter;
    private static final String EXTRA_CALC_ID = "com.appdev.mycalculator.calc_id";

    public static Intent newIntent(Context packageContext, UUID calcID){
        Intent intent = new Intent(packageContext, CalculatorHistoryFragment.class);
        intent.putExtra(EXTRA_CALC_ID, calcID);
        return intent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.calculation_history, container, false);

        mCalcHisView = (RecyclerView) view.findViewById(R.id.calculation_recycler_view);
        mCalcHisView.setLayoutManager((new LinearLayoutManager(getActivity())));
        return view;
    }

    private class CalcHolder extends RecyclerView.ViewHolder{
        public CalcHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.calculation_history, parent, false));
        }
    }

    private class CalcAdapter extends RecyclerView.Adapter<CalcHolder>{
        private List<Calculation> mCalcs;

        public CalcAdapter(List<Calculation> calcs){
            mCalcs = calcs;
        }

        @Override
        public CalcHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CalcHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CalcHolder holder, int position){

        }

        @Override
        public int getItemCount(){
            return mCalcs.size();
        }
    }

    private void updateUI(){
        CalcList calcList = CalcList.get(getActivity());
        List<Calculation> calcs = calcList.getCalc();

        mAdapter = new CalcAdapter(calcs);
        mCalcHisView.setAdapter(mAdapter);

    }
}
