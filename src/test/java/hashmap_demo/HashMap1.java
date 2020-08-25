package hashmap_demo;

public class HashMap1<K, V> implements Map1<K, V> {
    Entry1<K, V> table[] = null;
    int size = 0;

    public HashMap1() {
        table = new Entry1[16];
    }

    int hashIndex(K k) {
        int i = k.hashCode() % 16;
        return Math.abs(i);
    }


    /**
     * 1、计算参数k的index
     * 2、根据index拿到找到当前entry数组的值table[index]
     * 3、判断table[index]是否为空，为空则直接赋值，size++
     * 4、如果不为空(用链表)，则将table[index]设置为新的entery<k,v>，entry的next属性指向上一个值entry
     */
    @Override
    public V put(K k, V v) {
        int index = hashIndex(k);
        Entry1<K, V> entry1 = table[index];
        if (null == entry1) {
            table[index] = new Entry1<K, V>(k, v, index, null);
            size++;
            return null;
        } else {
            table[index] = new Entry1<K, V>(k, v, index, entry1);
        }
        return null;
    }

    /**
     * 1、获取参数k的index
     * 2、获取当前index的entry数组值table[index]
     * 3、判断table[index]是否为空，为空则直接返回空
     * 4、不为空，调用链表查询
     */
    @Override
    public V get(K k) {
        int index = hashIndex(k);
        Entry1<K, V> entry1 = table[index];
        if (null == entry1) {
            return null;
        }
        return findValue(k, entry1);
    }

    /**
     * 1、判断table[index]的k1和参数k是否相等，相等则直接返回当前entry的value
     * 2、如果不等，则判断entry.next是否为空，为空则直接返回空，
     * 如果不为空，则递归调用，判断 next的k2和参数k是否相同，
     * 如此循环，直到找到相等的k 或 next 为空
     */
    V findValue(K k, Entry1<K, V> entry1) {
        if (k == entry1.getKey() || k.equals(entry1.getKey())) {
            return entry1.getValue();
        } else if (entry1.next != null) {
            return findValue(k, entry1.next);
        }
        return null;
    }


    @Override
    public int size() {
        return size;
    }


    class Entry1<K, V> implements Map1.Entry1<K, V> {
        K k;
        V v;
        int index;
        Entry1<K, V> next;

        public Entry1(K k, V v, int index, Entry1<K, V> next) {
            this.k = k;
            this.v = v;
            this.index = index;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
