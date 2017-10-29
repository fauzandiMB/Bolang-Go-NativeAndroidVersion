package go.bolang.www.bolang_go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class BuatPermainan2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinTRM, spinTRB, spinTRH, spinTRO,
            spinHRM, spinHRB, spinHRH, spinHRO;
    private Button bLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_permainan2);
    }

    public void addListener(){
        spinTRM = (Spinner) findViewById(R.id.spin_HR_M);
        spinTRB = (Spinner) findViewById(R.id.spin_HR_B);
        spinTRH = (Spinner) findViewById(R.id.spin_HR_H);
        spinTRO = (Spinner) findViewById(R.id.spin_HR_O);

        spinHRM = (Spinner) findViewById(R.id.spin_HR_M);
        spinHRB = (Spinner) findViewById(R.id.spin_HR_B);
        spinHRH = (Spinner) findViewById(R.id.spin_HR_H);
        spinHRO = (Spinner) findViewById(R.id.spin_HR_O);

        spinTRM.setOnItemSelectedListener(this);
        spinTRB.setOnItemSelectedListener(this);
        spinTRH.setOnItemSelectedListener(this);
        spinTRO.setOnItemSelectedListener(this);

        spinHRM.setOnItemSelectedListener(this);
        spinHRB.setOnItemSelectedListener(this);
        spinHRH.setOnItemSelectedListener(this);
        spinHRO.setOnItemSelectedListener(this);

        bLanjut = (Button) findViewById(R.id.b_Lanjut);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
