package go.bolang.www.bolang_go;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import model.Constant;
import model.DataManager;
import model.GameInfo;

public class QuizActivity extends AppCompatActivity {
    private GameInfo gi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gi = DataManager.loadGameInfo(Constant.FILENAME_GAME_INFO, getApplicationContext());
        setContentView(R.layout.activity_quiz);
    }

    public void choose_answer(View view) {
        gi.getCurrentChallenge().setCleared(true);
        gi.getPlayer().setIndexChallenge(gi.getPlayer().getIndexChallenge() + 1);
        gi.getPlayer().setAnsweredQuiz(gi.getPlayer().getAnsweredQuiz()+1);
        DataManager.saveGameInfo(gi, Constant.FILENAME_GAME_INFO, getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Selamat, Jawaban Anda Benar!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
