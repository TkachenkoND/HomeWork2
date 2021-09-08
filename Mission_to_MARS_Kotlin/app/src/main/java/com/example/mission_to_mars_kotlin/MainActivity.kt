package com.example.mission_to_mars_kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var txtSumU1: TextView? = null
    var txtSumU2: TextView? = null
    var simulation = Simulation(this)
    private var totalBudgetU1 = 0
    private var totalBudgetU2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtSumU1 = findViewById(R.id.txt_sum_U1)
        txtSumU2 = findViewById(R.id.txt_sum_U2)
    }

    fun runTheSimulation(view: View){

        //Запуск моделювання для U1
        totalBudgetU1 = simulation.runSimulation(simulation.loadU1(simulation.loadItems("Phase-1.txt"))) //перша фаза
        totalBudgetU1 += simulation.runSimulation(simulation.loadU1(simulation.loadItems("Phase-2.txt"))) //друга фаза
        txtSumU1!!.text = "$totalBudgetU1 million $"

        //Запуск моделювання для U2
        totalBudgetU2 = simulation.runSimulation(simulation.loadU2(simulation.loadItems("Phase-1.txt"))) //перша фаза
        totalBudgetU2 += simulation.runSimulation(simulation.loadU2(simulation.loadItems("Phase-2.txt"))) //друга фаза
        txtSumU2!!.text = "$totalBudgetU2 million $"
    }

}