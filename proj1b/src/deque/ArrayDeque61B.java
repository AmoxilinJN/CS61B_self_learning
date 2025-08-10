package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {
    int initialCapacity;
    T[] items;
    int size;
    int nextFirst,nextLast;

    public ArrayDeque61B() {
        initialCapacity = 8;
        items = (T[]) new Object[initialCapacity];
        size = 0;
        nextFirst = initialCapacity / 2;
        nextLast = nextFirst + 1;
    }

    public ArrayDeque61B(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        items = (T[]) new Object[initialCapacity];
        size = 0;
        nextFirst = initialCapacity / 2;
        nextLast = nextFirst + 1;
    }

    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst-1, initialCapacity);
        size++;
        if (items[nextFirst] != null) {
            resize();
        }
    }

    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast+1, initialCapacity);
        size++;
        if (items[nextLast] != null) {
            resize();
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = nextFirst + 1; i < size + nextFirst + 1; i++) {
            int count = Math.floorMod(i, initialCapacity);
            returnList.add(items[count]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return items[initialCapacity / 2] == null && items[initialCapacity / 2 + 1] == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (items[Math.floorMod(nextFirst+1, initialCapacity)] == null) {
            return null;
        }
        nextFirst = Math.floorMod(nextFirst+1, initialCapacity);
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (initialCapacity >= 16 && (double) size / (double) initialCapacity <= 0.25) {
            resizeSmall();
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if (items[Math.floorMod(nextLast-1, initialCapacity)] == null) {
            return null;
        }
        nextLast = Math.floorMod(nextLast-1, initialCapacity);
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
        if (initialCapacity >= 16 && (double) size / (double) initialCapacity <= 0.25) {
            resizeSmall();
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= initialCapacity) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index + 1, initialCapacity)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private void resize() {
        ArrayDeque61B<T> temp = new ArrayDeque61B<>(this.initialCapacity * 2);
        for (int i = this.size / 2; i >= 0; i--) {
            temp.addFirst(this.get(i));
        }
        for (int i = this.size / 2 + 1; i < this.size; i++) {
            temp.addLast(this.get(i));
        }
        this.nextFirst = temp.nextFirst;
        this.nextLast = temp.nextLast;
        this.size = temp.size;
        this.items = temp.items;
        this.initialCapacity = temp.initialCapacity;
    }

    private void resizeSmall() {
        ArrayDeque61B<T> temp = new ArrayDeque61B<>(this.initialCapacity / 2);
        for (int i = this.size / 2; i >= 0; i--) {
            temp.addFirst(this.get(i));
        }
        for (int i = this.size / 2 + 1; i < this.size; i++) {
            temp.addLast(this.get(i));
        }
        this.nextFirst = temp.nextFirst;
        this.nextLast = temp.nextLast;
        this.size = temp.size;
        this.items = temp.items;
        this.initialCapacity = temp.initialCapacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator {

        T[] curr = items;
        int next = nextFirst + 1;
        int hasNext = nextFirst;
        int end = nextLast;
        @Override
        public boolean hasNext() {
            hasNext = Math.floorMod(++hasNext,initialCapacity);
            if (hasNext != end) {
                return curr[hasNext] != null;
            }
            return false;
        }

        @Override
        public Object next() {
            T item = curr[next];
            if (item == null) {
                throw new NoSuchElementException();
            }
            next = Math.floorMod(++next,initialCapacity);
            return item;
        }
    }

    @Override
    public boolean equals(Object other) {
        int i = 0;
        if (this == other) {
            return true;
        }
        if (other instanceof ArrayDeque61B otherDeque) {
            if (this.size != otherDeque.size) {
                return false;
            }
            for (T x : this) {
                if (x != otherDeque.get(i)) {
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}
