package com.sidroded;

import com.sidroded.logic.ApplicationStartUpPath;
import com.sidroded.logic.ListOfBits;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        ApplicationStartUpPath startUpPath = new ApplicationStartUpPath();
        String pathInput = "";
        String pathOutput= "";

        try {
            pathInput = startUpPath.getApplicationStartUp() + "/input.txt";
            pathOutput = startUpPath.getApplicationStartUp() + "/output.txt";
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