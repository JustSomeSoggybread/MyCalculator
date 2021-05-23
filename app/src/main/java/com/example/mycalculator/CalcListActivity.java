package com.example.mycalculator;

//import classes
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class CalcListActivity extends SingleFragmentActivity{

    protected Fragment createFragment(){return new CalculatorHistoryFragment();}

}