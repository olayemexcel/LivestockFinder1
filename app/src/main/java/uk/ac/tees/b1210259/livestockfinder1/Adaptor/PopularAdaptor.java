package uk.ac.tees.b1210259.livestockfinder1.Adaptor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.ac.tees.b1210259.livestockfinder1.AnimalDomain;
import uk.ac.tees.b1210259.livestockfinder1.R;
import uk.ac.tees.b1210259.livestockfinder1.ShowDetail;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    ArrayList<AnimalDomain> popularAnimal;

    public PopularAdaptor(ArrayList<AnimalDomain> categoryDomains) {
        this.popularAnimal = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(popularAnimal.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularAnimal.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(popularAnimal.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.trackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), ShowDetail.class);
                intent.putExtra("object", popularAnimal.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return popularAnimal.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView trackBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            trackBtn=itemView.findViewById(R.id.trackBtn);
        }
    }

}
