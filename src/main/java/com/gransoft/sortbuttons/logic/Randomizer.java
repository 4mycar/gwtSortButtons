package com.gransoft.sortbuttons.logic;

import java.util.Random;

public class Randomizer {
    private final Random random = new Random();
    private final int RANGEMIN = 1;
    private final int RANGEMAX = 1000;

    public int[] generateArrayInt(int length) {
        int lessThan30Count = 0;
        int[] arr = new int[length];
        int randomNumber;
        for (int i = 0; i < length; i++) {
            randomNumber = getRandomInt(RANGEMIN, RANGEMAX);
            if (randomNumber <= 30) {
                lessThan30Count++;
            }
            arr[i] = randomNumber;
        }
        if (lessThan30Count == 0) {
            arr[getRandomInt(0, length - 1)] = getRandomInt(0, 30);
        }
        return arr;
    }

    private int getRandomInt(int rangeMin, int rangeMax) {
        return random.nextInt(rangeMax - rangeMin + 1) + rangeMin;
    }
}
