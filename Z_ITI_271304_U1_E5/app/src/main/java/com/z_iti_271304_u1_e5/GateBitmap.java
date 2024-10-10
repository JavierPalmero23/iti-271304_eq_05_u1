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
    public Bitmap getBitmap(int width, int height) {
        int adjustment = width / 4;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Usar el Paint desde CustomBitmap
        Paint paint = getPaint();
        paint.setAntiAlias(true);

        int halfWidth = width / 2;
        canvas.drawRect(0, 0, halfWidth + ((float) adjustment / 2), height, paint);

        RectF oval = new RectF(halfWidth - adjustment, 0, width, height);
        canvas.drawArc(oval, 270, 180, true, paint);

        return bitmap;
    }
}