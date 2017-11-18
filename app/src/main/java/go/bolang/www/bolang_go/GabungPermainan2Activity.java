package go.bolang.www.bolang_go;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GabungPermainan2Activity extends AppCompatActivity {

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

        setContentView(R.layout.activity_gabung_permainan2);
    }

    public void toMap(View view) {
        Intent intent = new Intent(GabungPermainan2Activity.this, BolangActivity.class);
        startActivity(intent);
    }
}
