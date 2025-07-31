import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B <T> implements Deque61B<T>{

    Node sentinal;
    int size;

    private class Node{
        Node head,tail;
        T item;
        public Node(Node head,T item,Node tail){
            this.head = head;
            this.item = item;
            this.tail = tail;
        }
        public T getRecursiveHelper(int index,int count){
            if (count < index){
                count++;
                return this.tail.getRecursiveHelper(index,count);
            }
            return this.item;
        }
    }

    public LinkedListDeque61B(){
        sentinal = new Node(null,null,null);
        sentinal.head = sentinal;
        sentinal.item = null;
        sentinal.tail = sentinal.head;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node temp = sentinal.tail;
        sentinal.tail = new Node(sentinal,x,sentinal.tail);
        temp.head = sentinal.tail;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node temp = sentinal.head;
        sentinal.head = new Node(sentinal.head,x,sentinal);
        temp.tail = sentinal.head;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (Node p = this.sentinal.tail;p != sentinal;p = p.tail){
            returnList.add(p.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (this.sentinal.head == sentinal) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        Node temp = sentinal.tail;
        if (this.sentinal.tail==sentinal) {
            return null;
        }
        this.sentinal.tail = temp.tail;
        temp.tail.head = sentinal;
        size--;
        return temp.item;
    }

    @Override
    public T removeLast() {
        Node temp = sentinal.head;
        if (this.sentinal.head == sentinal) {
            return null;
        }
        this.sentinal.head = temp.head;
        temp.head.tail = sentinal;
        size--;
        return temp.item;
    }

    @Override
    public T get(int index) {
        Node p = this.sentinal;
        index++;
        if (index > size || index <= 0) {
            return null;
        }
        for (int count = 0;count < index;count++){
            p = p.tail;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        Node p = this.sentinal;
        index++;
        if (index > size || index <= 0) {
            return null;
        }
        return p.getRecursiveHelper(index,0);
    }

}
