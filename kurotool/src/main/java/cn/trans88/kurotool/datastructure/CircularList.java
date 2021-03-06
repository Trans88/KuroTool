package cn.trans88.kurotool.datastructure;

public class CircularList<T> {
    private static final String TAG = "CircularList";
    //Head node
    private  Item<T> header;
    private  int size;

    public class Item<O> {
        public   O value;
        private  Item<O> prev;
        private  Item<O> next;
        Item(O obj) {
            value = obj;
            prev = this;
            next = this;
        }


    }

    /**
     * Initialize linked list
     * */
    public CircularList() {
        header = null;
        size = 0;
    }

    /**
     * Insert linked list (tail)
     * */
    public   int insertTail(T obj) {
        Item<T> e = new Item<>(obj);
        if(null == header) {
            header = e;
            e.next = header;
            e.prev = header;
        } else {
            Item<T> temp = header.prev;
            temp.next = e;
            e.next = header;
            e.prev = temp;
            header.prev = e;
        }
        ++size;
        return size;
    }

    /**
     * Insert linked list (head)
     */
    public int insertHead(T obj) {
        insertTail(obj);
        header = header.prev;
        return size;
    }

    /**
     * Delete elements in linked list
     * */
    public synchronized boolean delete(T obj) {
        if (null == header) {
            return false;
        }
        if (1 == size) {
            if (header.value.equals(obj)) {
                clean();
            }
            return 0 == size;
        }
        Item<T> cur = header;
        while(true) {
            if (cur.value.equals(obj)) {
                if (cur.equals(header)) {
                    header = header.next;
                }
                cur.next.prev = cur.prev;
                cur.prev.next = cur.next;
                cur.prev = cur;
                cur.next = cur;
                --size;
                return true;
            }
            cur = cur.next;
            if (cur.equals(header)) {
                break;
            }
        }
        return false;
    }

    public void clean() {
        header = null;
        size = 0;
    }

    public boolean delete(int index) {
        T value = getItem(index);
        return delete(value);
    }

    public T getHead() {
        if (null != header) {
            return header.value;
        }
        return null;
    }

    public synchronized T getHeadAndNext() {
            if (null != header) {
                T o = header.value;
                header = header.next;
                return o;
            }

            return null;
    }

    public boolean setHead(T o) {
        if (!isEmpty() && isContains(o)) {
            while (!header.value.equals(o)) {
                header = header.next;
            }
            return true;
        }
        return false;
    }

    /**
     * Get the element of the i position of the linked list
     * I from 0
     * */
    public T getItem(int index) {
        if (null == header) {
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        Item<T> cur = header;
        for (int i = 0; i < size; ++i) {
            if (i == index) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        Item<T> cur = header;
        return cur.prev.value;
    }

    public T getNext(T obj) {
        if (null == header) {
            return null;
        }
        Item<T> cur = header;
        for (int i = 0; i < size; ++i) {
            if (cur.value.equals(obj)) {
                return cur.next.value;
            }
            cur = cur.next;
        }
        return header.value;
    }

    public T getPrev(T obj) {
        Item<T> cur = header;
        if (null == header) {
            return null;
        }
        for (int i = 0; i < size; ++i) {
            if (cur.value.equals(obj)) {
                return cur.prev.value;
            }
            cur = cur.prev;
        }
        return null;
    }

    public boolean isFirst(T obj) {
        if (null == obj) {
            return false;
        }
        if (null == header) {
            return false;
        }
        return obj.equals(header.value);
    }

    public boolean isLast(T obj) {
        if (null == obj) {
            return false;
        }
        if (null == header) {
            return false;
        }
        return obj.equals(header.prev.value);
    }

    /**
     * linked list length
     * */
    public int size() {
        return size;
    }

    /**
     * Determine whether an element exists in the linked list
     * */
    public boolean isContains(T obj) {
        Item<T> cur =header;
        if (null == cur) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if(cur.value.equals(obj)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * Judge whether it is empty
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    public void print(Callback<T> callback) {
        if (null != header) {
            Item<T> cur = header;
            for (int i = 0; i < 10; ++i) {
                callback.doWhat(cur.value);
                cur = cur.next;
            }
        }
    }

    public interface Callback<T> {
        void doWhat(T t);
    }
}
