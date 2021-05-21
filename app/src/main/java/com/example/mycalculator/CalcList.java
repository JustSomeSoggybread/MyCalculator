package com.example.mycalculator;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CalcList {

    private static CalcList sCalcList;

    private final List<Calculation> mCalcs;

    private CalcList(Context context) {
        mCalcs = new ArrayList<>();
    }

    public static CalcList get(Context context){
        if (sCalcList == null){
            sCalcList = new CalcList(context);

        }
        return sCalcList;
    }

    public List<Calculation> getCalc(){
        return mCalcs;
    }

    public void addCalc(Calculation calc){
        mCalcs.add(calc);
    }
}
