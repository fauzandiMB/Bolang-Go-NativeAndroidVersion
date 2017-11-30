package go.bolang.www.bolang_go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void choose_answer(View view) {
        Intent intent = new Intent(QuizActivity.this, BolangActivity.class);
        startActivity(intent);
    }
}
