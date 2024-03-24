package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Pair<K, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 20;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        values = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double)values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        int index = Math.abs(key.hashCode() % values.length);

        int probingSteps = 0;
        while (values[index] != null) {
            if (probingSteps > maxProbingSteps) {
                maxProbingSteps = probingSteps;
            }
            if (values[index].getKey().equals(key)) {
                values[index] = new Pair<>(key, value);
                return true;
            }
            index = (index + 1) % values.length;
            probingSteps++;
            collisionCount++;
        }
        values[index] = new Pair<>(key, value);
        count++;
        if (((double) count / values.length) > LOAD_FACTOR) {
            reallocate(values.length * 2);
        }

        return true;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = Math.abs(key.hashCode() % values.length);

        int probingSteps = 0;
        while (values[index] != null && probingSteps < values.length) {
            if (values[index].getKey().equals(key)) {
                return values[index].getValue();
            }
            index = (index + 1) % values.length;
            probingSteps++;
        }

        return null;
    }

    @Override
    public Pair<K,V>[] toSortedArray() {
        Pair<K, V>[] sorted = (Pair<K,V>[])new Pair[count];
        int newIndex = 0;

        for (Pair<K, V> value : values) {
            if (value != null) {
                sorted[newIndex++] = new Pair<>(value.getKey(), value.getValue());
            }
        }

        Arrays.sort(sorted, 0, newIndex, Comparator.comparing(Pair::getKey));

        return sorted;
    }

    @SuppressWarnings("unchecked")
    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize = DEFAULT_CAPACITY;
        }

        Pair<K, V>[] oldValues = values;
        values = (Pair<K, V>[]) new Pair[newSize];

        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;

        for (Pair<K, V> oldValue : oldValues) {
            if (oldValue != null) {
                add(oldValue.getKey(), oldValue.getValue());
            }
        }

        reallocationCount++;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int)(count * (1.0 / LOAD_FACTOR));
        if (newCapacity < values.length) {
            reallocate(newCapacity);
        }
    }
}
