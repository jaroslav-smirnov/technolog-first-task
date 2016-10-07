package study.optimal_drive.ru.firstcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    private EditText operandOneEditText;
    private EditText operandTwoEditText;
    private Button plusButton;
    private TextView resultView;

    private View.OnClickListener plusButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String firstNumberString = operandOneEditText.getText().toString();
            String secondNumberString = operandTwoEditText.getText().toString();
            if (firstNumberString.isEmpty() || secondNumberString.isEmpty()) {
                Toast.makeText(CalculatorActivity.this,"Введите число", Toast.LENGTH_SHORT).show();
            } else {
                calculateResultAndSet(firstNumberString, secondNumberString);
            }
        }

        private void calculateResultAndSet(String firstNumberString, String secondNumberString) {
            double firstNumber = Double.parseDouble(firstNumberString);
            double secondNumber = Double.parseDouble(secondNumberString);
            double sum = firstNumber + secondNumber;
            resultView.setText("" + sum);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        operandOneEditText = (EditText) findViewById(R.id.editTextOperandOne);
        operandTwoEditText = (EditText) findViewById(R.id.editTextOperandTwo);
        plusButton = (Button) findViewById(R.id.buttonPlus);
        resultView = (TextView) findViewById(R.id.textViewResult);

        plusButton.setOnClickListener(plusButtonClick);
    }
}
