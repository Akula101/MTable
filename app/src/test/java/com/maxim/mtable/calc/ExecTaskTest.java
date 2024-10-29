package com.maxim.mtable.calc;

import junit.framework.TestCase;

public class ExecTaskTest extends TestCase {

    public void testCheck() {

        ExecTask task = new ExecTask(2,2, 4);
        assertTrue(task.check());

        ExecTask task1 = new ExecTask(2,2, 5);
        assertFalse(task1.check());

    }
}