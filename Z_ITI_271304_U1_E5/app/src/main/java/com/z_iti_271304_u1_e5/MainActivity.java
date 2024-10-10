package com.z_iti_271304_u1_e5;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RadioGroup[][] radioGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        Button btnGenerate = findViewById(R.id.btnGenerate);
        EditText etNumVariables = findViewById(R.id.etNumVariables);

        btnGenerate.setOnClickListener(v -> {
            int numVariables;
            try {
                numVariables = Integer.parseInt(etNumVariables.getText().toString());
                if (numVariables > 7) {
                    numVariables = 7;
                    Toast.makeText(this, "Numero de variables LIMITADO a 7", Toast.LENGTH_SHORT).show();
                    etNumVariables.setText("7");
                }
            } catch (NumberFormatException e) {
                numVariables = 3; // valor por defecto
                Toast.makeText(this, "Numero de variables no válido, se usará el valor por defecto (3)", Toast.LENGTH_SHORT).show();
            }

            int numRows = (int) Math.pow(2, numVariables);
            tableLayout.removeAllViews();

            radioGroups = new RadioGroup[numRows][1];

            int[][] truthTable = generateTruthTable(numVariables);

            // Crear filas y columnas
            for (int i = 0; i < numRows; i++) {
                TableRow tableRow = new TableRow(this);

                // Llenar las celdas con 0 y 1
                for (int j = 0; j < numVariables; j++) {
                    TextView textView = new TextView(this);
                    textView.setText(String.valueOf(truthTable[i][j]));
                    textView.setPadding(8, 8, 8, 8);
                    tableRow.addView(textView);
                }

                // Crear RadioGroup con tres RadioButtons (0, 1, X)
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setOrientation(RadioGroup.HORIZONTAL);

                // Los Ids únicos evitan que se seleccionen varios RadioButtons a la vez
                RadioButton radioButton0 = new RadioButton(this);
                radioButton0.setText("0");
                radioButton0.setId(View.generateViewId());
                radioButton0.setChecked(true); // Por defecto seleccionado

                RadioButton radioButton1 = new RadioButton(this);
                radioButton1.setId(View.generateViewId());
                radioButton1.setText("1");

                RadioButton radioButtonX = new RadioButton(this);
                radioButtonX.setId(View.generateViewId());
                radioButtonX.setText("X");

                radioGroup.addView(radioButton0);
                radioGroup.addView(radioButton1);
                radioGroup.addView(radioButtonX);

                tableRow.addView(radioGroup);
                radioGroups[i][0] = radioGroup;

                tableLayout.addView(tableRow);
            }
        });
        Button btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(v -> {
            // Dimensiones de la matriz
            int numRows = radioGroups.length;
            int numColumns = 3;

            int[][] resultMatrix = new int[numRows][numColumns];

            for (int i = 0; i < radioGroups.length; i++) {
                RadioGroup radioGroup = radioGroups[i][0];
                int selectedId = radioGroup.getCheckedRadioButtonId();
                int radioButtonCount = radioGroup.getChildCount();

                // Asignar valor a cada RadioButton en el RadioGroup
                for (int j = 0; j < radioButtonCount; j++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(j);

                    // Si el RadioButton está seleccionado, se asigna 1, si no, se asigna 0
                    if (radioButton.getId() == selectedId) {
                        resultMatrix[i][j] = 1;
                    } else {
                        resultMatrix[i][j] = 0;
                    }
                }
            }

            // Convertir la matriz a una lista
            ArrayList<int[]> resultMatrixList = new ArrayList<>(Arrays.asList(resultMatrix));

            // Enviar la matriz a una nueva actividad
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("resultMatrix", resultMatrixList);
            startActivity(intent);
            Log.d("RESULT_MATRIX", java.util.Arrays.deepToString(resultMatrix));
        });
    }

    // Generar tabla de verdad
    private int[][] generateTruthTable(int numVariables) {
        int numRows = (int) Math.pow(2, numVariables);
        int[][] truthTable = new int[numRows][numVariables];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numVariables; j++) {
                truthTable[i][j] = (i / (int) Math.pow(2, numVariables - j - 1)) % 2;
            }
        }
        return truthTable;
    }
}