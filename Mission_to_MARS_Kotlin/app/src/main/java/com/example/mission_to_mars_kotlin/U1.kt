package com.example.mission_to_mars_kotlin

class U1: Rocket() {
    init {
        cost = 100
        weight = 10000
        maxWeight =18000
        launchExplosion = 0.0
        landingCrash = 0.0
        currentWeight = weight
    }

    override fun land(): Boolean{
        val random = (Math.random() * 100 + 1).toInt()
        landingCrash = 1.0 * (currentWeight - weight) / (maxWeight - weight)

        return landingCrash <= random
    }

    override fun launch(): Boolean {
        val random = (Math.random() * 100 + 1).toInt()
        launchExplosion = 5.0 * (currentWeight - weight) / (maxWeight - weight)

        return launchExplosion <= random
    }


}