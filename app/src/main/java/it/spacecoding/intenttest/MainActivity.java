package it.spacecoding.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // IMPO: modificare AndroidManifest.xml
    Button btnSendSMS;
    Button btnStarCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSendSMS = findViewById(R.id.btnSendSMS);
        btnStarCall = findViewById(R.id.btnStartCall);
        // SEND SMS
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "SendSMS clicked");
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                // URI -> Uniform Resource Identifier
                intent.setData(Uri.parse("sms:+39 324 1234567"));

                // Per inserire un messaggio pre costruito
                // intent.putExtra("sms_body","Ciao Gino, ti ricordo l'appuntamento di domani alle ore 13:30");



                // controllo che esista un activity che può eseguire l'azione
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                    Log.d("MainActivity", "Action avviata");
                }else{
                    Log.d("MainActivity", "Action NON avviata");
                }

            }
        });
        // START CALL
        // Aggiungere uses-permission e uses-feature
        btnStarCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +39 324 1234567"));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });
    }
}