package de.cristiano.marathon.daily;

/**
 * Provide a simple HashTable implementation
 */
class Day8 {


    static class HashTable {
        private Object[] data;

        HashTable(int size) {
            this.data = new Object[size];
        }

        private int _hash(String key) {
            int hash = 0;
            for (int i = 0; i < key.length(); i++) {
                hash = (hash + key.charAt(i) * i) % this.data.length;
            }
            return hash;
        }

        Object get(Object object) {
            final int hash = _hash(object.toString());

            return data[hash];
        }

        void set(Object object) {
            final int hash = _hash(object.toString());

            data[hash] = object;
        }
    }

}