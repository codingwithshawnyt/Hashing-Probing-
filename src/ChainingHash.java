import java.util.LinkedList;

public class ChainingHash<E> implements Hash<E> {
    private LinkedList<HashNode<E>>[] hashTable;
    private int size;
    private int numBuckets;

    public ChainingHash() {
        numBuckets = 10;
        hashTable = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            hashTable[i] = new LinkedList<>();
        }
        size = 0;
    }

    private class HashNode<E> {
        E value;

        public HashNode(E value) {
            this.value = value;
        }
    }

    private int hashFunction(E value) {
        return Math.abs(value.hashCode()) % numBuckets;
    }

    public boolean add(E value) {
        int bucketIndex = hashFunction(value);
        LinkedList<HashNode<E>> bucket = hashTable[bucketIndex];
    
        for (HashNode<E> node : bucket) {
            if (node.value.equals(value)) {
                return false;
            }
        }
    
        bucket.addFirst(new HashNode<>(value));  // Add new elements to the beginning of the list
        size++;
    
        return true;
    }
    
    public boolean contains(E value) {
        int bucketIndex = hashFunction(value);
        LinkedList<HashNode<E>> bucket = hashTable[bucketIndex];

        for (HashNode<E> node : bucket) {
            if (node.value.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public boolean remove(E value) {
        int bucketIndex = hashFunction(value);
        LinkedList<HashNode<E>> bucket = hashTable[bucketIndex];

        for (HashNode<E> node : bucket) {
            if (node.value.equals(value)) {
                bucket.remove(node);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int length() {
        return numBuckets;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
    
        for (int i = 0; i < numBuckets; i++) {
            LinkedList<HashNode<E>> bucket = hashTable[i];
            for (HashNode<E> node : bucket) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(node.value);
            }
        }
        return builder.toString();
    }
}