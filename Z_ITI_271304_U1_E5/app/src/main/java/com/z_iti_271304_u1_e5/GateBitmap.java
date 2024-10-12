package com.z_iti_271304_u1_e5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class GateBitmap extends CustomBitmap {

    public GateBitmap() {
        super();
    }

    public GateBitmap(Paint paint) {
        super(paint);
    }

    public GateBitmap(int color, float strokeWidth, Paint.Style style) {
        super(color, strokeWidth, style);
    }

    @Override
    public Bitmap getBitmap(int width, int height, String[] labels) {
        // Ajustar el alto del Bitmap para incluir espacio para el texto debajo
        int textHeight = 50; // Altura estimada para el texto
        int totalHeight = height + textHeight; // Altura total incluyendo el texto

        Bitmap bitmap = Bitmap.createBitmap(width, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Usar el Paint desde CustomBitmap
        Paint paint = getPaint();
        paint.setAntiAlias(true);

        int halfWidth = width / 2;
        // Dibujar el rectángulo
        canvas.drawRect(0, 0, halfWidth + ((float) width / 4 / 2), height, paint);

        // Dibujar el arco
        RectF oval = new RectF(halfWidth - width / 4, 0, width, height);
        canvas.drawArc(oval, 270, 180, true, paint);

        // Configurar el Paint para el texto
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);

        // Concatenar las etiquetas con comas
        StringBuilder labelsText = new StringBuilder();
        for (int i = 0; i < labels.length; i++) {
            labelsText.append(labels[i]);
            if (i < labels.length - 1) {
                labelsText.append(", "); // Añadir coma y espacio entre etiquetas
            }
        }

        // Dibujar el texto debajo del Bitmap
        canvas.drawText(labelsText.toString(), 10, height + 40, textPaint);

        return bitmap;
    }
}
