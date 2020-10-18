package com.gransoft.sortbuttons.logic;

import com.gransoft.sortbuttons.pojo.SortStep;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    private List<SortStep> sortStepList = new ArrayList<>();

    public void sort(int[] arr, boolean isDesc) {
        sortStepList.clear();
        sort(arr, 0, arr.length - 1, isDesc);
    }

    private void sort(int[] arr, int begin, int end, boolean isDesc) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, isDesc);
            sort(arr, begin, partitionIndex - 1, isDesc);
            sort(arr, partitionIndex + 1, end, isDesc);
        }
    }

    private int partition(int[] arr, int begin, int end, boolean isDesc) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            sortStepList.add(new SortStep(i + 1, j, end, begin, end, false));
            if (!isDesc && arr[j] <= pivot) {
                i++;
                sortStepList.add(new SortStep(i, j, end, begin, end, true));
                swap(arr, i, j);
            } else if (isDesc && arr[j] >= pivot) {
                i++;
                sortStepList.add(new SortStep(i, j, end, begin, end, true));
                swap(arr, i, j);
            }
        }
        sortStepList.add(new SortStep(i + 1, end, end, begin, end, true));
        swap(arr, i + 1, end);

        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
    }

    public List<SortStep> getSortStepList() {
        return sortStepList;
    }
}
