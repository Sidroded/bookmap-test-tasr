package com.sidroded.bits;

import com.sidroded.bitmodel.Bit;
import com.sidroded.bitmodel.Sellable;

public class Bid extends Bit implements Sellable {

    public Bid(int price, int size) {
        super(price, size);
    }

    @Override
    public void sell(int size) {
        update(size);
    }
}
