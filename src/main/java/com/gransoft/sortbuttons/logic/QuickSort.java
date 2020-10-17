package com.gransoft.sortbuttons.logic;


import com.gransoft.sortbuttons.pojo.SortStep;
import com.gransoft.sortbuttons.view.SortScreen;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
//    SortScreen sortScreen;
    private List<SortStep> sortStepList = new ArrayList<>();
//    public QuickSort (SortScreen sortScreen){
//        this.sortScreen=sortScreen;
//    }


    public void sort(int[] arr, boolean isDesc) {
        sortStepList.clear();
        sort(arr, 0,arr.length-1,isDesc);
//        sortScreen.visualizeSwap(sortStepList);
    }

    private void sort(int[] arr, int begin, int end, boolean isDesc){
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
//                sortStepList.add(new SortStep(i,j,pivot,begin,end));
                swap(arr, i, j);
            } else if (isDesc && arr[j] >= pivot) {
                i++;
//                sortStepList.add(new SortStep(i,j,pivot,begin,end));
                swap(arr, i, j);
            }
        }
        sortStepList.add(new SortStep(i + 1,end,pivot,begin,end));
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
