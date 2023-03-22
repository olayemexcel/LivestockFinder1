package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    Button btnExplore;
    TextView fullNameLabel, usernameLabel;
    TextInputLayout fullName,email,phoneNo,password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks
        fullName = findViewById(R.id.prof_full_name);
        email = findViewById(R.id.prof_email);
        phoneNo = findViewById(R.id.prof_phoneNo);
       // password = findViewById(R.id.prof_password);
        fullNameLabel = findViewById(R.id.prof_page);
        usernameLabel = findViewById(R.id.prof_des);

        btnExplore = findViewById(R.id.prof_button);

        //Show All Data
        showAllUserData();


        //Button explore click event
        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);

            }
        }); //Button explore ends


    }
    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phone = intent.getStringExtra("phoneNo");
        //String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phone);
        //password.getEditText().setText(user_password);
    }
}
//UserProfile > showAllUserData()