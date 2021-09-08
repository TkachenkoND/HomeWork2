package com.example.mission_to_mars;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
    private Context context;

    public Simulation(Context context) {
        this.context = context;
    }

    public ArrayList<Item> loadItems(String fileName) throws IOException{
        AssetManager assManeger = context.getAssets();
        ArrayList<Item> loadManifest = new ArrayList();

        Scanner filescanner = new Scanner(assManeger.open(fileName));

        while (filescanner.hasNextLine()) {

            Item newItem = new Item();
            String[] tokens = filescanner.nextLine().split("=");
            newItem.name = tokens[0];
            newItem.weight = Integer.parseInt(tokens[tokens.length-1]);

            loadManifest.add(newItem);
        }

        return loadManifest;
    }

    public ArrayList<Rocket> loadU1(ArrayList<Item> list) {
        ArrayList<Rocket> rocketListOne = new ArrayList<>();
        Rocket rocketOne = new U1();

        int itemCounter = 1;
        int rocketCounter = 1;

        for (Item i : list) {

            while (!rocketOne.canCarry(i)) {

                rocketCounter++;
                rocketListOne.add(rocketOne);
                rocketOne = new U1();
            }
            rocketOne.carry(i);

            itemCounter++;
        }
        rocketListOne.add(rocketOne);

        return rocketListOne;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> list) {

        ArrayList<Rocket> rocketListTwo = new ArrayList();
        Rocket rocketTwo = new U2();

        int itemCounter = 1;
        int rocketCounter = 1;

        for (Item i : list) {

            while (!rocketTwo.canCarry(i)) {

                rocketCounter++;
                rocketListTwo.add(rocketTwo);
                rocketTwo = new U1();
            }
            rocketTwo.carry(i);

            itemCounter++;
        }
        rocketListTwo.add(rocketTwo);

        return rocketListTwo;
    }

    public int runSimulation(ArrayList<Rocket> rockets) {

        int totalCost = 0;

        for (Rocket currentRocket : rockets) {
            totalCost += currentRocket.getCost();

            do{
                totalCost += currentRocket.getCost();
            }while (!currentRocket.launch() || !currentRocket.land());
        }
        return totalCost;
    }

}
