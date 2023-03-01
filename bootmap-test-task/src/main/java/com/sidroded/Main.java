package com.sidroded;

import com.sidroded.logic.ListOfBits;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String pathInput = "";
        String pathOutput= "";
        String dir = System.getProperty("user.dir");

        try {
            pathInput = dir + "/input.txt";
            pathOutput = dir + "/output.txt";
        } catch (Exception e) {

            e.printStackTrace();
        }

        try(FileInputStream inputStream = new FileInputStream(pathInput);
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathOutput))) {

            ListOfBits list = new ListOfBits(inputStream, writer);

            list.runCommands();

            list.outputResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}