package com.example.mycalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class CalculatorFragment extends Fragment {


    //variables
    private Button mAddButton;
    private Button mSubtractButton;
    private Button mMultiplyButton;
    private Button mDivideButton;
    private Button mHistoryButton;
    private static CalcList calcList;

    private double total=0;
    int modifyThis = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_calculator, container, false);

        //finds main sum display
        TextView sumTotal = (TextView) v.findViewById(R.id.sumText);

        EditText mEditText = (EditText) v.findViewById(R.id.editText);

        //code for addition button
        mAddButton = (Button) v.findViewById(R.id.addition_button);
        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finds input text
                String temporary;
                temporary = mEditText.getText().toString();
                Double saveTotal = total;
                //if input text is valid, add it to the sum
                if (!"".equals(temporary)){
                    modifyThis=Integer.parseInt(temporary);
                    total += modifyThis;

                    Calculation newCalc = new Calculation(saveTotal, "+", modifyThis, total);

                    calcList = CalcList.get(getActivity());
                    calcList.addCalc(newCalc);
                }
                //else, return error
                else{
                    displayToast("Error: Please enter a number", 0, 0);
                }

                //clean up
                cleanUp(total, sumTotal);
            }
        });

        //code for subtraction button
        mSubtractButton = (Button) v.findViewById(R.id.subtraction_button);
        mSubtractButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finds input text
                String temporary;
                temporary = mEditText.getText().toString();;
                Double saveTotal = total;
                //if input text is valid, add it to the sum
                if (!"".equals(temporary)){
                    modifyThis=Integer.parseInt(temporary);
                    total -= modifyThis;

                    Calculation newCalc = new Calculation(saveTotal, "-", modifyThis, total);

                    calcList = CalcList.get(getActivity());
                    calcList.addCalc(newCalc);
                }
                //else, return error
                else{
                    displayToast("Error: Please enter a number", 0, 0);
                }

                //clean up
                cleanUp(total, sumTotal);
            }
        });

        //code for division button
        mDivideButton = (Button) v.findViewById(R.id.division_button);
        mDivideButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //finds input text
                String temporary;
                temporary = mEditText.getText().toString();
                Double saveTotal = total;
                //if input text is valid...
                if (!"".equals(temporary)) {
                    modifyThis = Integer.parseInt(temporary);
                    // and also not 0 (divide by zero error)
                    if (modifyThis == 0) {
                        displayToast("Error: Cannot divide by 0", 0, 0);
                    }
                    //divide total by input
                    else {
                        total /= modifyThis;

                        Calculation newCalc = new Calculation(saveTotal, "/", modifyThis, total);

                        calcList = CalcList.get(getActivity());
                        calcList.addCalc(newCalc);
                    }
                }

                //otherwise, return error
                else{
                    displayToast("Error: Please enter a number", 0, 0);
                }

                //clean up
                cleanUp(total, sumTotal);
            }

        });

        //code for multiplication button
        mMultiplyButton = (Button) v.findViewById(R.id.multiplication_button);
        mMultiplyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //find input
                String temporary;
                temporary = mEditText.getText().toString();
                Double saveTotal = total;
                //if input is valid. multiply sum by it
                if (!"".equals(temporary)) {
                    modifyThis = Integer.parseInt(temporary);
                    total *= modifyThis;

                    Calculation newCalc = new Calculation(saveTotal, "x", modifyThis, total);

                    calcList = CalcList.get(getActivity());
                    calcList.addCalc(newCalc);
                }
                //else, return error
                else{
                    displayToast("Error: Please enter a number", 0, 0);
                }

                //clean up
                cleanUp(total, sumTotal);
            }

        });


        mHistoryButton = (Button) v.findViewById(R.id.view_history_button);
        mHistoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = CalculatorHistoryFragment.newIntent(v.getContext());
                startActivity(intent);
            }
        });


    return v;
    }

    //standardizing displayToast
    private void displayToast(String message, int x, int y){
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, x, y);
        toast.show();
    }

    //clean up steps
    private void cleanUp(double total, TextView sumTotal){
        //print total to terminal
        System.out.println(total);
        //sets the string of the total
        sumTotal.setText(String.valueOf(total));
        //sets modifythis to 0 (although this is ultimately unnecessary?)
        int modifyThis = 0;
        //sets edittext to be blank
        EditText editText = (EditText) getView().findViewById(R.id.editText);
        editText.setText("");
        calcList = CalcList.get(getActivity());
        calcList.printCalcs();
    }

    public static List<Calculation> getCalcList(){
        return calcList.getCalcs();
    }


}

