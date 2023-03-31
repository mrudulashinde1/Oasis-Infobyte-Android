package com.ab.simplecalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, solution;
    MaterialButton btnC,btnBracketOpen,btnBracketClose;
    MaterialButton btnDivide,btnMultiply,btnAdd,btnSubtract,btnEqual;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    MaterialButton btnAc,btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.txt_result);
        solution =findViewById(R.id.txt_solution);
        assignId(btnC,R.id.btn_c);
        assignId(btnBracketClose,R.id.btn_close_bracket);
        assignId(btnBracketOpen,R.id.btn_open_bracket);
        assignId(btnDivide,R.id.btn_devide);
        assignId(btnMultiply,R.id.btn_multiply);
        assignId(btnAdd,R.id.btn_add);
        assignId(btnSubtract,R.id.btn_subtract);
        assignId(btnEqual,R.id.btn_equal);
        assignId(btn0,R.id.btn_zero);
        assignId(btn1,R.id.btn_one);
        assignId(btn2,R.id.btn_two);
        assignId(btn3,R.id.btn_three);
        assignId(btn4,R.id.btn_four);
        assignId(btn5,R.id.btn_five);
        assignId(btn6,R.id.btn_six);
        assignId(btn7,R.id.btn_seven);
        assignId(btn8,R.id.btn_eight);
        assignId(btn9,R.id.btn_nine);
        assignId(btnAc,R.id.btn_ac);
        assignId(btnDot,R.id.btn_dot);
    }
    void assignId(MaterialButton btn,int id){
            btn = findViewById(id);
            btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if (buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if (finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
           }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}