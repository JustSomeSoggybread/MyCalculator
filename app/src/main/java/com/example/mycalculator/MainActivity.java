package com.example.mycalculator;

//import classes
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables
    private Button mAddButton;
    private Button mSubtractButton;
    private Button mMultiplyButton;
    private Button mDivideButton;
    private double total=0;
    int modifyThis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //conventional onCreate stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finds main sum display
        TextView sumTotal = (TextView) findViewById(R.id.sumText);

        //code for addition button
        mAddButton = (Button) findViewById(R.id.addition_button);
        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finds input text
                EditText mEditText = findViewById(R.id.displayText);
                String temporary = mEditText.getText().toString();
                //if input text is valid, add it to the sum
                if (!"".equals(temporary)){
                    modifyThis=Integer.parseInt(temporary);
                    total += modifyThis;
                }
                //otherwise, return error
                else{
                    displayToast("Error: Please enter a number", 0, 0);
                }
                //clean up
                cleanUp(total, sumTotal);
            }
        });
        //code for subtraction button
        mSubtractButton = (Button) findViewById(R.id.subtraction_button);
        mSubtractButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finds input text
                EditText mEditText = findViewById(R.id.displayText);
                String temporary = mEditText.getText().toString();
                //if input text is valid, add it to the sum
                if (!"".equals(temporary)){
                    modifyThis=Integer.parseInt(temporary);
                    total -= modifyThis;
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
        mDivideButton = (Button) findViewById(R.id.division_button);
        mDivideButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //finds input text
                EditText mEditText = findViewById(R.id.displayText);
                String temporary = mEditText.getText().toString();
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
        mMultiplyButton = (Button) findViewById(R.id.multiplication_button);
        mMultiplyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //find input
                EditText mEditText = findViewById(R.id.displayText);
                String temporary = mEditText.getText().toString();
                //if input is valid. multiply sum by it
                if (!"".equals(temporary)) {
                    modifyThis = Integer.parseInt(temporary);
                    total *= modifyThis;
                }
                //else, return error
                else{
                    displayToast("Error: Please enter a number", 0, 0);
                }
                //clean up
                cleanUp(total, sumTotal);
            }

        });
    }

    //standardizing displayToast
    private void displayToast(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
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
        EditText editText = (EditText) findViewById(R.id.displayText);
        editText.setText("");
    }
}