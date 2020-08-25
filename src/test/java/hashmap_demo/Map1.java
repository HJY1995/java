package hashmap_demo;

public interface Map1<K, V> {
    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry1<K, V> {
        K getKey();

        V getValue();
    }
}
