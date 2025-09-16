package semana05.ejercicios;

import java.util.Arrays;

/**
 * Clase que proporciona métodos para ordenar arreglos utilizando el algoritmo MergeSort.
 * Este algoritmo sigue un paradigma 'divide y vencerás', con complejidad O(n log n).
 */
public class MergeSort {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Merge Sort.
     *
     * @param arr el arreglo que se desea ordenar
     * @return un nuevo arreglo ordenado de manera ascendente
     */
    public static int[] sort(int[] arr) {
        if (arr.length <= 1) {
            return Arrays.copyOf(arr, arr.length);
        }

        int middle = arr.length / 2;
        int[] left = sort(Arrays.copyOfRange(arr, 0, middle));
        int[] right = sort(Arrays.copyOfRange(arr, middle, arr.length));

        return merge(left, right);
    }

    /**
     * Combina dos arreglos ordenados en un nuevo arreglo ordenado.
     *
     * @param left primer arreglo ordenado
     * @param right segundo arreglo ordenado
     * @return un nuevo arreglo que contiene todos los elementos de ambos arreglos ordenados
     */
    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                merged[mergedIndex++] = left[leftIndex++];
            } else {
                merged[mergedIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            merged[mergedIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            merged[mergedIndex++] = right[rightIndex++];
        }

        return merged;
    }
}