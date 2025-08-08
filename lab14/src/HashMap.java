import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<K,V> implements Map61BL<K,V> {


    /* TODO: Instance variables here */
    private LinkedList<Entry<K,V>>[] buckets;
    private int initialCapacity;
    private double loadFactor;

    /* TODO: Constructors here */
    public HashMap() {
        initialCapacity = 16;
        loadFactor = 0.75;
        buckets = (LinkedList<Entry<K,V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public HashMap(int initialCapacity){
        this.initialCapacity = initialCapacity;
        loadFactor = 0.75;
        buckets = (LinkedList<Entry<K,V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public HashMap(int initialCapacity,double loadFactor){
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = (LinkedList<Entry<K,V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    /* TODO: Interface methods here */
    @Override
    public void clear() {
        this.buckets = (LinkedList<Entry<K,V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean containsKey(K key) {
//        for (int i = 0; i < initialCapacity; i++) {
//            if (buckets[i] == null) {
//                return false;
//            }
//        }
        int count = buckets[Math.floorMod(key.hashCode(),initialCapacity)].size();
        for (int i = 0; i < count; i++) {
            if (buckets[Math.floorMod(key.hashCode(), initialCapacity)].get(i).key == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (!this.containsKey(key)) {
            return null;
        }
        for (int i = 0; i < buckets[Math.floorMod(key.hashCode(),initialCapacity)].size(); i++) {
            if (buckets[Math.floorMod(key.hashCode(),initialCapacity)].get(i).key == key) {
                return buckets[Math.floorMod(key.hashCode(), initialCapacity)].get(i).value;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (this.containsKey(key)){
            if (!this.get(key).equals(value)) {
                for (int i = 0; i < buckets[Math.floorMod(key.hashCode(),initialCapacity)].size(); i++) {
                    if (buckets[Math.floorMod(key.hashCode(),initialCapacity)].get(i).key == key) {
                        this.buckets[Math.floorMod(key.hashCode(), initialCapacity)].get(i).value = value;
                    }
                }
            }
            return;
        }
        Entry<K,V> pair = new Entry<>(key,value);
        buckets[Math.floorMod(key.hashCode(),initialCapacity)].add(pair);
        this.resize(this.size());
    }

    @Override
    public V remove(K key) {
        LinkedList<Entry<K,V>> temp = buckets[Math.floorMod(key.hashCode(),initialCapacity)];
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).key == key) {
                return buckets[Math.floorMod(key.hashCode(), initialCapacity)].remove(i).value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        if (!this.containsKey(key) || this.get(key) != value) {
            return false;
        } else {
            this.remove(key);
            return true;
        }
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < initialCapacity; i++){
            count += buckets[i].size();
        }
        return count;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private void resize(int size){
        double newFactor = (double) size / (double) this.buckets.length;
        if (newFactor <= this.loadFactor){
            return;
        }
        LinkedList<Entry<K,V>>[] temp = this.buckets;
        this.initialCapacity *= 2;
        this.buckets = (LinkedList<Entry<K,V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++){
            buckets[i] = new LinkedList<>();
        }
        for (int i = 0; i < initialCapacity/2; i++){
            while (!temp[i].isEmpty()) {
                this.put(temp[i].getFirst().key,temp[i].removeFirst().value);
            }
        }
    }

    public int capacity(){
        return this.buckets.length;
    }

    private static class Entry<K,V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
