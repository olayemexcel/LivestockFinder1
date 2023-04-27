package uk.ac.tees.b1210259.livestockfinder1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button btnSignUp, btnLogin, btnForget, btnPrivacy;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;

    //Database reference
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.button2);
        btnSignUp = findViewById(R.id.button3);
        btnForget = findViewById(R.id.button1);
        btnPrivacy = findViewById(R.id.privacybt);

        //Firebase auth method
        auth = FirebaseAuth.getInstance();



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);

            }
        });

        //Button forgetPassword click event
        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(intent);

            }
        }); //Button forgetPassword ends


        //Button privacy click event
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Privacy1.class);
                startActivity(intent);

            }
        }); //Button privacy ends


    }


    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {

        //Login Validation
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    username.setError(null);
                    username.setErrorEnabled(false);

                    Log.v("Login", userEnteredUsername + "," + userEnteredPassword);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);


                    if (passwordFromDB.equals(userEnteredPassword)) {


                        password.setError(null);
                        password.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                    } else {
                        password.setError("Invalid Password");
                        password.requestFocus();
                    }
                } else {
                    username.setError("Invalid Username");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


      /*  //Forget password method
        buttonForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });  */


    }
    // New Login
   /* public void login(String username, String password)
    {
       auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {

               if (task.isSuccessful())
               {
                   Intent intent = new Intent(Login.this, UserProfile.class);
                   Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(Login.this, "Login not successful", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }  */


}