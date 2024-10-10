package com.z_iti_271304_u1_e5;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
            int numVariables = Integer.parseInt(etNumVariables.getText().toString());

            // Dimensiones de la matriz
            int numRows = radioGroups.length;
            int[][] selectedValues = new int[numRows][1];

            // Obtener valores seleccionados
            for (int i = 0; i < numRows; i++) {
                RadioGroup radioGroup = radioGroups[i][0];
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = findViewById(selectedId);
                String selectedText = selectedButton.getText().toString();

                // Asignar 0, 1, o -1 dependiendo de la selección (X será -1)
                selectedValues[i][0] = selectedText.equals("X") ? -1 : Integer.parseInt(selectedText);
            }

            generateKMap(numVariables, selectedValues);

            /*// Convertir la matriz a una lista
            ArrayList<int[]> resultMatrixList = new ArrayList<>(Arrays.asList(resultMatrix));

            // Enviar la matriz a una nueva actividad
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("resultMatrix", resultMatrixList);
            startActivity(intent);
            Log.d("RESULT_MATRIX", java.util.Arrays.deepToString(resultMatrix));*/
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

    private void generateKMap(int numVariables, int[][] selectedValues) {
        // Determinar dimensiones del K-map
        int numRows;
        int numCols;

        switch (numVariables) {
            case 2:
                numRows = 2;
                numCols = 2;
                break;
            case 3:
                numRows = 2;
                numCols = 4;
                break;
            case 4:
                numRows = 4;
                numCols = 4;
                break;
            case 5:
                numRows = 4;
                numCols = 8;
                break;
            case 6:
                numRows = 8;
                numCols = 8;
                break;
            case 7:
                numRows = 8;
                numCols = 16;
                break;
            default:
                Toast.makeText(this, "Número de variables no soportado", Toast.LENGTH_SHORT).show();
                return;
        }

        TableLayout kMapLayout = new TableLayout(this);
        kMapLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        // Crear fila de encabezado para las columnas
        TableRow headerRow = new TableRow(this);
        headerRow.addView(new TextView(this)); // Espacio para la esquina superior izquierda

        // Agregar encabezados de columna
        for (int j = 0; j < numCols; j++) {
            TextView headerCell = new TextView(this);
            headerCell.setText(getColumnHeader(j, numVariables)); // Obtener encabezado de columna
            headerCell.setPadding(16, 16, 16, 16);
            headerCell.setBackgroundResource(android.R.drawable.btn_default);
            headerRow.addView(headerCell);
        }
        kMapLayout.addView(headerRow);

        // Crear filas del K-map
        for (int i = 0; i < numRows; i++) {
            TableRow kMapRow = new TableRow(this);

            // Añadir etiqueta a la izquierda para cada fila
            TextView rowLabel = new TextView(this);
            rowLabel.setText(getRowHeader(i, numVariables)); // Obtener encabezado de fila
            rowLabel.setPadding(16, 16, 16, 16);
            rowLabel.setBackgroundResource(android.R.drawable.btn_default);
            kMapRow.addView(rowLabel);

            for (int j = 0; j < numCols; j++) {
                TextView kMapCell = new TextView(this);
                int truthValue = getTruthValue(i, j, numVariables, selectedValues);
                String displayValue = truthValue == -1 ? "X" : String.valueOf(truthValue);
                kMapCell.setText(displayValue);
                kMapCell.setPadding(16, 16, 16, 16);
                kMapCell.setBackgroundResource(android.R.drawable.btn_default);
                kMapCell.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                kMapRow.addView(kMapCell);
            }
            kMapLayout.addView(kMapRow);
        }

        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.removeAllViews();
        mainLayout.addView(kMapLayout);
    }

    // Obtener encabezado de columna
    private String getColumnHeader(int index, int numVariables) {
        int gray = grayCode(index);

        int colBits = numVariables / 2 + (numVariables % 2); // Variables para columnas

        StringBuilder header = new StringBuilder();

        // Generar encabezados de columna basados en las variables (poner negadas primero)
        for (int i = colBits - 1; i >= 0; i--) {
            char variable = (char) ('A' + (numVariables - colBits + (colBits - i - 1)));
            if ((gray & (1 << i)) == 0) {
                header.append(variable).append("'"); // Variable negada primero
            } else {
                header.append(variable); // Variable positiva
            }
        }
        return header.toString();
    }

    // Obtener encabezado de fila
    private String getRowHeader(int index, int numVariables) {
        int gray = grayCode(index);

        int rowBits = numVariables / 2; // Variables para filas

        StringBuilder header = new StringBuilder();

        // Generar encabezados de fila basados en las variables (poner negadas primero)
        for (int i = rowBits - 1; i >= 0; i--) {
            char variable = (char) ('A' + i);
            if ((gray & (1 << i)) == 0) {
                header.append(variable).append("'"); // Variable negada primero
            } else {
                header.append(variable); // Variable positiva
            }
        }
        return header.toString();
    }

    private int getTruthValue(int row, int col, int numVariables, int[][] selectedValues) {
        int numRowVariables = numVariables / 2;
        int numColVariables = numVariables - numRowVariables;

        // Obtener códigos Gray para filas y columnas
        int rowGray = grayCode(row);
        int colGray = grayCode(col);

        // Combinar las partes de fila y columna
        int combinedCode = (rowGray << numColVariables) | colGray;

        // Validar que el índice combinado esté dentro del rango
        if (combinedCode >= 0 && combinedCode < selectedValues.length) {
            return selectedValues[combinedCode][0]; // Obtener el valor del K-map
        }
        return 0; // Valor por defecto
    }


    // Función para obtener el código Gray de un número
    // Función para obtener el código Gray de un número (ajuste correcto)
    private int grayCode(int num) {
        return num ^ (num >> 1);  // Solo un bit de desplazamiento
    }
}