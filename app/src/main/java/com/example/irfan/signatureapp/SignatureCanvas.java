package com.example.irfan.signatureapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;

/**
 * Created by Irfan on 08/01/18.
 */

public class SignatureCanvas extends PDFView {

    Bitmap signatureBitmap;
    final String signaturePath = Environment.getExternalStorageDirectory().toString() + "/saved_signature/mysignature.png";

    /**
     * Construct the initial view
     *
     * @param context
     * @param set
     */
    public SignatureCanvas(Context context, AttributeSet set) {
        super(context, set);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(10,20,40,50,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.d("TAG",""+event.getX());
        }
        return true;

    }
}
