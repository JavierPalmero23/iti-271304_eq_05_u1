package com.z_iti_271304_u1_e5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class CustomDrawingView extends View {
    private Paint paint;

    static final int GATE_WIDTH = 400;
    static final int GATE_HEIGHT = 200;
    static int CONTEXT_WIDTH;
    static int CONTEXT_HEIGHT;
    private int gateCount;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Calcular el tamaño del contenido basado en la cantidad de compuertas y el espaciado
        int desiredWidth = CONTEXT_WIDTH;
        int desiredHeight = (GATE_HEIGHT + 100) * gateCount; // Espacio total necesario

        // Asegurar que el ancho y alto de la vista se ajusten al contenido
        int width = resolveSize(desiredWidth, widthMeasureSpec);
        int height = resolveSize(desiredHeight, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    public CustomDrawingView(Context context) {
        super(context);
        init(context);
    }

    public CustomDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setGateCount(int gateCount) {
        this.gateCount = gateCount;
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(android.graphics.Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        CONTEXT_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
        CONTEXT_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        this.drawGates(canvas, this.gateCount);
    }

    private void drawGates(Canvas canvas, int gateCount) {
        int width = CONTEXT_WIDTH;
        int height = CONTEXT_HEIGHT;

        int gateSpacing = 100;
        float initialY = (height - ((GATE_HEIGHT + gateSpacing) * gateCount - gateSpacing)) / 2f; // Para centrar verticalmente

        // Variables para guardar las posiciones previas de las compuertas
        float prevXPos = 0;
        float prevYPos = 0;
        boolean prevIsLeft = false;

        for (int i = 0; i < gateCount; i++) {
            // Alternar la posición X para las compuertas
            float xPos;
            boolean isLeft;
            if (i % 2 == 0) {
                // Compuertas en la izquierda
                xPos = width * 0.1f; // 10% del ancho de la pantalla
                isLeft = true;
            } else {
                // Compuertas en la derecha
                xPos = width * 0.9f - GATE_WIDTH; // 90% del ancho menos el ancho de la compuerta
                isLeft = false;
            }

            // Calcular la posición Y de la compuerta actual
            float yPos = initialY + i * (GATE_HEIGHT + gateSpacing);

            // Alternar colores para las compuertas AND y OR
            Paint gatePaint = new Paint(paint); // Crear una copia del Paint original
            if (i % 2 == 0) {
                gatePaint.setColor(android.graphics.Color.BLUE); // Color para AND
            } else {
                gatePaint.setColor(android.graphics.Color.RED); // Color para OR
            }

            // Crear la compuerta con el paint modificado
            ANDGateBitmap gateBitmap = new ANDGateBitmap(gatePaint);
            canvas.drawBitmap(gateBitmap.getBitmap(GATE_WIDTH, GATE_HEIGHT), xPos, yPos, gatePaint);

            // Dibujar la línea de conexión si no es la primera compuerta
            if (i > 0) {
                Paint linePaint = new Paint();
                linePaint.setColor(android.graphics.Color.BLACK);
                linePaint.setStrokeWidth(5);

                // Punto de inicio: lado izquierdo de la compuerta anterior
                float startX = prevIsLeft ? prevXPos + GATE_WIDTH : prevXPos;
                float startY = prevYPos + GATE_HEIGHT / 2; // Posición Y del punto de inicio

                // Punto de fin: lado izquierdo de la compuerta actual
                float endX = isLeft ? xPos + GATE_WIDTH : xPos;
                float endY = yPos + GATE_HEIGHT / 2;

                // Dibujar la línea
                canvas.drawLine(startX, startY, endX, endY, linePaint);
            }

            // Actualizar las posiciones previas
            prevXPos = xPos;
            prevYPos = yPos;
            prevIsLeft = isLeft;
        }
    }
}