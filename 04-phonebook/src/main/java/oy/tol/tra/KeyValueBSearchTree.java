package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        String toReturn = "Tree has max depth of " + maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
        toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
            return true;
        }
        TreeNode<K, V> current = root;
        while (true) {
            int cmp = key.compareTo(current.getKey());
            if (cmp < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode<>(key, value));
                    count++;
                    return true;
                } else {
                    current = current.getLeft();
                }
            } else if (cmp > 0) {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode<>(key, value));
                    count++;
                    return true;
                } else {
                    current = current.getRight();
                }
            } else {
                current.setValue(value);
                return true;
            }
        }
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        TreeNode<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.getKey());
            if (cmp < 0) {
                current = current.getLeft();
            } else if (cmp > 0) {
                current = current.getRight();
            } else {
                return current.getValue();
            }
        }
        return null;
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
    }
}
