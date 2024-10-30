package com.maxim.mtable;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import com.maxim.mtable.calc.ExecTask;
import com.maxim.mtable.calc.Exercise;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private Exercise currentExercise = null;
    private boolean isRandom = false;

    private int currentNumber = 2;

    private Spinner spinner;
    private static final String[] num_items = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(int i = 0; i < 9;i++) {
            num_items[i] = String.valueOf(i+2);
        }



        setContentView(R.layout.exectest);

        EditText resultField = findViewById(R.id.mres);

        resultField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ExecTask currentTask = currentExercise.getCurrent();
                //TextView resText = findViewById(R.id.result);
                ImageView resImg = findViewById(R.id.resImage);
                currentTask.inputMissedValue(Integer.parseInt(textView.getText().toString()));

                if(currentTask.check()) {
//                    resText.setText("CORRECT");
//                    resText.setTextColor(Color.GREEN);
                    resImg.setImageResource(R.mipmap.chek);
                    resImg.setVisibility(View.VISIBLE);
                    textView.clearFocus();
                }
                else {
                    resImg.setImageResource(R.mipmap.cross);
                    resImg.setVisibility(View.VISIBLE);

                }
                currentTask.rollbackMissedValue();
                return true;
            }
        });

        Spinner spinner =findViewById(R.id.number_choice);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,num_items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentNumber = position + 2;
                resetExercise();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Switch switchRandom = findViewById(R.id.random_mode);
        switchRandom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRandom = isChecked;
                resetExercise();
            }
        });

        resetExercise();

    }

    public void nextTask(View view) {

        setNextTask();
    }

    private void setNextTask() {
        currentExercise.getNext();
        setCurrentTask();
    }

    private void resetExercise() {
        currentExercise = Exercise.generateExercise(currentNumber, isRandom);
        setCurrentTask();;
    }

    private void setCurrentTask() {
        ExecTask currentTask = currentExercise.getCurrent();
        TextView m1 = findViewById(R.id.m1);
        TextView m2 = findViewById(R.id.m2);
        TextView m3 = findViewById(R.id.mres);
        m1.setText(String.valueOf(currentTask.getM1()));
        m2.setText(String.valueOf(currentTask.getM2()));
        m3.setText("");
        ImageView resImg = findViewById(R.id.resImage);
        resImg.setVisibility(View.INVISIBLE);



    }


}