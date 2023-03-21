package uk.ac.tees.b1210259.livestockfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import uk.ac.tees.b1210259.livestockfinder1.Adaptor.CategoryAdaptor;

public class Home extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerViewcategory();
    }

    private void recyclerViewcategory() {
        LinearLayoutManager LinearLayoutManager=new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategorylist=findViewById(R.id.recyclerView);
        recyclerViewCategorylist.setLayoutManager(LinearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain( "Cattle", "cat_1"));
        category.add(new CategoryDomain( "Goat", "cat_2"));
        category.add(new CategoryDomain( "Sheep", "cat_3"));
        category.add(new CategoryDomain( "Cat", "cat_4"));
        category.add(new CategoryDomain( "Dog", "cat_5"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategorylist.setAdapter(adapter);

    }

}