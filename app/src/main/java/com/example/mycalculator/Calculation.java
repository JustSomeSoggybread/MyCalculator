package com.example.mycalculator;

import java.util.Date;
import java.util.UUID;

public class Calculation {

    public UUID calcID;
    private Date mDate;

    private double mFirst;
    private String mMod;
    private double mSecond;
    private double mFinal;

    public Calculation (double First, String mod, double Second, double Final){
        mFirst = First;
        mSecond = Second;
        mFinal = Final;
        mMod = mod;

        calcID = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getCalcID(){
        return calcID;
    }

    public Date getDate(){
        return mDate;
    }

    public String getCalc(){
        return (mFirst + mMod + mSecond + "=" + mFinal);
    }

    public double getTotal(){ return (mFinal);}
}
