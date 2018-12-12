package com.example.utsav.qrmul;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.List;
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class MainActivity extends AppCompatActivity implements BarcodeRetriever {

    public static final int barcode=0x7f090025;
    private static final String TAG = "BarcodeMain";
    String s1;
    CheckBox fromXMl, pause;
    SwitchCompat autoFocus, supportMultiple, flash, frontCam,drawRect, drawText;
    BarcodeCapture barcodeCapture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(barcode);
        barcodeCapture.setRetrieval(this);
        drawRect = (SwitchCompat) findViewById(R.id.draw_rect);
        autoFocus = (SwitchCompat) findViewById(R.id.focus);
        supportMultiple = (SwitchCompat) findViewById(R.id.support_multiple);
        flash = (SwitchCompat) findViewById(R.id.on_flash);
        frontCam = (SwitchCompat) findViewById(R.id.front_cam);
        drawText = (SwitchCompat) findViewById(R.id.draw_text);

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    barcodeCapture.setShowDrawRect(false);
                    barcodeCapture.setSupportMultipleScan(false);
                    barcodeCapture.setShowFlash(false);
                    barcodeCapture.shouldAutoFocus(false);
                    barcodeCapture.setShouldShowText(false);
                    barcodeCapture.setCameraFacing(0);
                    barcodeCapture.stopScanning();
                    barcodeCapture.refresh(true);
onBackPressed();
            }
        });
        frontCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcodeCapture .setCameraFacing(frontCam.isChecked() ? CameraSource.CAMERA_FACING_FRONT : CameraSource.CAMERA_FACING_BACK);
                barcodeCapture.refresh(true);
            }
        });
        drawRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawRect.isChecked())
                    barcodeCapture.setShowDrawRect(true);
                    else
                    barcodeCapture.setShowDrawRect(false);
                barcodeCapture.refresh(true);
            }
        });
        supportMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (supportMultiple.isChecked())
                    barcodeCapture.setSupportMultipleScan(true);
                    else
                    barcodeCapture.setSupportMultipleScan(false);
                barcodeCapture.refresh(true);

            }
        });
        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flash.isChecked())
                    barcodeCapture.setShowFlash(true);
                    else
                    barcodeCapture.setShowFlash(false);
                barcodeCapture.refresh(true);
               // barcodeCapture.refresh(true);
            }
        });
        autoFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoFocus.isChecked())
                    barcodeCapture.shouldAutoFocus(true);
                    else
                    barcodeCapture.shouldAutoFocus(false);
                barcodeCapture.refresh(true);
            }
        });
        drawText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawText.isChecked())
                    barcodeCapture.setShouldShowText(true);
                    else
                    barcodeCapture.setShouldShowText(false);
                barcodeCapture.refresh(true);
            }
        });
               findViewById(R.id.retrive).setOnClickListener(new View.OnClickListener() {
                    @Override
               public void onClick(View v) {
                   startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    }
                 });
    }


    @Override
    public void onRetrieved(final Barcode barcode) {

           }

    @Override
    public void onRetrievedMultiple(final Barcode closetToClick, final List<BarcodeGraphic> barcodeGraphics) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = "Codes include : \n";
                for (int index = 0; index < barcodeGraphics.size(); index++) {
                    Barcode barcode = barcodeGraphics.get(index).getBarcode();
                    message += (index + 1) + ". " + barcode.displayValue + "\n";
                 }
               s1 = message;
                ExampleDB db = new ExampleDB(MainActivity.this);
                db.insert(s1);
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        for (int i = 0; i < sparseArray.size(); i++) {
            Barcode barcode = sparseArray.valueAt(i);
            Log.e("value", barcode.displayValue);
            Toast.makeText(MainActivity.this, " "+barcode.displayValue,Toast.LENGTH_SHORT ).show();
        }

    }

    @Override
    public void onRetrievedFailed(String reason) {

    }

    @Override
    public void onPermissionRequestDenied() {

    }


}
