package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.os.Bundle;

public class Privacy1 extends AppCompatActivity {

    Button btn1;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_privacy1);

        btn1 = findViewById(R.id.buttPrivacy);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });
    }
}