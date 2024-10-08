package com.z_iti_271304_u1_e5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner[][] spinners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        Button btnGenerate = findViewById(R.id.btnGenerate);
        EditText etNumVariables = findViewById(R.id.etNumVariables);

        //generar tabla
        btnGenerate.setOnClickListener(v -> {
            //leer num variables
            int numVariables;
            try {
                numVariables = Integer.parseInt(etNumVariables.getText().toString());
            } catch (NumberFormatException e) {
                numVariables = 3; //default
                Toast.makeText(this, "Número de variables no válido, se usará el valor por defecto (3)", Toast.LENGTH_SHORT).show();
            }
            int numRows = (int) Math.pow(2, numVariables);
            tableLayout.removeAllViews();

            spinners = new Spinner[numRows][numVariables];
            int[][] truthTable = generateTruthTable/*chingatumadreedmaverick*/(numVariables);

            //crear las filas y columnas
            for (int i = 0; i < numRows; i++) {
                TableRow tableRow = new TableRow(this);

                //llennar tabla verdad 0, 1
                for (int j = 0; j < numVariables; j++) {
                    TextView textView = new TextView(this);
                    textView.setText(String.valueOf(truthTable[i][j]));
                    textView.setPadding(8, 8, 8, 8);
                    tableRow.addView(textView);
                }

                // Crear un Spinner para cada fila para seleccionar "0", "1" o "X"
                Spinner spinner = new Spinner(this);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new String[]{"0", "1", "X"});
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                //agregar Spinner
                tableRow.addView(spinner);

                spinners[i][0] = spinner;

                tableLayout.addView(tableRow);
            }
        });

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(v -> {
            for (int i = 0; i < spinners.length; i++) {
                StringBuilder rowResult = new StringBuilder("Fila " + (i + 1) + ": ");
                for (int j = 0; j < spinners[i].length; j++) {
                    String selectedValue = spinners[i][j].getSelectedItem().toString();
                    rowResult.append(selectedValue).append(" ");
                }
                Toast.makeText(MainActivity.this, rowResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //tabla de verdad
    private int[][] generateTruthTable(int numVariables) {
        int numRows = (int) Math.pow(2, numVariables);
        int[][] truthTable = new int[numRows][numVariables];

        //llenado
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numVariables; j++) {
                truthTable[i][j] = (i / (int) Math.pow(2, numVariables - j - 1)) % 2;
            }
        }
        return truthTable;
    }
}
