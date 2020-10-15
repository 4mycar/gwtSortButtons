package com.gransoft.sortbuttons.logic;


public class QuickSort {

    public void sort(int[] arr, int begin, int end, boolean isDesc) {
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
            if (!isDesc && arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            } else if (isDesc && arr[j] >= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);

        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
    }

}
