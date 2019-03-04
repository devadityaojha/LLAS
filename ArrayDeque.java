public class ArrayDeque<T> {
    private T[] arraynode;
    private int size = 0;
    private int nextFirst = 0;
    private int nextLast = 1;

    public ArrayDeque() {
        arraynode = (T[]) new Object[8];
    }

    //deep copy of ArrayDeque
    public ArrayDeque(ArrayDeque other) {
        this.arraynode = (T[]) new Object[other.arraynode.length];
        System.arraycopy(other.arraynode, 0, arraynode, 0, other.arraynode.length);
        this.size = other.size();
        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
    }

    public void addFirst(T item) {
        arraynode[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = arraynode.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
        if (size == arraynode.length) {
            resize(arraynode.length * 2);
        }
    }

    public void addLast(T item) {
        arraynode[nextLast] = item;
        nextLast = Math.floorMod((nextLast + 1), (arraynode.length));
        size += 1;
        if (size == arraynode.length) {
            resize(arraynode.length * 2);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        int pointer = nextFirst % (arraynode.length - 1) + 1;
        for (int i = 0; i <= arraynode.length - 1; i++) {
            System.out.print(arraynode[pointer + i] + " ");
        }
        pointer = 0;
        for (int i = 0; i <= nextFirst; i++) {
            System.out.print(arraynode[pointer + i] + " ");
        }

    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item;
        if (nextFirst == arraynode.length - 1) {
            item = (T) arraynode[0];
            nextFirst = 0;
            arraynode[0] = null;
        } else {
            item = (T) arraynode[nextFirst + 1];
            arraynode[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        if (size > 0 && size <= arraynode.length * 0.25 && arraynode.length >= 16) {
            resize(arraynode.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item;
        if (nextLast == 0) {
            item = (T) arraynode[arraynode.length - 1];
            arraynode[arraynode.length - 1] = null;
            nextLast = arraynode.length - 1;
        } else {
            item = (T) arraynode[nextLast - 1];
            arraynode[nextLast - 1] = null;
            nextLast -= 1;
        }
        size -= 1;
        if (size > 0 && size <= arraynode.length * 0.25 && arraynode.length >= 16) {
            resize(arraynode.length / 2);
        }
        return item;
    }

    private void resize(int maxLength) {
        T[] newArray = (T[]) new Object[maxLength];
        // int nodeLength = arraynode.length;
        if (Math.abs(nextLast - nextFirst - 1) == size) {
            System.arraycopy(arraynode, Math.floorMod((nextFirst + 1),
                    (arraynode.length)), newArray, 0, size);
            nextLast = size;
            nextFirst = maxLength - 1;

        } else {
            System.arraycopy(arraynode, 0, newArray, 0, nextLast);
            System.arraycopy(arraynode, nextFirst + 1, newArray,
                    maxLength - (arraynode.length - nextFirst - 1),
                    arraynode.length - nextFirst - 1);
            nextFirst = maxLength - (arraynode.length - nextFirst);
        }
        arraynode = newArray;

    }

    public T get(int index) {
        int pointer = nextFirst + 1;
        int arrayIndex;
        if (index >= size) {
            return null;
        } else {
            if (nextFirst == arraynode.length - 1) {
                arrayIndex = index;
            } else {
                if (index < (arraynode.length - 1) - pointer) {
                    arrayIndex = (pointer + index) % (arraynode.length - 1);
                } else if (index == arraynode.length - 1 - pointer) {
                    arrayIndex = arraynode.length - 1;
                } else {
                    arrayIndex = ((pointer + index) % (arraynode.length - 1)) - 1;
                    // @source geeksforgeeks
                }
            }
        }
        return arraynode[arrayIndex];
    }
}
