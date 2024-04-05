package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] itemArray;
    private int capacity;
    private int size;
    private int head;
    private int tail;

    public QueueImplementation(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.capacity = initialCapacity;
        this.itemArray = (E[]) new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public QueueImplementation() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(E element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size == capacity) {
            resizeArray();
        }
        itemArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return itemArray[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = head, count = 0; count < size; i = (i + 1) % capacity, count++) {
            sb.append(itemArray[i]);
            if (count < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void resizeArray() {
        int newCapacity = capacity * 2;
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0, j = head; i < size; i++, j = (j + 1) % capacity) {
            newArray[i] = itemArray[j];
        }
        itemArray = newArray;
        capacity = newCapacity;
        head = 0;
        tail = size;
    }
}
