package go.bolang.www.bolang_go;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import model.Challenge;

import static android.R.attr.x;
import static android.R.attr.y;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ShakeActivity extends AppCompatActivity implements SensorEventListener, LocationListener {
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD1 = 500;
    private static final int SHAKE_THRESHOLD2 = 1000;
    private static final int SHAKE_THRESHOLD3 = 3000;
    private static final int SHAKE_THRESHOLD4 = 6000;

    private DatabaseReference mDatabase;
    private DatabaseReference mTreasure;

    private Button buttonMulai;
    private boolean sudahMulai = false;

    private Challenge challenge;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        buttonMulai = (Button) findViewById(R.id.buttonMulaiShake);
        // Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference("");

        mTreasure = mDatabase.child("");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER && sudahMulai) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD1) {
                    getIncrementalValue1(23);
                }else if (speed > SHAKE_THRESHOLD2){
                    getIncrementalValue2(52);
                }else if (speed > SHAKE_THRESHOLD3){
                    getIncrementalValue3(76);
                }else if (speed > SHAKE_THRESHOLD4){
                    getIncrementalValue4(92);
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

//    private void getRandomNumber() {
//        ArrayList numbersGenerated = new ArrayList();
//
//        for (int i = 0; i < 6; i++) {
//            Random randNumber = new Random();
//            int iNumber = randNumber.nextInt(48) + 1;
//
//            if(!numbersGenerated.contains(iNumber)) {
//                numbersGenerated.add(iNumber);
//            } else {
//                i--;
//            }
//        }
//
//        TextView text = (TextView)findViewById(R.id.number_1);
//        text.setText(""+numbersGenerated.get(0));
//
//        text = (TextView)findViewById(R.id.number_2);
//        text.setText(""+numbersGenerated.get(1));
//
//        text = (TextView)findViewById(R.id.number_3);
//        text.setText(""+numbersGenerated.get(2));
//
//        text = (TextView)findViewById(R.id.number_4);
//        text.setText(""+numbersGenerated.get(3));
//
//        text = (TextView)findViewById(R.id.number_5);
//        text.setText(""+numbersGenerated.get(4));
//
//        text = (TextView)findViewById(R.id.number_6);
//        text.setText(""+numbersGenerated.get(5));
//    }

    private int points = 0;
    private final int max_points = 3000; //based on defined max points when config new game
    private void getIncrementalValue1(int poin) {
        TextView text = (TextView)findViewById(R.id.number_3);
        if(points<max_points) {
            points += poin;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
        if(points>max_points){
            points = max_points;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
    }

    private void getIncrementalValue2(int poin) {
        TextView text = (TextView)findViewById(R.id.number_3);
        if(points<max_points) {
            points += poin;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
        if(points>max_points){
            points = max_points;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
    }

    private void getIncrementalValue3(int poin) {
        TextView text = (TextView)findViewById(R.id.number_3);
        if(points<max_points) {
            points += poin;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
        if(points>max_points){
            points = max_points;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
    }

    private void getIncrementalValue4(int poin) {
        TextView text = (TextView)findViewById(R.id.number_3);
        if(points<max_points) {
            points += poin;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
        if(points>max_points){
            points = max_points;
            text.setText("" + (int) points +"/" + (int) max_points);
        }
    }

        public void mulaiShake(View view) {
            this.sudahMulai = true;
//            buttonMulai.setEnabled(false);

            View namebar = (View) findViewById(R.id.buttonMulaiShake);
            ((ViewGroup) namebar.getParent()).removeView(namebar);

            final TextView timerText = (TextView)findViewById(R.id.timer);

//        start 1 minutes timer
            new CountDownTimer(20000, 10) {

                public void onTick(long millisUntilFinished) {
                    timerText.setText((millisUntilFinished / 1000 < 10 ? ("0" + millisUntilFinished / 1000):(millisUntilFinished / 1000))  + ":" + ((millisUntilFinished % 1000 / 10) < 10 ? ("0" + millisUntilFinished % 1000 / 10):(millisUntilFinished % 1000 / 10))  );
                }

                public void onFinish() {
                    sudahMulai = false;
                    timerText.setTextSize(40);
                    timerText.setText("Waktu Habis! Anda mendapatkan " + points + " koin");
                }
            }.start();

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;

        if(challenge != null){
            double radius = challenge.getDistance(location);

            // TODO kalo radius lokasinya lebih dari yang ditentukan dia bakal keluar.
            if(radius > 10){
                finish();
            }
        }
    }
}
