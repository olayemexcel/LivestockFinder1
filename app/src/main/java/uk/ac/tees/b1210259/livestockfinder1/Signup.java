package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //signup form variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regLoginBtn;

    //Database reference
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    Button btn2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //disable status top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        //Links to all xml elements in the activity_signup.xml
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.signup);
        regLoginBtn = findViewById(R.id.button4);


        //Click button to save data in Firebase
       /* regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Get all the values in String
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                UserSignupClass helperClass = new UserSignupClass(name, username, email, phoneNo, password);
                reference.child(phoneNo).setValue(helperClass);
                Toast.makeText(Signup.this, "Saving data..", Toast.LENGTH_SHORT).show();
            }
        }); *///Signup button method End


        //onclick event to another page
        btn2 = findViewById(R.id.button4);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });// Login button method end
    } // OnCreate methods end Old signup


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailFormat)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        /*String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +  //ant letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\s+$)" +  //no white space
                ".{4,}" +  //at least 4 char
                "$";  */

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } /*else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        }*/ else {
            regPassword.setError(null);
            return true;
        }
    }


    //regBtn.setOnClickListener(new View.OnClickListener() {
    // @Override
    //Save data in firebase when click button
    public void registerUser(View view) {

       rootNode = FirebaseDatabase.getInstance();
       reference = rootNode.getReference("users");


        if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()) {
            return;
        }

        //Get all the values in String
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        UserSignupClass helperClass = new UserSignupClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);
        Toast.makeText(Signup.this, "Data Save Successfully!", Toast.LENGTH_SHORT).show();

    }

}

