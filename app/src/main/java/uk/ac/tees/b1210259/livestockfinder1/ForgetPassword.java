package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {


    String verificationCodebySystem;
    Button verify_btn, buttonLogin;
    EditText myemail;
    ProgressBar progressBar;


   // @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        verify_btn = findViewById(R.id.button_a);
        myemail = findViewById(R.id.resetemail);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        buttonLogin = findViewById(R.id.btnLogin);



        //Button login click event
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        }); //Button login ends


        //Button reset click event
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);*/
                Toast.makeText(ForgetPassword.this, "Password reset link sent to your mail!", Toast.LENGTH_SHORT).show();

            }

        }); //Button reset ends

    }
}