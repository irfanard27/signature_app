package com.example.irfan.signatureapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.net.URI;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final int FILE_PICKER_REQUEST_CODE = 1;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.menu_add_pdf){
            openFilePicker();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Intent intent = new Intent(this,PdfEditorActivity.class);
            intent.putExtra("filename",path);
            startActivity(intent);
        }
    }

    public void toNewSignatureActivity(View v){
        Intent intent = new Intent(this,NewSignatureActivity.class);
        startActivity(intent);
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withTitle("Sample title")
                .start();
    }

}
