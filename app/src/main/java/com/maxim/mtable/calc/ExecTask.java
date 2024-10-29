package com.maxim.mtable.calc;

public class ExecTask {
    public void setM1(int m1) {
        this.m1 = m1;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public void setResult(int mRes) {
        this.mRes = mRes;
    }

    public void inputMissedValue(int value) {
        if( m1 == 0) {
            m1 = value;
            index = 0;
        }
        if (m2 == 0) {
            index = 1;
            m2 = value;
        }

        if (mRes == 0) {
            mRes = value;
            index = 2;
        }
    }

    public void rollbackMissedValue() {
        switch (index) {
            case 0:
                m1 = 0;
                break;
            case 1:
                m2 = 0;
                break;
            case 2:
                mRes = 0;
                break;

        }
    }



    public int getM1() {
        return m1;
    }

    public int getM2() {
        return m2;
    }

    public int getmRes() {
        return mRes;
    }

    protected int m1;
    protected int m2;
    protected int mRes;
    protected int index = -1;


    public ExecTask(int m1, int m2, int result) {
        this.m1 = m1;
        this.m2 = m2;
        this.mRes = result;

    }

    public boolean check() {
        return m1*m2 == mRes;
    }
}