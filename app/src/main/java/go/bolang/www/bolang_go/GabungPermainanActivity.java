package go.bolang.www.bolang_go;

import android.content.Context;
import android.content.Intent;
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

        // Add new game info
        gameInfo = new GameInfo();
        gameInfo.setGameName(rawResult.getText());


        try {
            FileOutputStream fos = openFileOutput(Constant.FILENAME_GAME_INFO, Context.MODE_PRIVATE);
                    //this.openFileOutput("GameInfo", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(gameInfo);
            os.close();
            fos.close();
            Log.d(this.getClass().getName(), "gabung permainan success");
            // pindah ke gabung permainan 2
            this.toGabungPermainan(rawResult.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        AlertDialog.Builder builder = new AlertDialog.Builder(this)6;
//        builder.setTitle("Scan Result");
//        builder.setMessage(rawResult.getText());
//        AlertDialog alert1 = builder.create();
//        alert1.show();

        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
    }

    public void toGabungPermainan(String code) {
        Intent intent = new Intent(GabungPermainanActivity.this, GabungPermainan2Activity.class);
        intent.putExtra("code", code);
        startActivity(intent);
    }

}
