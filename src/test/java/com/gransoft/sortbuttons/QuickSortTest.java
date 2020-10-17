package com.gransoft.sortbuttons;

import com.gransoft.sortbuttons.logic.QuickSort;
import com.gransoft.sortbuttons.view.SortScreen;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {
    private int[] arr = {5, 9, 4, 6, 5, 3};
    private int[] ascSortedArr = {3, 4, 5, 5, 6, 9};
    private int[] descSortedArr = {9, 6, 5, 5, 4, 3};
    private QuickSort quickSort = new QuickSort();

    @Test
    public void quickSortAsc() {
        quickSort.sort(arr, false);
        quickSort.getSortStepList().stream().forEach(System.out::println);
        assertArrayEquals(ascSortedArr, arr);
    }
    @Test
    public void quickSortDesc() {
        quickSort.sort(arr, true);
        assertArrayEquals(descSortedArr, arr);
    }

}