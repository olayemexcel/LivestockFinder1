package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

import uk.ac.tees.b1210259.livestockfinder1.Adaptor.CategoryAdaptor;
import uk.ac.tees.b1210259.livestockfinder1.Adaptor.PopularAdaptor;

public class Home extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategorylist,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerViewcategory();
        recyclerViewPopular();
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
        animalList.add(new AnimalDomain("Brown cattle","cattle1","brown cattle commonly found in America",500.50));
        animalList.add(new AnimalDomain("Wolf dog","dog1","wolf dog has wolf gene and good for security",450.30));
        animalList.add(new AnimalDomain("Boro cattle","cattle2","white colour cattle commonly found in Africa",300.80));

        adapter2=new PopularAdaptor(animalList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

}