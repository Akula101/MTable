package com.maxim.mtable.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise {

    private int currentIndex = 0;
    private ExecTask currentTask;
    private List<ExecTask> tasks = new ArrayList<>();

    public static Exercise generateExercise(int num, boolean isRandom) {

        return new Exercise(num, isRandom);
    }

    protected Exercise(int num, boolean isRandom) {

        for(int i = 0; i < 10; i++) {
            tasks.add(new ExecTask(num, i+1, 0));
        }
        if(isRandom)
            randomize();

        currentTask = tasks.get(0);

    }

    protected void randomize() {
        Random random = new Random();
        List<ExecTask> newList = new ArrayList<>();
        for(int i = 0 ; i < 10; i++) {
            int next = random.nextInt(10 -i);
            if(next >= tasks.size())
                next = tasks.size()-1;

            newList.add(tasks.remove(next));
        }
        tasks = newList;

    }

    public void getNext() {
        if(currentIndex == tasks.size() -1)
            currentIndex = 0;
        else
            currentIndex++;
        currentTask = tasks.get(currentIndex);
    }

    public ExecTask getCurrent() {
        return  currentTask;
    }
}
