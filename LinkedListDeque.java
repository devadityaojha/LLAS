public class LinkedListDeque<T> {
    private class DequeNode<T> {
        private T item;
        private DequeNode<T> next = null;
        private DequeNode<T> prev = null;

        DequeNode(DequeNode p, T i, DequeNode n) {
            prev = p;
            item = i;
            next = n;
        }
    
        public void setNext(DequeNode<T> next) {
            this.next = next;
        }

        public void setPrev(DequeNode<T> prev) {
            this.prev = prev;
        }
    }

    private DequeNode<T> sentinel;
    private int size = 0;

    //creates an empty liked list deque
    public LinkedListDeque() {
        sentinel = new DequeNode<>(null, null, null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    //creates a deep copy of 'other'
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new DequeNode<>(null, null, null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
        for (int i = 0; i < other.size; i++) {
            T item = (T) other.get(i);
            this.addLast(item);
        }
        this.size = other.size();
    }

    //addFirst
    public void addFirst(T item) {
        DequeNode<T> first = new DequeNode<>(sentinel, item, sentinel.next);
        sentinel.next.setPrev(first);
        sentinel.setNext(first);
        size += 1;
    }

    //addLast
    public void addLast(T item) {
        DequeNode<T> last = new DequeNode<>(sentinel.prev, item, sentinel);
        sentinel.prev.setNext(last);
        sentinel.setPrev(last);
        size += 1;
    }

    //isEmpty
    public boolean isEmpty() {
        return size == 0;
    }

    //size
    public int size() {
        return size;
    }

    //print Deque
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(get(i).toString() + " ");
        }
    }

    //removeFirst
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T remvar = sentinel.next.item;
        sentinel.setNext(sentinel.next.next);
        sentinel.next.setPrev(sentinel);
        size -= 1;
        return remvar;
    }

    //removeLast
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T remvar = sentinel.prev.item;
        sentinel.setPrev(sentinel.prev.prev);
        sentinel.prev.setNext(sentinel);
        size -= 1;
        return remvar;
    }

    //gets item at given index through iteration
    public T get(int index) {
        if (isEmpty()) {
            return null;
        } else {
            DequeNode<T> pointer = sentinel.next;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return pointer.item;
                }
                pointer = pointer.next;
            }
            return pointer.item;
        }
    }

    //getRecursive helper function
    private T helperrecursive(int index, DequeNode<T> pointer) {
        if (index == 0) {
            return pointer.item;
        } else {
            return helperrecursive(index - 1, pointer.next); 
        }
    }

    //gets item at given index through recursion
    public T getRecursive(int index) {
        return helperrecursive(index, sentinel.next);
    }
}
