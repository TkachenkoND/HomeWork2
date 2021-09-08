package com.example.mission_to_mars_kotlin

class U2: Rocket() {
    init {
        cost = 120
        weight = 18000
        maxWeight = 29000
        launchExplosion = 0.0
        landingCrash = 0.0
        currentWeight = weight
    }

    override fun land(): Boolean {
        val random = (Math.random() * 100 + 1).toInt()
        landingCrash = 8.0 * (currentWeight - weight) / (maxWeight - weight)

        return landingCrash <= random
    }

    override fun launch(): Boolean {
        val random = (Math.random() * 100 + 1).toInt()
        launchExplosion = 4.0 * (currentWeight - weight) / (maxWeight - weight)

        return launchExplosion <= random
    }
}