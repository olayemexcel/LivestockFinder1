package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    Button btnExplore, btnlogout;
    TextView fullNameLabel, usernameLabel;
    TextInputLayout fullName,email,phoneNo,password;

    //initialize location variables
    EditText editText;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        //assign location variable
        editText = findViewById(R.id.edit_location);
        //textView = findViewById(R.id.view_address);


        //Hooks
        fullName = findViewById(R.id.prof_full_name);
        email = findViewById(R.id.prof_email);
        phoneNo = findViewById(R.id.prof_phoneNo);
       // password = findViewById(R.id.prof_password);
        fullNameLabel = findViewById(R.id.prof_page);
        usernameLabel = findViewById(R.id.prof_des);

        btnExplore = findViewById(R.id.prof_button);
        btnlogout = findViewById(R.id.logoutbtn);

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

        //Button logout click event
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        }); //Button logout ends


        //initialize places
        Places.initialize(getApplicationContext(), "AIzaSyA_ReHuBhQwXPh7AVdO2hjYzVQ1VVI9kNE");

        //set edit text non focusable
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);
                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(UserProfile.this);
                //start activity result

                startActivityForResult(intent,100);
            }
        }); // end


    }

    //continuation of google places code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            //when success

            //initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //Set address on EditText
            editText.setText(String.format("Postcode : %s",place.getAddress()));
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR){
            //when error
            //initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            //Display toast
            Toast.makeText(getApplicationContext(),status.getStatusMessage(),Toast.LENGTH_SHORT).show();
        }
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