package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ForgetPassword extends AppCompatActivity {


    String verificationCodebySystem;
    Button verify_btn;
    EditText myemail;
    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        verify_btn = findViewById(R.id.button_a);
        myemail = findViewById(R.id.resetemail);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);


    }
}