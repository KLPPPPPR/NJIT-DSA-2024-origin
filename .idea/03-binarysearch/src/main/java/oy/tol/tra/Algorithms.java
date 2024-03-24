package oy.tol.tra;
public class Algorithms {

    private Algorithms() {
        // Private constructor to prevent instantiation
    }

    /**
     * Performs insertion sort on the specified array of integers.
     *
     * @param array The array of integers to be sorted.
     */
    public static void sort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Performs insertion sort on the specified array of strings.
     *
     * @param array The array of strings to be sorted.
     */
    public static void sort(String[] array) {
        for (int i = 1; i < array.length; i++) {
            String key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /**
     * Performs a binary search on the specified array, returning the index of the element if found, -1 otherwise.
     *
     * @param <T>       The type of elements in the array, must implement Comparable interface.
     * @param aValue    The value to find.
     * @param fromArray The array to search.
     * @param fromIndex The index to start searching from.
     * @param toIndex   The index to search to in the array.
     * @return The index of the element in the array, -1 if not found.
     */
    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        // Binary search implementation
        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;

            int comparison = aValue.compareTo(fromArray[mid]);

            // Check if aValue is present at mid
            if (comparison == 0)
                return mid;

            // If aValue greater, ignore left half
            if (comparison > 0)
                fromIndex = mid + 1;

                // If aValue is smaller, ignore right half
            else
                toIndex = mid - 1;
        }

        // aValue is not present in array
        return -1;
    }

    /**
     * Sorts the given array based on its type.
     *
     * @param array The array to be sorted.
     */
    public static void sort(Comparable[] array) {
        if (array instanceof Integer[]) {
            sort((Integer[]) array);
        } else if (array instanceof String[]) {
            sort((String[]) array);
        }
    }
}

