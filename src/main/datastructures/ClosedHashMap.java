package main.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ClosedHashMap<V> implements Iterable<ClosedHashMap.KeyValuePair<V>>{
	private static int DEFAULT_SIZE = 23;
	private KeyValuePair<V>[] map;
	private int currentSize;
	
	public ClosedHashMap() throws IllegalArgumentException {
		this(DEFAULT_SIZE);
	}

	
	@SuppressWarnings("unchecked")
	public ClosedHashMap(final int size) throws IllegalArgumentException {
		if(!isPrime(size) || size % 4 != 3) {
			throw new IllegalArgumentException("Eigenschaften nicht erfuellt");
		}
		this.currentSize = 0;
		this.map = new KeyValuePair[size];
	}
	
	boolean isPrime(final int n) {
		for(int i = 2; i < n/2; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private int hashFunction(final int key, final int i) {
		return (key + i*i) % this.map.length;
	}
	
	public V put(final int key, final V value) throws IllegalStateException {
		int i = 0;
		for(;this.map[this.hashFunction(key, i)] != null; i++) {
			if(this.map[this.hashFunction(key, i)].getKey() == key) {
				V oldVal = this.map[this.hashFunction(key, i)].getValue();
				this.map[this.hashFunction(key, i)] = new KeyValuePair<V>(key, value);
				return oldVal;
			}
		}
		this.map[this.hashFunction(key, i)] = new KeyValuePair<V>(key, value);
		
		if(((float) ++this.currentSize)/((float) this.map.length) > 0.8f) {
			this.rehash();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void rehash() {
		int size = findSize();
		ArrayList<KeyValuePair<V>> pairs = new ArrayList<KeyValuePair<V>>();
		for(KeyValuePair<V> pair: this) {
			pairs.add(pair);
		}
		this.map = new KeyValuePair[size];
		for(KeyValuePair<V> pair: pairs) {
			this.put(pair.getKey(), pair.getValue());
		}
	}
	
	private int findSize() {
		int currSize = this.map.length + 4;
		while(!isPrime(currSize) || currSize % 4 != 3) {
			currSize +=4;
		}
		return currSize;
	}
	
	
	public boolean containsKey(final int key) {
		return this.get(key) != null;
	}

	public V get(final int key) {
		int i = 0;
		for(;this.map[this.hashFunction(key, i)] != null; i++) {
			if(this.map[this.hashFunction(key, i)].getKey() == key) {
				return this.map[this.hashFunction(key, i)].getValue();
			}
		}
		return null;
	}
	
	public Iterator<KeyValuePair<V>> iterator() {
		return new Iterator<KeyValuePair<V>>() {

			int index = 0;

			@Override
			public boolean hasNext() {
				boolean result = false;
				int i = index;
				while (i < map.length && !result) {
					result = (map[i] != null);
					i++;
				}
				return result;
			}

			@Override
			public KeyValuePair<V> next() throws NoSuchElementException {
				int i = index;
				KeyValuePair<V> result = null;    
				while (i < map.length && map[i] == null) {
					index++;
					i = index;
				}
				if (index >= map.length) {
					throw new NoSuchElementException("No such element!");
				}
				result = map[i];
				index++;
				return result;
			}
		};
	}
	
	static class KeyValuePair<G>{

		private final int key;
		private final G value;

		public KeyValuePair(final int key, final G value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return this.key;
		}

		public G getValue() {
			return this.value;
		}
	}
}
