package com.example.mission_to_mars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtSumU1;
    TextView txtSumU2;
    Simulation simulation = new Simulation(this);
    private int totalBudgetU1 = 0;
    private int totalBudgetU2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSumU1 = findViewById(R.id.txt_sum_U1);
        txtSumU2 = findViewById(R.id.txt_sum_U2);
    }

    public void runTheSimulation(View view) throws Exception {
        //Запуск моделювання для U1
        totalBudgetU1 = simulation.runSimulation(simulation.loadU1(simulation.loadItems("Phase-1.txt"))); //перша фаза
        totalBudgetU1 += simulation.runSimulation(simulation.loadU1(simulation.loadItems("Phase-2.txt"))); //друга фаза
        txtSumU1.setText(totalBudgetU1 + " million $");

        //Запуск моделювання для U2
        totalBudgetU2 = simulation.runSimulation(simulation.loadU2(simulation.loadItems("Phase-1.txt"))); //перша фаза
        totalBudgetU2 += simulation.runSimulation(simulation.loadU2(simulation.loadItems("Phase-2.txt"))); //друга фаза
        txtSumU2.setText(totalBudgetU2 + " million $");

    }
}