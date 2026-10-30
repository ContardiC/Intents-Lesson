package it.spacecoding.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnSendSMS;
    Button btnStartCall;
    Button btnStartAlarm;

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

        // Inizializzazione dei pulsanti con findViewById
        btnSendSMS = findViewById(R.id.btnSendSMS);
        btnStartCall = findViewById(R.id.btnStartCall);
        btnStartAlarm = findViewById(R.id.btnStartAlarm);

        // SEND SMS
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "SendSMS clicked");
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms:+39 324 1234567"));

                // Per inserire un messaggio pre costruito
                // intent.putExtra("sms_body","Ciao Gino, ti ricordo l'appuntamento di domani alle ore 13:30");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    Log.d("MainActivity", "Action avviata");
                } else {
                    Log.d("MainActivity", "Action NON avviata");
                }
            }
        });

        // START CALL
        btnStartCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+39 324 1234567"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("MainActivity", "App Telefono non trovata");
                }
            }
        });

        // START ALARM
        btnStartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Riunione con Nino e Gino");
                intent.putExtra(AlarmClock.EXTRA_HOUR, 12);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, 0);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("MainActivity", "App Orologio/Sveglia non trovata");
                }
            }
        });
    }
}