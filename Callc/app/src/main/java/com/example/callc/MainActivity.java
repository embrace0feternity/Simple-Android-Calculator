package com.example.callc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import com.udojava.evalex.Expression;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView expressionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            int[] buttonsId = new int[]{
                    R.id.buttonClearAll, R.id.button7, R.id.button4, R.id.button1, R.id.button0,
                    R.id.buttonMultiply, R.id.button8, R.id.button5, R.id.button2, R.id.button00,
                    R.id.buttonDivide, R.id.button9, R.id.button6, R.id.button3, R.id.buttonPoint,
                    R.id.buttonBackspace, R.id.buttonMinus, R.id.buttonPlus, R.id.buttonEqual};

            Button[] buttons = new Button[buttonsId.length];

            expressionTextView = (TextView) findViewById(R.id.expressionTextView);
            for (int i = 0; i < buttonsId.length; i++) {
                buttons[i] = (Button) findViewById(buttonsId[i]);
                buttons[i].setOnClickListener(this);
            }
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int[] buttonsId = new int[]{
                    R.id.buttonOpenParenthesis, R.id.buttonLog, R.id.buttonLn, R.id.buttonAbs, R.id.buttoneX,
                    R.id.buttonCloseParenthesis, R.id.buttonxY, R.id.buttonX2, R.id.button1X, R.id.buttonE,
                    R.id.buttonSqrt, R.id.buttonSin, R.id.buttonCos, R.id.buttonTan, R.id.buttonPi,
                    R.id.buttonClearAll, R.id.button7, R.id.button4, R.id.button1, R.id.button0,
                    R.id.buttonMultiply, R.id.button8, R.id.button5, R.id.button2, R.id.button00,
                    R.id.buttonDivide, R.id.button9, R.id.button6, R.id.button3, R.id.buttonPoint,
                    R.id.buttonBackspace, R.id.buttonMinus, R.id.buttonPlus, R.id.buttonEqual };

            Button[] buttons = new Button[buttonsId.length];

            expressionTextView = (TextView) findViewById(R.id.expressionTextView);
            for (int i = 0; i < buttonsId.length; i++) {
                buttons[i] = (Button) findViewById(buttonsId[i]);
                buttons[i].setOnClickListener(this);
            }
        }

        if (savedInstanceState != null) {
            String temp = (String) savedInstanceState.getString(null, expressionTextView.getText().toString());
            expressionTextView.setText(temp);
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonOpenParenthesis: expressionTextView.append("("); break;
            case R.id.buttonLog: expressionTextView.append("LOG10("); break;
            case R.id.buttonLn: expressionTextView.append("LOG("); break;
            case R.id.buttonAbs: expressionTextView.append("ABS("); break;
            case R.id.buttoneX: expressionTextView.append("e^"); break;

            case R.id.buttonCloseParenthesis: expressionTextView.append(")"); break;
            case R.id.buttonxY: expressionTextView.append("^"); break;
            case R.id.buttonX2: expressionTextView.append("^2"); break;
            case R.id.button1X: expressionTextView.append("1/"); break;
            case R.id.buttonE: expressionTextView.append("e"); break;

            case R.id.buttonSqrt: expressionTextView.append("SQRT("); break;
            case R.id.buttonSin: expressionTextView.append("SIN("); break;
            case R.id.buttonCos: expressionTextView.append("COS("); break;
            case R.id.buttonTan: expressionTextView.append("TAN("); break;
            case R.id.buttonPi: expressionTextView.append("PI"); break;

            case R.id.buttonClearAll:  expressionTextView.setText(""); break;
            case R.id.button7:  expressionTextView.append("7"); break;
            case R.id.button4:  expressionTextView.append("4"); break;
            case R.id.button1:  expressionTextView.append("1"); break;
            case R.id.button0:  expressionTextView.append("0"); break;

            case R.id.buttonMultiply:  expressionTextView.append("*"); break;
            case R.id.button8:  expressionTextView.append("8"); break;
            case R.id.button5:  expressionTextView.append("5"); break;
            case R.id.button2:  expressionTextView.append("2"); break;
            case R.id.button00:  expressionTextView.append("00"); break;

            case R.id.buttonDivide:  expressionTextView.append("/"); break;
            case R.id.button9:  expressionTextView.append("9"); break;
            case R.id.button6:  expressionTextView.append("6"); break;
            case R.id.button3:  expressionTextView.append("3"); break;
            case R.id.buttonPoint:  expressionTextView.append("."); break;

            case R.id.buttonBackspace:
                String last = (String) expressionTextView.getText().toString();
                if (last.length() == 0) expressionTextView.setText("");
                else
                    expressionTextView.setText(last.substring(0, last.length() - 1));
                break;
            case R.id.buttonMinus:  expressionTextView.append("-"); break;
            case R.id.buttonPlus:  expressionTextView.append("+"); break;
            case R.id.buttonEqual:
                try {
                    Expression expression = new Expression(expressionTextView.getText().toString());
                    expression.setPrecision(3);
                    expressionTextView.setText(expression.eval().toPlainString());
                }
                catch (ArithmeticException e)
                {
                    expressionTextView.setText("Арифметическая ошибка");
                }
                catch (Expression.ExpressionException e)
                {
                    expressionTextView.setText("Ошибка выражения");
                }
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle SaveInstanceState){
        super.onSaveInstanceState(SaveInstanceState);

        SaveInstanceState.putString(null, expressionTextView.getText().toString());
    }

}