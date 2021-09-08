package com.example.mission_to_mars_kotlin

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class Simulation(var context: Context) {

    @Throws(IOException::class)
    fun loadItems(fileName: String): ArrayList<Item>{
        val assManeger: AssetManager = context!!.assets
        val loadManifest: ArrayList<Item> = ArrayList()

        val filescanner = Scanner(assManeger.open(fileName))

        while (filescanner.hasNextLine()) {
            val newItem: Item = Item()
            val tokens = filescanner.nextLine().split("=").toTypedArray()
            newItem.name = tokens[0]
            newItem.weight = tokens[tokens.size - 1].toInt()
            loadManifest.add(newItem)
        }

        return loadManifest
    }

    fun loadU1(list: ArrayList<Item>): ArrayList<Rocket>{
        val rocketListOne : ArrayList<Rocket> = ArrayList()
        var rocketOne: Rocket = U1()

        var itemCounter = 1
        var rocketCounter = 1

        for (i in list) {
            while (!rocketOne.canCarry(i)) {
                rocketCounter++
                rocketListOne.add(rocketOne)
                rocketOne = U1()
            }
            rocketOne.carry(i)
            itemCounter++
        }
        rocketListOne.add(rocketOne)

        return rocketListOne
    }

    fun loadU2(list: ArrayList<Item>): ArrayList<Rocket>{
        val rocketListTwo: ArrayList<Rocket> = ArrayList()
        var rocketTwo: Rocket = U2()

        var itemCounter = 1
        var rocketCounter = 1

        for (i in list) {
            while (!rocketTwo.canCarry(i)) {
                rocketCounter++
                rocketListTwo.add(rocketTwo)
                rocketTwo = U1()
            }
            rocketTwo.carry(i)
            itemCounter++
        }
        rocketListTwo.add(rocketTwo)

        return rocketListTwo
    }

    fun runSimulation(rockets: ArrayList<Rocket>): Int{
        var totalCost = 0

        for (currentRocket in rockets) {
            totalCost += currentRocket.getCost()

            do {
                totalCost += currentRocket.getCost()
            }while (!currentRocket.launch() || !currentRocket.land())
        }
        return totalCost
    }
}