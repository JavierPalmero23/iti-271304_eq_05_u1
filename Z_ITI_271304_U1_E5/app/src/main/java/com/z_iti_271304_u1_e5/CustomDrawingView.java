package com.z_iti_271304_u1_e5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomDrawingView extends View {
    private Paint paint;
    private ArrayList<String[]> gateLabels = new ArrayList<>();

    static final int GATE_WIDTH = 400;
    static final int GATE_HEIGHT = 200;
    static int CONTEXT_WIDTH;
    static int CONTEXT_HEIGHT;
    private int gateCount;

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

    public void setGateLabels(ArrayList<String[]> labels) {
        this.gateLabels = labels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Calcular el ancho requerido basado en el tamaño de las compuertas y el espacio necesario
        int desiredWidth = (int) (GATE_WIDTH * 2 + 300); // Espacio horizontal suficiente para compuertas AND y OR
        // Calcular la altura basada en la cantidad de compuertas y el espaciado entre ellas
        int desiredHeight = (GATE_HEIGHT + 100) * gateCount + 700; // 200 de margen adicional para asegurar espacio extra

        // Ajustar el ancho y alto de la vista para expandirse según el contenido real
        int width = resolveSize(desiredWidth, widthMeasureSpec);
        int height = resolveSize(desiredHeight, heightMeasureSpec);

        setMeasuredDimension(width, height);
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
        float initialY = (height - ((GATE_HEIGHT + gateSpacing) * (gateCount - 1))) / 2f; // Para centrar verticalmente

        // Variables para guardar las posiciones previas de las compuertas
        float prevXPos = 0;
        float prevYPos = 0;
        boolean prevIsLeft = false;

        for (int i = 0; i < gateCount; i++) {
            // Alternar la posición X para las compuertas AND y definir la posición de la compuerta OR
            float xPos;
            float yPos;
            boolean isLeft;

            if (i < gateCount - 1) {
                // Compuertas AND en la izquierda
                xPos = width * 0.1f; // 10% del ancho de la pantalla
                yPos = initialY + i * (GATE_HEIGHT + gateSpacing);
                isLeft = true;
            } else {
                // Compuerta OR en la derecha
                xPos = width * 0.9f - GATE_WIDTH; // 90% del ancho menos el ancho de la compuerta
                yPos = (initialY + (gateCount - 2) * (GATE_HEIGHT + gateSpacing)) / 2f; // Posicionar en el centro de las compuertas AND
                isLeft = false;
            }

            // Alternar colores: azul para compuertas AND, rojo para OR (última compuerta)
            Paint gatePaint = new Paint(paint); // Crear una copia del Paint original
            String[] labels = gateLabels.size() > i ? gateLabels.get(i) : new String[0];
            if (i < gateCount - 1) {
                gatePaint.setColor(android.graphics.Color.BLUE); // Color para AND
            } else {
                gatePaint.setColor(android.graphics.Color.RED); // Color para OR
            }

            // Crear la compuerta con el paint modificado y etiquetas si corresponde
            GateBitmap gateBitmap = new GateBitmap(gatePaint);
            canvas.drawBitmap(gateBitmap.getBitmap(GATE_WIDTH, GATE_HEIGHT, labels), xPos, yPos, gatePaint);

            // Dibujar la línea de conexión si no es la primera compuerta
            if (i > 0) {
                Paint linePaint = new Paint();
                linePaint.setColor(android.graphics.Color.BLACK);
                linePaint.setStrokeWidth(5);

                // Punto de inicio: salida de la compuerta AND anterior
                float startX = prevIsLeft ? prevXPos + GATE_WIDTH : prevXPos;
                float startY = prevYPos + GATE_HEIGHT / 2; // Posición Y del punto de inicio

                // Punto de fin: entrada de la compuerta OR o la siguiente compuerta AND
                float endX = isLeft ? xPos + GATE_WIDTH : xPos;
                float endY = yPos + GATE_HEIGHT / 2;

                // Dibujar la línea
                canvas.drawLine(startX, startY, endX, endY, linePaint);
            }

            // Conectar todas las salidas de las compuertas AND a la compuerta OR si estamos en la última iteración
            if (i == gateCount - 1) {
                for (int j = 0; j < gateCount - 1; j++) {
                    float andX = width * 0.1f + GATE_WIDTH; // Salida de cada compuerta AND
                    float andY = initialY + j * (GATE_HEIGHT + gateSpacing) + GATE_HEIGHT / 2;

                    Paint linePaint = new Paint();
                    linePaint.setColor(android.graphics.Color.BLACK);
                    linePaint.setStrokeWidth(5);

                    // Dibujar la línea desde cada compuerta AND a la entrada de la compuerta OR
                    canvas.drawLine(andX, andY, xPos, yPos + GATE_HEIGHT / 2, linePaint);
                }
            }

            // Actualizar las posiciones previas
            prevXPos = xPos;
            prevYPos = yPos;
            prevIsLeft = isLeft;
        }
    }
}
