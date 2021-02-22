package com.khruschevks.enrolltask;

import java.util.Arrays;
import java.util.Random;

public class EnrollTask {
    public static void main(String[] args) {
        int[][] result = getSortedArraysWithRandomLengths(100);

        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    public static int[][] getSortedArraysWithRandomLengths(int arraysAmount) {
        if (arraysAmount < 0) {
            throw new IllegalArgumentException("Arrays amount could`nt be less than 0. Now it is " + arraysAmount + ".");
        }

        if (arraysAmount == 0) {
            return new int[arraysAmount][];
        }

        final int customizedMaxArrayLength = 6;
        // Если значение будет меньше количества массивов (arraysAmount),
        // максимально возможной длинной массива будет их кол-во - 1.

        final int maxArrayLength = Math.max(arraysAmount, customizedMaxArrayLength);
        // Сделано из соображений надежности.
        // Такой код нельзя сломать, случайно изменив customizedMaxArrayLength на недопустимое значение

        int[][] resultArrays = new int[arraysAmount][];
        int uniqueArrayLength;

        for (int i = 0; i < arraysAmount; i++) {
            uniqueArrayLength = (int) (Math.random() * maxArrayLength);// интервал [0; maxArrayLength - 1]. Может работать с любым интервалом

            for (int j = 0; j < i; ) {
                if (resultArrays[j].length == uniqueArrayLength) {
                    j = 0;
                    uniqueArrayLength = (int) (Math.random() * maxArrayLength);
                } else {
                    j++;
                }
            }

            resultArrays[i] = new int[uniqueArrayLength];

            if ((i + 1) % 2 == 0) { // сделано из размышлений, что порядковый номер != индекс массива
                fillArrayAndSortAscending(resultArrays[i]);
            } else {
                fillArrayAndSortDescending(resultArrays[i]);
            }
        }

        return resultArrays;
    }

    public static void fillArrayAndSortAscending(int[] array) {
        int currentArrayNumber;
        int j;
        int temp;
        final int randomizerBound = 50;

        Random randomizer = new Random();

        for (int i = 0; i < array.length; i++) {
            currentArrayNumber = randomizer.nextInt(randomizerBound);

            j = array.length - i - 1;
            array[j] = currentArrayNumber;

            if (i > 0) {
                while (j < array.length - 1 && array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    j++;
                }
            }
        }
    }

    public static void fillArrayAndSortDescending(int[] array) {
        int currentArrayNumber;
        int temp;
        int j;
        final int randomizerBound = 50;

        Random randomizer = new Random();

        for (int i = 0; i < array.length; i++) {
            currentArrayNumber = randomizer.nextInt(randomizerBound);

            j = array.length - i - 1;
            array[j] = currentArrayNumber;

            if (i > 0) {
                while (j < array.length - 1 && array[j] < array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    j++;
                }
            }
        }
    }
}
