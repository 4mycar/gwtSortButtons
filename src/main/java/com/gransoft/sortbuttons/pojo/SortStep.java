package com.gransoft.sortbuttons.pojo;

public class SortStep {
    private int i;
    private int j;
    private int pivot;
    private int low;
    private int high;
    private boolean isSwap;

    public SortStep(int i, int j, int pivot, int low, int high, boolean isSwap) {
        this.i = i;
        this.j = j;
        this.pivot = pivot;
        this.low = low;
        this.high = high;
        this.isSwap = isSwap;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getPivot() {
        return pivot;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public boolean isSwap() {
        return isSwap;
    }

    @Override
    public String toString() {
        return "SortStep{" +
                "i=" + i +
                ", j=" + j +
                ", pivot=" + pivot +
                ", low=" + low +
                ", high=" + high +
                ", isSwap=" + isSwap +
                '}';
    }
}
