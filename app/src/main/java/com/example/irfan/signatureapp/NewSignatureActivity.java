package com.example.irfan.signatureapp;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.kyanogen.signatureview.SignatureView;
import java.io.File;
import java.io.FileOutputStream;

public class NewSignatureActivity extends AppCompatActivity {

    SignatureView signatureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signature);

        signatureView = (SignatureView) findViewById(R.id.signatureView);

    }

    public void clearSignature(View v){
        signatureView.clearCanvas();
    }

    public void saveSignature(View v){
        Bitmap img = signatureView.getSignatureBitmap();
        String root = Environment.getExternalStorageDirectory().toString();
        File dir = new File(root + "/saved_signature");

        if(!dir.exists()){
            dir.mkdirs();
        }

        String filename = "mysignature.png";
        File file = new File (dir, filename);

        if (file.exists ()) file.delete ();

        try {
            FileOutputStream out = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Your image is saved to this folder", Toast.LENGTH_LONG).show();
        signatureView.clearCanvas();
        Log.e("TAG",root + "/saved_signature");
        finish();
    }



}
