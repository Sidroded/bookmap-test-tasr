package com.sidroded.bits;

import com.sidroded.bitmodel.Bit;
import com.sidroded.bitmodel.Buyable;

public class Ask extends Bit implements Buyable {

    public Ask(int price, int size) {
        super(price, size);
    }


    @Override
    public void buy(int size) {
        update(size);
    }
}
