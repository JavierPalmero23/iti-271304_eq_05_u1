package com.z_iti_271304_u1_e5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    CustomDrawingView customDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Obtener el Intent y la expresión booleana
        Intent intent = getIntent();
        String booleanExpression = intent.getStringExtra("BOOLEAN_EXPRESSION");
        Log.d("ResultActivity", "Boolean expression: " + booleanExpression);

        // Eliminar "y = " de la expresión para quedarnos solo con la parte booleana
        if (booleanExpression != null && booleanExpression.startsWith("y = ")) {
            booleanExpression = booleanExpression.substring(4); // Elimina "y = "
        }

        // Tokenizar la expresión por el operador '+'
        String[] tokens = booleanExpression.split("\\+");

        // Inicializar la vista de dibujo
        customDrawingView = findViewById(R.id.customDrawingView);

        // Crear etiquetas para las compuertas AND (una por cada término)
        ArrayList<String[]> labels = new ArrayList<>();
        for (String token : tokens) {
            token = token.trim();
            // Dividir el término en variables (considerando las negaciones)
            String[] variables = token.split("(?=[A-Z]'?)"); // Expresión regular para capturar variables con y sin apóstrofo
            labels.add(variables); // Añadir las variables que pertenecen a cada compuerta AND
        }

        // Añadir un array vacío para la compuerta OR, ya que no necesita etiquetas
        labels.add(new String[0]);

        // Establecer el número de compuertas (AND y OR combinadas)
        customDrawingView.setGateCount(tokens.length + 1); // AND gates + 1 OR gate

        // Establecer las etiquetas para cada compuerta
        customDrawingView.setGateLabels(labels);

        // Dibujar las compuertas en la vista
        customDrawingView.invalidate(); // Forzar el redibujado de la vista
    }
}
