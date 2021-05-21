package com.example.mycalculator;

//import classes
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity{

    protected Fragment createFragment(){return new CalculatorFragment();}

}