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

        btnGenerate.setOnClickListener(v -> {
            int numVariables;
            try {
                numVariables = Integer.parseInt(etNumVariables.getText().toString());
                if (numVariables > 7) {
                    numVariables = 7;
                    Toast.makeText(this, "Numero de variables LIMITADO a 7"/*a peticion del profe para evitar desbordar la memoria*/, Toast.LENGTH_SHORT).show();
                    etNumVariables.setText("7");
                }
            } catch (NumberFormatException e) {
                numVariables = 3; // default por si es un chistoso
                Toast.makeText(this, "Numero de variables no válido, se usará el valor por defecto (3)", Toast.LENGTH_SHORT).show();
            }

            int numRows = (int) Math.pow(2, numVariables);
            tableLayout.removeAllViews();

            spinners = new Spinner[numRows][1];

            int[][] truthTable = generateTruthTable/*chingatumadreedmaverick*/(numVariables);

            // filas y columns
            for (int i = 0; i < numRows; i++) {
                TableRow tableRow = new TableRow(this);

                // llenar 0, 1
                for (int j = 0; j < numVariables; j++) {
                    TextView textView = new TextView(this);
                    textView.setText(String.valueOf(truthTable[i][j]));
                    textView.setPadding(8, 8, 8, 8);
                    tableRow.addView(textView);
                }

                // crear Spinner 0, 1 o X
                Spinner spinner = new Spinner(this);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new String[]{"0", "1", "X"});
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                tableRow.addView(spinner);
                spinners[i][0] = spinner;

                tableLayout.addView(tableRow);
            }
        });

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(v -> {
            for (int i = 0; i < spinners.length; i++) {
                StringBuilder rowResult = new StringBuilder("Fila " + (i + 1) + ": ");
                String selectedValue = spinners[i][0].getSelectedItem().toString();
                rowResult.append(selectedValue).append(" ");
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
