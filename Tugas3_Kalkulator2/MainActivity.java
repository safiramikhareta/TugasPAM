package com.example.kalkulator2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEquals;
    MaterialButton button0,button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonC;

    String inputData = "";
    String operator = "";
    double firstValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonEquals, R.id.button_equals);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonC, R.id.button_c);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if(buttonText.equals("C")){
           inputData = "";
           solutionTv.setText("0");
           resultTv.setText("0");
           firstValue = 0;
           operator = "";
           return;
        }

        if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
            if (!inputData.isEmpty()) {
                firstValue = Double.parseDouble(inputData);
                operator = buttonText;
                inputData = "";
                solutionTv.setText(firstValue + " " + operator);
            }
            return;
        }

        if(buttonText.equals("=")){
            if (!inputData.isEmpty() && !operator.isEmpty()) {
                double secondValue = Double.parseDouble(inputData);
                double result = calculate(firstValue, secondValue, operator);
                DecimalFormat df = new DecimalFormat("0.######");
                resultTv.setText(df.format(result));
                solutionTv.setText("");
                inputData = "";
                operator = "";
            }
            return;
        }
        inputData += buttonText;
        solutionTv.setText(inputData);
    }

    double calculate(double firstValue, double secondValue, String operator) {
        switch (operator) {
            case "+":
                return firstValue + secondValue;
            case "-":
                return firstValue - secondValue;
            case "*":
                return firstValue * secondValue;
            case "/":
                if (secondValue != 0) {
                    return firstValue / secondValue;
                } else {
                    resultTv.setText("Error");
                    return 0;
                }
            default:
                return 0;
        }
    }

}