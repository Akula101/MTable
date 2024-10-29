package com.maxim.mtable.calc;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    public static List<ExecTask> generateExecTasks() {
        List<ExecTask> tasks = new ArrayList<>();

        for(int i = 0; i < 10 ; i++) {
            tasks.add(new ExecTask(7, i+1, 0));
        }

        return tasks;

    }
}
