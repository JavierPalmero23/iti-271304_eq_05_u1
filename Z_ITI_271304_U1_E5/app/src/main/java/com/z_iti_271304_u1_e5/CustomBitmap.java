package com.z_iti_271304_u1_e5;

import android.graphics.Bitmap;
import android.graphics.Paint;

public abstract class CustomBitmap {
    private Paint paint;

    public CustomBitmap() {
        paint.setColor(android.graphics.Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    public CustomBitmap(Paint paint) {
        this.paint = paint;
    }

    public CustomBitmap(int color, float strokeWidth, Paint.Style style) {
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
    }

    public Paint getPaint() {
        return paint;
    }

    /**
     * Define la lógica del bitmap
     * @param width Ancho del bitmap
     * @param height Alto del bitmap
     * @return Bitmap
     */
    public abstract Bitmap getBitmap(int width, int height);
}
