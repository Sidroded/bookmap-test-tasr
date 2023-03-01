package com.sidroded.logic;

import com.sidroded.bitmodel.Bit;
import com.sidroded.bits.Ask;
import com.sidroded.bits.Bid;
import com.sidroded.bits.Spread;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.util.*;

public class ListOfBits {
    private final FileInputStream inputStream;
    private final BufferedWriter writer;
    private final HashMap<Bit, Integer> allBits = new HashMap<>();
    private final TreeMap<Bit, Integer> allBids = new TreeMap<>();
    private final TreeMap<Bit, Integer> allAsks = new TreeMap<>();
    private final ArrayList<String> inputCommands = new ArrayList<>();
    private final StringBuilder result = new StringBuilder();

    public ListOfBits(FileInputStream inputStream, BufferedWriter writer) {
        this.inputStream = inputStream;
        this.writer = writer;
        fillInputCommands();
    }

    public String getResult() {
        return result.toString();
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
                        getBest(parts[1]);
                    } else {
                        getSize(parts[1], parts[2]);
                    }
            }
        }
    }

    public void outputResult() {
        Scanner scanner = new Scanner(getResult());

        try {
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                writer.write(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void order(String order, String sizeStr) {
        int size = Integer.parseInt(sizeStr);
        switch (order) {
            case "sell" -> {
                Bid bid = (Bid) allBids.firstKey();
                int sizeAfterSell = allBids.get(bid) - size;
                allBits.put(bid, sizeAfterSell);

                if (sizeAfterSell > 0) {
                    allBids.put(bid, sizeAfterSell);
                } else {
                    allBids.remove(bid);
                }
            }
            case "buy" -> {
                Ask ask = (Ask) allAsks.lastKey();
                int sizeAfterPurchase = allAsks.get(ask) - size;
                allBits.put(ask, sizeAfterPurchase);

                if (sizeAfterPurchase > 0) {
                    allAsks.put(ask, sizeAfterPurchase);
                } else {
                    allAsks.remove(ask);
                }
            }
        }
    }

    private void update(String priceStr, String sizeStr, String bit) {
        int price = Integer.parseInt(priceStr);
        int size = Integer.parseInt(sizeStr);

        switch (bit) {
            case "bid" -> {
                Bid bid = new Bid(price);
                allBits.put(bid, size);
                if (size != 0) {
                    allBids.put(bid, size);
                } else {
                    allBids.remove(bid);
                }
            }
            case "ask" -> {
                Ask ask = new Ask(price);
                allBits.put(ask, size);
                if (size != 0) {
                    allAsks.put(ask, size);
                } else {
                    allAsks.remove(ask);
                }
            }
            case "spread" -> {
                Spread spread = new Spread(price);
                allBits.put(spread, size);
            }
        }
    }

    private void getBest(String bestBit) {
        switch (bestBit) {
            case "best_bid" -> {
                if (!allBids.isEmpty()) {
                    Bid bid = (Bid) allBids.firstKey();
                    result.append(bid.getPrice()).append(",").append(allBids.get(bid)).append("\n");
                }
            }
            case "best_ask" -> {
                if (!allAsks.isEmpty()) {
                    Ask ask = (Ask) allAsks.lastKey();
                    result.append(ask.getPrice()).append(",").append(allAsks.get(ask)).append("\n");
                }
            }
        }
    }

    private void getSize(String size, String priceStr) {
        if (size.equals("size")) {
            int price = Integer.parseInt(priceStr);
            for (var bit : allBits.entrySet()) {
                if (bit.getKey().getPrice() == price) {
                    result.append(allBits.get(bit.getKey())).append("\n");
                }
            }
        }
    }
}
