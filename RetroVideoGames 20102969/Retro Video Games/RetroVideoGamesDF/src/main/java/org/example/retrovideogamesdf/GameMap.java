package org.example.retrovideogamesdf;

import java.io.*;
import java.security.Key;
import java.util.*;

public class GameMap<K, V> implements Serializable   {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<Entry<K,V>>[] buckets;
    private int size;

    public GameMap() {
        this.buckets = new List[INITIAL_CAPACITY];
        this.size = 0;
    }

    public  void putGames(K key, V value) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        // Check if the key already exists, update the value if it does
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Add a new entry
        buckets[index].add(new Entry<>(key, value));
        size++;

        // Resize if needed
        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V getGame(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }
    public V getGamesMachine(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }
    

    public void sortGames() {
        List<Entry<K, V>> allEntries = new ArrayList<>();
        for (List<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                allEntries.addAll(bucket);
            }
        }

        allEntries.sort((entry1, entry2) -> entry1.key.toString().compareTo(entry2.key.toString()));

        // Rebuild the buckets
        clearBuckets();
        allEntries.forEach(entry -> putGames(entry.key, entry.value));
    }

    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    private void resize() {
        List<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new List[oldBuckets.length * 2];

        for (List<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    putGames(entry.key, entry.value);
                }
            }
        }
    }

    private void clearBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    public List<Entry<K, V>> entrySet() {
        List<Entry<K, V>> allEntries = new ArrayList<>();
        for (List<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                allEntries.addAll(bucket);
            }
        }
        return allEntries;
    }

    public void removeGamesMachine(String machineName) {
        remove((K) machineName);
    }


    static class Entry<K, V> {
        final K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }
    }
    public void saveToFile(String GameMap) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(GameMap))) {
            outputStream.writeObject(this);
            System.out.println("Data saved to file: " + GameMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameMap<?, ?> loadFromFile(String GameMap) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(GameMap))) {
            Object obj = inputStream.readObject();
            if (obj instanceof GameMap) {
                System.out.println("Data loaded from file: " + GameMap);
                return (GameMap<?, ?>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void remove(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            List<GameMap.Entry<K, V>> bucket = buckets[index];
            for (GameMap.Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    bucket.remove(entry);
                    size--;
                    return;
                }
            }
        }
    }
}