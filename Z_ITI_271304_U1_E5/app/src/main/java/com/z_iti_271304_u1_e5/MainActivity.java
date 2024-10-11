package com.z_iti_271304_u1_e5;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.util.Log;
import android.widget.Space;
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

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup[][] radioGroups;
    private List<Group> groups = new ArrayList<>();
    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};
    TextView simplificationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        Button btnGenerate = findViewById(R.id.btnGenerate);
        EditText etNumVariables = findViewById(R.id.etNumVariables);
        simplificationTextView = findViewById(R.id.simplificationTextView);
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

            // Prueba para verificar si el scroll funciona correctamente con 7 variables
            if (numVariables == 7) {
                Toast.makeText(this, "Desplazamiento habilitado para 7 variables", Toast.LENGTH_SHORT).show();
            }

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

            simplificationTextView = findViewById(R.id.simplificationTextView);
            simplificationTextView.setText("");

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

    // Método para generar la expresión booleana (Suma de Productos)
    private String generateBooleanExpression(int[][] selectedValues, int numVariables) {
        StringBuilder expression = new StringBuilder();

        // Iterar sobre las celdas seleccionadas
        for (int i = 0; i < selectedValues.length; i++) {
            if (selectedValues[i][0] == 1) { // Solo considerar los valores de "1"
                StringBuilder term = new StringBuilder();

                // Obtener las variables de fila y columna
                int rowBits = numVariables / 2;
                int colBits = numVariables - rowBits;

                // Variables de fila
                for (int j = rowBits - 1; j >= 0; j--) {
                    char variable = (char) ('A' + j);
                    if ((i & (1 << j)) == 0) {
                        term.append(variable).append("'"); // Variable negada
                    } else {
                        term.append(variable); // Variable positiva
                    }
                }

                // Variables de columna
                for (int j = colBits - 1; j >= 0; j--) {
                    char variable = (char) ('A' + (rowBits + j));
                    if ((i & (1 << j)) == 0) {
                        term.append(variable).append("'"); // Variable negada
                    } else {
                        term.append(variable); // Variable positiva
                    }
                }

                // Agregar el término a la expresión completa
                if (expression.length() > 0) {
                    expression.append(" + ");
                }
                expression.append(term.toString());
            }
        }

        return expression.toString().isEmpty() ? "0" : expression.toString(); // Retorna la expresión
    }

    private void simplifyKMap(TableLayout kMapLayout, int numRows, int numCols, int numVariables, int[][] selectedValues) {
        // En lugar de borrar grupos, crearemos una nueva lista para esta simplificación
        List<Group> newGroups = new ArrayList<>();
        findGroups(numRows, numCols, numVariables, selectedValues, newGroups);

        // Agrega los nuevos grupos a los grupos existentes
        groups.addAll(newGroups);

        drawGroups(kMapLayout, numRows, numCols);
        displaySimplifiedExpression(numVariables);
    }

    private void findGroups(int numRows, int numCols, int numVariables, int[][] selectedValues, List<Group> newGroups) {
        boolean[][] covered = new boolean[numRows][numCols];


        // Buscar grupos de tamaño 8 (si es posible)
        if (numRows * numCols >= 8) {
            findGroupsOfSize(numRows, numCols, numVariables, selectedValues, covered, 8);
        }

        // Buscar grupos de tamaño 4
        findGroupsOfSize(numRows, numCols, numVariables, selectedValues, covered, 4);

        // Buscar grupos de tamaño 2
        findGroupsOfSize(numRows, numCols, numVariables, selectedValues, covered, 2);

        // Buscar grupos de tamaño 1 (celdas individuales)
        findGroupsOfSize(numRows, numCols, numVariables, selectedValues, covered, 1);
    }

    private void findGroupsOfSize(int numRows, int numCols, int numVariables, int[][] selectedValues, boolean[][] covered, int groupSize) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (!covered[i][j] && isValidGroup(i, j, numRows, numCols, numVariables, selectedValues, groupSize)) {
                    addGroup(i, j, numRows, numCols, groupSize);
                    markAsCovered(i, j, numRows, numCols, covered, groupSize);
                }
            }
        }
    }

    private boolean isValidGroup(int startRow, int startCol, int numRows, int numCols, int numVariables, int[][] selectedValues, int groupSize) {
        int height = (groupSize == 8) ? 4 : (groupSize == 4) ? 2 : 1;
        int width = groupSize / height;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int row = (startRow + i) % numRows;
                int col = (startCol + j) % numCols;
                if (getTruthValue(row, col, numVariables, selectedValues) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void addGroup(int startRow, int startCol, int numRows, int numCols, int groupSize) {
        int height = (groupSize == 8) ? 4 : (groupSize == 4) ? 2 : 1;
        int width = groupSize / height;
        groups.add(new Group(startRow, startCol, width, height));
    }

    private void markAsCovered(int startRow, int startCol, int numRows, int numCols, boolean[][] covered, int groupSize) {
        int height = (groupSize == 8) ? 4 : (groupSize == 4) ? 2 : 1;
        int width = groupSize / height;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int row = (startRow + i) % numRows;
                int col = (startCol + j) % numCols;
                covered[row][col] = true;
            }
        }
    }
    private void drawGroups(TableLayout kMapLayout, int numRows, int numCols) {
        // Borrar fondos existentes
        for (int i = 1; i <= numRows; i++) {
            TableRow row = (TableRow) kMapLayout.getChildAt(i);
            for (int j = 1; j <= numCols; j++) {
                View cell = row.getChildAt(j);
                cell.setBackground(null);
            }
        }

        //dibujar todos los grupos
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            int color = colors[i % colors.length];

            for (int row = 0; row < group.height; row++) {
                for (int col = 0; col < group.width; col++) {
                    int actualRow = (group.startRow + row) % numRows;
                    int actualCol = (group.startCol + col) % numCols;

                    TableRow tableRow = (TableRow) kMapLayout.getChildAt(actualRow + 1);
                    View cell = tableRow.getChildAt(actualCol + 1);

                    addBorderToCell(cell, color, row, col, group.height, group.width);
                }
            }
        }
    }

    private void addBorderToCell(View cell, int color, int row, int col, int height, int width) {
        GradientDrawable newBorder = new GradientDrawable();
        newBorder.setStroke(2, color);
        float[] radii = new float[8];
        if (row == 0 && col == 0) radii[0] = radii[1] = 8f;
        if (row == 0 && col == width - 1) radii[2] = radii[3] = 8f;
        if (row == height - 1 && col == 0) radii[6] = radii[7] = 8f;
        if (row == height - 1 && col == width - 1) radii[4] = radii[5] = 8f;
        newBorder.setCornerRadii(radii);

        if (cell.getBackground() != null) {
            android.graphics.drawable.Drawable[] layers = {cell.getBackground(), newBorder};
            android.graphics.drawable.LayerDrawable layerDrawable = new android.graphics.drawable.LayerDrawable(layers);
            cell.setBackground(layerDrawable);
        } else {
            cell.setBackground(newBorder);
        }
    }
    private void displaySimplifiedExpression(int numVariables) {
        StringBuilder expression = new StringBuilder();
        for (Group group : groups) {
            expression.append(getTermForGroup(group, numVariables)).append(" + ");
        }
        if (expression.length() > 0) {
            expression.setLength(expression.length() - 3);
        }

        simplificationTextView.setText("y = " + expression);
    }

    private String getTermForGroup(Group group, int numVariables) {
        StringBuilder term = new StringBuilder();
        boolean[] dontCare = new boolean[numVariables];

        //Determinar qué variables no interesan a este grupo
        for (int i = 0; i < numVariables; i++) {
            int size = (i < numVariables / 2) ? group.height : group.width;
            dontCare[i] = (size == 2 || size == 4);
        }

        // construir el término
        for (int i = 0; i < numVariables; i++) {
            if (!dontCare[i]) {
                char variable = (char) ('A' + i);
                boolean isNegated = (i < numVariables / 2) ?
                        (group.startRow & (1 << (numVariables / 2 - 1 - i))) == 0 :
                        (group.startCol & (1 << (numVariables - 1 - i))) == 0;
                if (isNegated) {
                    term.append(variable).append("'");
                } else {
                    term.append(variable);
                }
            }
        }

        return term.length() > 0 ? term.toString() : "1";
    }

    private static class Group {
        int startRow, startCol, width, height;

        Group(int startRow, int startCol, int width, int height) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.width = width;
            this.height = height;
        }
    }

    private void generateKMap(int numVariables, int[][] selectedValues) {
        // Validación de número de variables (se asume de 2 a 7 variables)
        if (numVariables < 2 || numVariables > 7) {
            Toast.makeText(this, "Número de variables no soportado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Determinar dimensiones del K-map según el número de variables
        int numRows;
        int numCols;

        if (numVariables % 2 == 0) {
            numRows = (int) Math.pow(2, numVariables / 2); // Variables para las filas
            numCols = (int) Math.pow(2, numVariables / 2); // Variables para las columnas
        } else {
            numRows = (int) Math.pow(2, numVariables / 2); // Variables para las filas
            numCols = (int) Math.pow(2, (numVariables / 2) + 1); // Variables para las columnas
        }

        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.removeAllViews();

        // Crear el mapa de Karnaugh (Map)
        TableLayout kMapLayout = createKMapTable(numRows, numCols, numVariables, selectedValues, false);

        // Crear el layout del mapa (Map Layout) - mostrando índices
        TableLayout mapLayoutTable = createKMapTable(numRows, numCols, numVariables, selectedValues, true);

        // Añadir ambos mapas al layout principal
        mainLayout.addView(kMapLayout);

        // Espacio entre ambos mapas
        Space space = new Space(this);
        space.setMinimumHeight(50); // Ajusta la altura del espacio si es necesario
        mainLayout.addView(space);
        simplifyKMap(kMapLayout, numRows, numCols, numVariables, selectedValues);
        
        // Añadir layout del mapa
        mainLayout.addView(mapLayoutTable);

        /*
        String booleanExpression = generateBooleanExpression(selectedValues, numVariables);

        // Mostrar la expresión en pantalla
        TextView expressionTextView = new TextView(this);
        expressionTextView.setText("y = " + booleanExpression);
        expressionTextView.setTextSize(18);
        expressionTextView.setTypeface(null, Typeface.BOLD);
        expressionTextView.setPadding(16, 16, 16, 16);
        mainLayout.addView(expressionTextView);*/

        // Crear botón para mostrar el resultado en una nueva actividad
        Button resultButton = new Button(this);
        resultButton.setText("Mostrar resultado");
        resultButton.setPadding(16, 16, 16, 16);

        // Configurar el evento onClick para el botón
        resultButton.setOnClickListener(new View.OnClickListener() {
            String booleanExpression = simplificationTextView.getText().toString();

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("BOOLEAN_EXPRESSION", booleanExpression);
                startActivity(intent);
            }
        });

        // Añadir el botón al layout principal
        mainLayout.addView(resultButton);
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
    private int grayCode(int num) {
        return num ^ (num >> 1);  // Solo un bit de desplazamiento
    }

    // Método para crear la tabla del mapa de Karnaugh o el layout del mapa
    private TableLayout createKMapTable(int numRows, int numCols, int numVariables, int[][] selectedValues, boolean isMapLayout) {
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        // Crear fila de encabezado para las columnas
        TableRow headerRow = new TableRow(this);
        headerRow.addView(new TextView(this)); // Espacio para la esquina superior izquierda

        // Agregar encabezados de columna (Ejemplo: B'C', BC', etc.)
        for (int j = 0; j < numCols; j++) {
            TextView headerCell = new TextView(this);
            headerCell.setText(getColumnHeader(j, numVariables)); // Obtener encabezado de columna
            headerCell.setPadding(16, 16, 16, 16);
            headerRow.addView(headerCell);
        }
        tableLayout.addView(headerRow);

        // Crear filas del mapa
        for (int i = 0; i < numRows; i++) {
            TableRow kMapRow = new TableRow(this);

            // Añadir etiqueta a la izquierda para cada fila (Ejemplo: A', A, etc.)
            TextView rowLabel = new TextView(this);
            rowLabel.setText(getRowHeader(i, numVariables)); // Obtener encabezado de fila
            rowLabel.setPadding(16, 16, 16, 16);
            kMapRow.addView(rowLabel);

            // Agregar celdas con valores
            for (int j = 0; j < numCols; j++) {
                TextView kMapCell = new TextView(this);

                if (isMapLayout) {
                    // Mostrar el índice binario/gray correspondiente
                    int index = getMapLayoutIndex(i, j, numVariables);
                    kMapCell.setText(String.valueOf(index));
                } else {
                    // Mostrar los valores seleccionados por el usuario (0, 1 o X)
                    int truthValue = getTruthValue(i, j, numVariables, selectedValues);
                    String displayValue = truthValue == -1 ? "X" : String.valueOf(truthValue);
                    kMapCell.setText(displayValue);
                }

                kMapCell.setPadding(16, 16, 16, 16);
                kMapCell.setBackgroundResource(android.R.drawable.btn_default);
                kMapRow.addView(kMapCell);
            }

            tableLayout.addView(kMapRow);
        }

        return tableLayout;
    }

    // Método para obtener el índice del layout del mapa
    private int getMapLayoutIndex(int row, int col, int numVariables) {
        // Generar el índice correspondiente a la celda (usa códigos Gray si es necesario)
        return row * (int) Math.pow(2, (numVariables / 2) + (numVariables % 2)) + col;
    }


}