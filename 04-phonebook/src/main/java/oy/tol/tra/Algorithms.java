package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {

    /**
     * Sorts an array using the provided comparator.
     * @param array The array to be sorted.
     * @param comparator The comparator used to determine the order of the elements.
     * @param <T> The type of elements in the array.
     */
    public static <T> void sortWithComparator(T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }

    /**
     * Sorts an array using the natural ordering of its elements.
     * @param array The array to be sorted.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        Arrays.sort(array);
    }

    /**
     * Searches for the specified object in the specified array using the binary search algorithm.
     * The array must be sorted into ascending order according to the natural ordering of its elements.
     * @param array The array to be searched.
     * @param key The value to be searched for.
     * @param <T> The type of elements in the array.
     * @return The index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1).
     */
    public static <T extends Comparable<? super T>> int binarySearch(T[] array, T key) {
        return Arrays.binarySearch(array, key);
    }

    public static <T extends Comparable<T>> T[] fastSort(T[] array) {
        quickSort(array, 0, array.length - 1);
        return array; // 返回排序后的数组
    }
    private static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, left, right);
            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        // Choose pivot element using median-of-three method
        int mid = left + (right - left) / 2;
        T pivot = medianOfThree(array[left], array[mid], array[right]);

        int i = left - 1;
        int j = right + 1;
        while (true) {
            do {
                i++;
            } while (array[i].compareTo(pivot) < 0);
            do {
                j--;
            } while (array[j].compareTo(pivot) > 0);

            if (i >= j) {
                return j;
            }
            swap(array, i, j);
        }
    }

    private static <T extends Comparable<T>> T medianOfThree(T a, T b, T c) {
        if (a.compareTo(b) > 0) {
            T temp = a;
            a = b;
            b = temp;
        }
        if (b.compareTo(c) > 0) {
            T temp = b;
            b = c;
            c = temp;
        }
        return b;
    }


    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 对键值对数组进行排序，并返回排序后的数组。
     * @param array 要排序的键值对数组。
     * @param <K> 键的类型。
     * @param <V> 值的类型。
     * @return 排序后的键值对数组。
     */
    public static <K extends Comparable<? super K>, V> Pair<K, V>[] sortPairsByKeys(Pair<K, V>[] array) {
        Arrays.sort(array, Comparator.comparing(Pair::getKey));
        return array;
    }

    public static <T> int partitionByRule(T[] array, int size, Predicate<T> rule) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            while (left <= right && rule.test(array[left])) {
                left++;
            }
            while (left <= right && !rule.test(array[right])) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }

        return left;
    }
}
