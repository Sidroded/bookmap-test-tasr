package com.sidroded.bitmodel;

import java.util.Objects;

public abstract class Bit implements Updatable, Comparable<Bit> {
    private int size;
    private final int price;

    public Bit(int price, int size) {
        this.price = price;
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void update(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bit bit = (Bit) o;
        return size == bit.size && price == bit.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, price);
    }

    @Override
    public int compareTo(Bit o) {
        if (this.price < o.price) {
            return 1;
        } else if (this.price > o.price) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " price=" + price +
                " size=" + size;
    }
}
