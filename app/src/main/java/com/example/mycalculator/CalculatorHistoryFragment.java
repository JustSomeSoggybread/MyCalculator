package com.example.mycalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;
import java.util.UUID;

public class CalculatorHistoryFragment extends Fragment{

    private RecyclerView mCalcHisView;
    private CalcAdapter mAdapter;
    private CalcList mCalcList;
    private static final String EXTRA_CALC_ID = "com.appdev.mycalculator.calc_id";

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, CalcListActivity.class);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        mCalcList = CalcList.get(getActivity());
        System.out.println("HistoryFragment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.calculation_history, container, false);

        System.out.println("onCreateView created");

        mCalcHisView = (RecyclerView) view.findViewById(R.id.calculation_recycler_view);
        mCalcHisView.setLayoutManager((new LinearLayoutManager(getActivity())));
        updateUI();
        return view;
    }

    private class CalcHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Calculation mCalc;
        private TextView mCalcTitleText;
        private TextView mCalcDateText;

        public CalcHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.calc_item_list, parent, false));
            itemView.setOnClickListener(this);
            mCalcTitleText = (TextView) itemView.findViewById(R.id.calc_title);
            mCalcDateText = (TextView) itemView.findViewById(R.id.calc_date);
        }

        public void bind(Calculation calc){
            mCalc = calc;
            mCalcTitleText.setText(mCalc.getCalc());
            mCalcDateText.setText(DateFormat.getDateInstance().format(mCalc.getDate()));
        }
        public void onClick(View view){
        }
    }

    private class CalcAdapter extends RecyclerView.Adapter<CalcHolder>{
        private List<Calculation> mCalcs;

        public CalcAdapter(List<Calculation> calcs){
            mCalcs = calcs;
        }

        @Override
        public CalcHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater =     LayoutInflater.from(getActivity());

            return new CalcHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CalcHolder holder, int position){
            Calculation calc = mCalcs.get(position);
            holder.bind(calc);
        }

        @Override
        public int getItemCount(){
            return mCalcs.size();
        }
    }

    private void updateUI(){

        List<Calculation> calcs = getCalculations();

        if (mAdapter == null){
            //PROBLEM: FIGURE OUT HOW TO COMMUNICATE WITH MAINACTIVITY SCACLIST AND THIS SCALCLIST BECAUSE THE TWO ARE NOT SHARED APPARENTLY.
            mAdapter = new CalcAdapter(calcs);
            mCalcHisView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }


    }

    private List<Calculation> getCalculations(){
        CalcList calcList = CalcList.get(getActivity());
        return calcList.getCalcs();
    }
}
