package go.bolang.www.bolang_go;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import model.Constant;
import model.GameInfo;

public class GabungPermainan2Activity extends AppCompatActivity{
    private GameInfo gameInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String code = extras.getString("code");
            //The key argument here must match that used in the other activity

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan Result");
            builder.setMessage(code);
            AlertDialog alert1 = builder.create();
            alert1.show();
        }


        try {
            FileInputStream fis = this.openFileInput(Constant.FILENAME_GAME_INFO);
            ObjectInputStream is = new ObjectInputStream(fis);
            gameInfo = (GameInfo) is.readObject();
            Log.d(this.getClass().getName(), "gabung permainan " + gameInfo.gameName);
            is.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Log.d(this.getClass().getName(), "gabung permainan 2");

        setContentView(R.layout.activity_gabung_permainan2);
    }

    public void toMap(View view) {
        Intent intent = new Intent(GabungPermainan2Activity.this, BolangActivity.class);
        startActivity(intent);
    }
}
