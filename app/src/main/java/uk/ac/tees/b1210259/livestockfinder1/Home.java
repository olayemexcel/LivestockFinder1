package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

import uk.ac.tees.b1210259.livestockfinder1.Adaptor.CategoryAdaptor;
import uk.ac.tees.b1210259.livestockfinder1.Adaptor.PopularAdaptor;

public class Home extends AppCompatActivity {

    Button btnprofile;

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategorylist,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerViewcategory();
        recyclerViewPopular();

        //Hook
        btnprofile = findViewById(R.id.profilebtn);


        //Button profile click event
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(intent);

            }
        }); //Button profile ends
    }

    private void recyclerViewcategory() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayoutManager LinearLayoutManager=new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategorylist=findViewById(R.id.recyclerView);
        recyclerViewCategorylist.setLayoutManager(LinearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain( "Cattle", "cat_1"));
        category.add(new CategoryDomain( "Goat", "cat_2"));
        category.add(new CategoryDomain( "Sheep", "cat_3"));
        category.add(new CategoryDomain( "Dog", "cat_4"));
        category.add(new CategoryDomain( "Cat", "cat_5"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategorylist.setAdapter(adapter);

    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<AnimalDomain> animalList=new ArrayList<>();
        animalList.add(new AnimalDomain("Brown cattle","cattle1","Brown cattle commonly found in America - Contact: Livestockinfo@gmail.com or Call 07459779004",500.50));
        animalList.add(new AnimalDomain("Wolf dog","dog1","Wolf dog has wolf gene and good for security- Contact: Livestockinfo@gmail.com or Call 07459779004",450.30));
        animalList.add(new AnimalDomain("Boro cattle","cattle2","White colour cattle commonly found in Africa- Contact: Livestockinfo@gmail.com or Call 07459779004",300.80));

        adapter2=new PopularAdaptor(animalList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

}