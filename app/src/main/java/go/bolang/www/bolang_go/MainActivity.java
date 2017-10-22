package go.bolang.www.bolang_go;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   public void toBuatPermainan(View view) {
       Intent intent = new Intent(MainActivity.this, BuatPermainanActivity.class);
       startActivity(intent);
   }

    public void toGabungPermainan(View view) {
        Intent intent = new Intent(MainActivity.this, GabungPermainanActivity.class);
        startActivity(intent);
    }
}
