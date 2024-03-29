package oy.tol.tra;

public class Algorithms<T extends Comparable<T>> {

    private T[] array = null;

    public Algorithms(T[] array) {
        this.array = array.clone();
    }

    public void reverse() {
        int i = 0;
        while (i < array.length/2) {
            T temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
            i++;
        }
    }

    public void sort() {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public T[] getArray() {
        return array;
    }
}
