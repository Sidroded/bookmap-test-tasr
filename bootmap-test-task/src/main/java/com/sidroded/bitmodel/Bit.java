package com.sidroded.bitmodel;

import java.util.Objects;

public abstract class Bit implements Comparable<Bit> {
    private final int price;

    public Bit(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bit bit = (Bit) o;
        return price == bit.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
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
                " price=" + price;
    }
}
