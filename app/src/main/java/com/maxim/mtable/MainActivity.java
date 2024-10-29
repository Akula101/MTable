package com.maxim.mtable;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import com.maxim.mtable.calc.ExecTask;
import com.maxim.mtable.calc.Exercise;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private List<ExecTask> taskList;
    private  int currentTaskIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        taskList = Exercise.generateExecTasks();

        setContentView(R.layout.exectest);

        EditText resultField = findViewById(R.id.mres);

        resultField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ExecTask currentTask = taskList.get(currentTaskIdx);
                TextView resText = findViewById(R.id.result);
                currentTask.inputMissedValue(Integer.parseInt(textView.getText().toString()));

                if(currentTask.check()) {
                    resText.setText("CORRECT");
                    resText.setTextColor(Color.GREEN);
                }
                else {
                    resText.setText("WRONG");
                    resText.setTextColor(Color.RED);

                }
                currentTask.rollbackMissedValue();
                return true;
            }
        });

        setCurrentTask();

    }

    public void nextTask(View view) {

        setNextTask();
    }

    private void setNextTask() {
        currentTaskIdx++;
        setCurrentTask();
    }

    private void setCurrentTask() {
        ExecTask currentTask = taskList.get(currentTaskIdx);
        TextView m1 = findViewById(R.id.m1);
        TextView m2 = findViewById(R.id.m2);
        TextView m3 = findViewById(R.id.mres);
        m1.setText(String.valueOf(currentTask.getM1()));
        m2.setText(String.valueOf(currentTask.getM2()));
        m3.setText("");
        TextView resText = findViewById(R.id.result);
        resText.setText("");

    }


}