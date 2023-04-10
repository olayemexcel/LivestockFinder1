package uk.ac.tees.b1210259.livestockfinder1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Signup extends AppCompatActivity {

    //signup form variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regLoginBtn;
    boolean imageControl = false;

    //Database reference
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth auth;


    Button btn2;

    private CircleImageView imageViewCircle;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //disable status top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        //Links to all xml elements in the activity_signup.xml
        imageViewCircle = findViewById(R.id.imageViewCircle);
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.signup);
        regLoginBtn = findViewById(R.id.logoutbtn);

        imageViewCircle = findViewById(R.id.imageViewCircle);


        //onclick event to another page
        btn2 = findViewById(R.id.logoutbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });// Login button method end

       //image viewer
       imageViewCircle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               imageChooser();

           }
       });



    } // OnCreate methods end Old signup


    //image viewer metthod
    public void imageChooser()
    {
        Intent intent = new Intent();
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    //image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            Uri imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageViewCircle);
            imageControl = true;
        }
        else
        {
           imageControl = false;
        }
    }

    //auth method
    public void signup(String email, String password, final String username)
    {
       auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful())
               {
                   reference.child("users").child(auth.getUid()).child("username").setValue(username);

                   if (imageControl)
                   {

                   }
                   else
                   {
                       reference.child("users").child(auth.getUid()).child("image").setValue("null");
                   }
                   Intent intent = new Intent(Signup.this,UserProfile.class);
                   intent.putExtra("username",username );
                   startActivity(intent);
                   finish();
               }

               else
               {
                   Toast.makeText(Signup.this, "There is a problem", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }




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
        } else if (val.length() >= 50) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        }
            else {
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


        //Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);
        //intent.putExtra("phoneNo",phoneNo);
        //startActivity(intent);

        //Storing data in firebase
        UserSignupClass helperClass = new UserSignupClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);

       Toast.makeText(Signup.this, "Account created Successfully!", Toast.LENGTH_SHORT).show();

    }


}

