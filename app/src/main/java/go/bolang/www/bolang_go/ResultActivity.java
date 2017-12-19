package go.bolang.www.bolang_go;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import model.Constant;
import model.DataManager;
import model.GameInfo;

public class ResultActivity extends AppCompatActivity {

    GameInfo gi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gi = new GameInfo();
        DataManager.saveGameInfo(gi,Constant.FILENAME_GAME_INFO, getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}
