package com.example.irfan.signatureapp;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.model.PagePart;
import java.io.File;

public class PdfEditorActivity extends AppCompatActivity{

    String pdfname;
    Bitmap signatureBitmap;
    PDFView pdfView;
    OnDrawListener drawListener;
    Canvas gCanvas;
    PagePart pagePart;

    private long then;
    private int longClickDuration = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_editor);

        pdfView = (PDFView) findViewById(R.id.pdfView);

        Intent intent = getIntent();
        pdfname = intent.getStringExtra("filename");

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPdfFile(pdfname);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.pdf_editor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.menu_add_signature){
            drawSignature();
        }
        return true;
    }

    void loadPdfFile(String path){
        File file = new File(path);
        pdfView.fromFile(file)
                .defaultPage(0)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                        String signaturePath = Environment.getExternalStorageDirectory().toString() + "/saved_signature/mysignature.png";
                        signatureBitmap = BitmapFactory.decodeFile(signaturePath);

                        Matrix m = new Matrix();
                        m.setTranslate(pageWidth/2, pageWidth/2);
                        canvas.drawBitmap(signatureBitmap,m,null);
                    }
                })
                .load();

    }

    void drawSignature(){
        final String signaturePath = Environment.getExternalStorageDirectory().toString() + "/saved_signature/mysignature.png";
        signatureBitmap = BitmapFactory.decodeFile(signaturePath);

        Toast.makeText(this,"INI HAHAHA",Toast.LENGTH_SHORT).show();

    }


}
