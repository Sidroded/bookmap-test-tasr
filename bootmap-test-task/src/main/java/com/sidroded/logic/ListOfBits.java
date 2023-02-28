package com.sidroded.logic;

import com.sidroded.bitmodel.Bit;
import com.sidroded.bits.Ask;
import com.sidroded.bits.Bid;
import com.sidroded.bits.Spread;

import java.io.FileInputStream;
import java.util.*;

public class ListOfBits {
    private final FileInputStream inputStream;
    private TreeSet<Bit> allBits = new TreeSet<>();
    private TreeSet<Bid> allBids = new TreeSet<>();
    private TreeSet<Ask> allAsks = new TreeSet<>();
    private ArrayList<String> inputCommands = new ArrayList<>();

    public ListOfBits(FileInputStream inputStream) {
        this.inputStream = inputStream;
        fillInputCommands();
    }


    public Set<Bit> getAllBits() {
        return allBits;
    }

    public Set<Bid> getAllBids() {
        return allBids;
    }

    public Set<Ask> getAllAsks() {
        return allAsks;
    }

    private void fillInputCommands() {
        try(Scanner scanner = new Scanner(inputStream);) {

            while (scanner.hasNext()) {
                inputCommands.add(scanner.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runCommands() {
        for (String command : inputCommands) {
            String[] parts = command.split(",");

            switch (parts[0]) {
                case "u":
                    update(parts[1], parts[2], parts[3]);
                    break;
                case "o":
                    order(parts[1],parts[2]);
                    break;
                case "q":
                    if (parts.length == 2) {
                        querie(parts[1]);
                    } else {
                        querie(parts[1], parts[2]);
                    }
            }
        }
    }
    private void order(String order, String size) {

    }

    private void update(String priceStr, String sizeStr, String bit) {
        int price = Integer.parseInt(priceStr);
        int size = Integer.parseInt(sizeStr);

        switch (bit) {
            case "bid" -> {
                Bid bid = new Bid(price, size);
                if (allBits.contains(bid)) {
                    allBits.remove(bid);
                    allBids.remove(bid);
                }
                allBids.add(bid);
                allBits.add(bid);

            }
            case "ask" -> {
                Ask ask = new Ask(price, size);
                if (allBits.contains(ask)) {
                    allBits.remove(ask);
                    allAsks.remove(ask);
                }
                allAsks.add(ask);
                allBits.add(ask);
            }
            case "spread" -> {
                Spread spread = new Spread(price, size);
                allBits.remove(spread);
                allBits.add(spread);
            }
        }
    }

    private void querie(String bestBit) {

    }

    private void querie(String size, String price) {

    }




}
