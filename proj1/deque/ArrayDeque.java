package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items=(T[]) new Object[10];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size=0;
        nextFirst=3;
        nextLast=4;
    }

    public ArrayDeque(T item) {
        items[3] = item;
        size = 1;
        nextFirst = 2;
        nextLast = 4;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }


    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        int firstpos = Math.abs(newSize-size)/2;
        System.arraycopy(items, 0, newItems, firstpos,size);
        items = newItems;
        nextFirst = minusOne(firstpos);
        nextLast = (nextFirst+size)%items.length;
    }

    public void addFirst(T item){
        if(size==items.length){
            resize(items.length*2);
        }
        items[nextFirst]=item;
        size++;
        minusOne(nextFirst);
    }

    public void addLast(T item){
        if(size==items.length){
            resize(items.length*2);
        }
        items[nextLast]=item;
        size++;
        plusOne(nextLast);
    }

    public int size(){
        return size;
    }

    private void shrinkSize() {
        if (isEmpty()) {
            resize(8);
        } else if (items.length / 4 > size && size >= 4) {
            resize(size * 2);
        }
    }

    public T removeFirst(){
        nextFirst=plusOne(nextFirst);
        T item=items[nextFirst];
        items[nextFirst]=null;
        size--;
        shrinkSize();
        return item;

    }
    public T removeLast(){
        nextLast=minusOne(nextLast);
        T item=items[nextLast];
        items[nextLast]=null;
        size--;
        shrinkSize();
        return item;
    }

    public T get(int index){
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return items[(index+nextFirst+1)%items.length];
    }
    public void printDeque(){
        int currentFist=minusOne(nextFirst);
        int currentLast=minusOne(nextLast);
        while(currentFist!=currentLast){
            System.out.print(items[currentFist]+" ");
            plusOne(currentFist);
        }
        System.out.println();
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int currentFirst=minusOne(nextFirst);
        private int currentLast=minusOne(nextLast);
        public boolean hasNext(){
            return currentFirst!=currentLast;
        }
        public T next(){
            T item=items[currentFirst];
            currentFirst=plusOne(currentFirst);
            return item;
        }
    }

    public boolean equals(Object o){
        if(o==null)return false;
        if(o==this)return true;
        if(!(o instanceof ArrayDeque))return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if(size!=other.size)return false;
        for(int currentFirst=plusOne(this.nextFirst); currentFirst<minusOne(this.nextLast); plusOne(this.nextFirst)){
            if(items[currentFirst]!=other.items[currentFirst])return false;
        }
        return true;
    }

}
