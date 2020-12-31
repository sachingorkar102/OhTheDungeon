/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

//https://stackoverflow.com/questions/6409652/random-weighted-selection-in-java

public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }
    
    public boolean isEmpty() {
        return total == 0D;
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        if(map.higherEntry(value) == null) {
            if(map.lowerEntry(value) == null) return null;
            return map.lowerEntry(value).getValue();
        }
        return map.higherEntry(value).getValue();
    }
}
