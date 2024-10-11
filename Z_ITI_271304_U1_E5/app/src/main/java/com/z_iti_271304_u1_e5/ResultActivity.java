package com.z_iti_271304_u1_e5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        // Calcular el número de compuertas AND y OR basado en los tokens
        int totalOrGates = tokens.length - 1;
        int totalAndGates = 0;
        for (String token : tokens) {
            token = token.trim();
            String[] variables = token.split("(?=[A-Z])");
            totalAndGates += variables.length - 1;
        }

        // Inicializar la vista de dibujo
        customDrawingView = findViewById(R.id.customDrawingView);

        // Establecer el número de compuertas (AND y OR combinadas)
        customDrawingView.setGateCount(totalAndGates + totalOrGates);

        // Dibujar las compuertas en la vista
        customDrawingView.invalidate(); // Forzar el redibujado de la vista
    }
}
