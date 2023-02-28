package com.sidroded.bitmodel;

public abstract class Bit implements Updatable {
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

}
