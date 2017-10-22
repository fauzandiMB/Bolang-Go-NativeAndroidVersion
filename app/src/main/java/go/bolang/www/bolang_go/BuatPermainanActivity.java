package go.bolang.www.bolang_go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BuatPermainanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_permainan);
    }

    public void toGabungPermainan2(View view) {
        Intent intent = new Intent(BuatPermainanActivity.this, BuatPermainan2Activity.class);
        startActivity(intent);
    }
}
