package com.z_iti_271304_u1_e5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Recuperar el intent y obtener la matriz
        Intent intent = getIntent();
        ArrayList<int[]> resultMatrixList = (ArrayList<int[]>) intent.getSerializableExtra("resultMatrix");

        // Convertir de nuevo la lista a matriz de dos dimensiones
        int[][] resultMatrix = new int[resultMatrixList.size()][];
        for (int i = 0; i < resultMatrixList.size(); i++) {
            resultMatrix[i] = resultMatrixList.get(i);
        }

        Log.d("RESULT_MATRIX_RECEIVED", java.util.Arrays.deepToString(resultMatrix));
    }
}
