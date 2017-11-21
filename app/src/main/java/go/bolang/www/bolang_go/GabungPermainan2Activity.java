package go.bolang.www.bolang_go;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import model.Constant;
import model.DataManager;
import model.GameInfo;

public class GabungPermainan2Activity extends AppCompatActivity{
    private GameInfo gameInfo;
    private TextView tv;
    private String code;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gabung_permainan2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            code = extras.getString("code");

            tv = (TextView) findViewById(R.id.label_scan_berhasil);

//            mDatabase = FirebaseDatabase.getInstance().getReference("Game").child(code);
            if (code.equals("game1") || code.equals("game2") || code.equals("game3")) {
                tv.setText(tv.getText() + " " + code);
            }

            else {
                tv.setText("Barcode tidak valid\nSilahkan scan ulang!");
                tv.setTextSize(30);
//                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                findViewById(R.id.input_nama_pemain).setVisibility(View.GONE);
                findViewById(R.id.label_isi_nama).setVisibility(View.GONE);
                findViewById(R.id.label_nama_pemain).setVisibility(View.GONE);

                Button btn_back = (Button) findViewById(R.id.btn_next);
                btn_back.setText("Scan Ulang");
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backToScan();
                    }
                });
            }
        }

        GameInfo gi = DataManager.loadGameInfo(Constant.FILENAME_GAME_INFO, this.getApplicationContext());
        if(gi != null){
            gameInfo = gi;
        }

        Log.d(this.getClass().getName(), "gabung permainan 2");
    }

    private void backToScan() {
        Intent intent = new Intent(GabungPermainan2Activity.this, GabungPermainanActivity.class);
        startActivity(intent);
    }

    public void toMap(View view) {
        Intent intent = new Intent(GabungPermainan2Activity.this, BolangActivity.class);
        intent.putExtra("code", code);
        startActivity(intent);
    }
}
