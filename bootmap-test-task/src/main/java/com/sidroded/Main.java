package com.sidroded;

import com.sidroded.logic.ListOfBits;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "d:/Test/input.txt";

        FileInputStream inputStream = new FileInputStream(path);

        ListOfBits list = new ListOfBits(inputStream);

        list.runCommands();
        System.out.println(list.getAllBits());
    }
}