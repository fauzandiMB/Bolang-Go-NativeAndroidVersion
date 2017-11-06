package go.bolang.www.bolang_go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GabungPermainan2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gabung_permainan2);
    }

    public void toMap(View view) {
        Intent intent = new Intent(GabungPermainan2Activity.this, BolangActivity.class);
        startActivity(intent);
    }
}
