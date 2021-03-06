package go.bolang.www.bolang_go;

import android.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import model.Constant;
import model.DataManager;
import model.GameInfo;

public class GabungPermainanActivity  extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private GameInfo gameInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_gabung_permainan);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        Log.d(this.getClass().getName(), "gabung permainan 1");
        //check permission

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkCameraPermission();
        }else {
            Log.d(this.getClass().getName(),"Udah di install permissionya");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        //this.toGabungPermainan(rawResult.getText());

        final String scanned = rawResult.getText();

        final GabungPermainanActivity act = this;

        if (scanned.equals("game1") || scanned.equals("game2") || scanned.equals("game3")) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setMessage("Anda akan masuk kedalam room " + scanned)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                            // Add new game info
                            gameInfo = new GameInfo();
                            gameInfo.setGameName(scanned);

                            if(DataManager.saveGameInfo(gameInfo,Constant.FILENAME_GAME_INFO, getApplicationContext())){
                                act.toGabungPermainan(scanned);
                            }

                            finish();
                        }
                    });
            android.app.AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setMessage("Barcode tidak valid! Silahkan scan ulang.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things

                            act.onResume();
                        }
                    });
            android.app.AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void toGabungPermainan(String code) {
        Intent intent = new Intent(GabungPermainanActivity.this, BolangActivity.class);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    public boolean checkCameraPermission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)){
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 3);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 3);
            }
            return false;
        }else
            return true;
    }

}
