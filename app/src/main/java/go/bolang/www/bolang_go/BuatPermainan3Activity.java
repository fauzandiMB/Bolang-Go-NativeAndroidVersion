package go.bolang.www.bolang_go;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BuatPermainan3Activity extends AppCompatActivity {


    ImageView image_generated_qrcode;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_permainan3);
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio_game1);
        radio1.setChecked(true);  
        changeBarcode("game1");
    }

    public void changeBarcode(String code) {
        image_generated_qrcode = (ImageView) findViewById(R.id.image_generated_qrcode);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image_generated_qrcode.setImageBitmap(bitmap);
        }
        catch (WriterException e){
            e.printStackTrace();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_game1:
                if (checked)
                    changeBarcode("game1");
                    break;
            case R.id.radio_game2:
                if (checked)
                    changeBarcode("game2");
                    break;
            case R.id.radio_game3:
                if (checked)
                    changeBarcode("game3");
                    break;
        }
    }
}