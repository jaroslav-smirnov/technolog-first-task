package study.optimal_drive.ru.firstcalc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CalculatorActivity extends AppCompatActivity implements SensorEventListener {

    private static final String OWN_TAG = "TEST_TAG";
    private EditText operandOneEditText;
    private EditText operandTwoEditText;
    private Button plusButton;
    private SensorManager mSensorManager;

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
    private Sensor accelerom;
    private View.OnHoverListener listener = new View.OnHoverListener() {
        @Override
        public boolean onHover(View v, MotionEvent event) {
            
            return false;
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
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.d(OWN_TAG, "Start list of sensors");
        for (Sensor deviceSensor : deviceSensors) {
            Log.d(OWN_TAG, deviceSensor.getName());
        }

        accelerom = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        plusButton.setOnHoverListener(listener);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        Log.d(OWN_TAG, String.format("%f;%f;%f",values[0],values[1],values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(OWN_TAG, "Accuracy changed");
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, accelerom, SensorManager.SENSOR_DELAY_FASTEST);
        //TODO: Set appropriate delay
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
