package com.example.crud_sqlite_mvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.bumptech.glide.Glide;
import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categories;
    private Context context;

    // Constructor cho adapter
    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryName.setText(category.getName());
//        Glide.with(context)
//                .load(category.getImageResId())  // Updated to match the correct method
//                .placeholder(R.drawable.pic1)
//                .into(holder.categoryImage);
    }



    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        //ImageView categoryImage;

        ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.textViewCategoryName);
           // categoryImage = itemView.findViewById(R.id.textViewCategoryImage);
        }
    }

}
