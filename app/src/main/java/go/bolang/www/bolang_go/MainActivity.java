package go.bolang.www.bolang_go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private Button logoutBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    finish();
                }
            }
        };

        Button logoutBtn = (Button) findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                if(mAuth.getCurrentUser() == null){
                    finish();
                }
            }
        });
    }

    public void toGabungPermainan(View view) {
        Intent intent = new Intent(MainActivity.this, GabungPermainanActivity.class);
        startActivity(intent);
    }

    public void toBuatPermainan(View view) {
        Intent intent = new Intent(MainActivity.this, BuatPermainanActivity.class);
        startActivity(intent);
    }

    public void toCobaShake(View view) {
        Intent intent = new Intent(MainActivity.this, ShakeActivity.class);
        startActivity(intent);
    }

    public void toCobaMap(View view) {
        Intent intent = new Intent(MainActivity.this, BolangActivity.class);
        startActivity(intent);
    }
}
