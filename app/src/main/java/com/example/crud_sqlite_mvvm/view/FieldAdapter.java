package com.example.crud_sqlite_mvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.AppDatabase;
import com.example.crud_sqlite_mvvm.model.Category;
import com.example.crud_sqlite_mvvm.model.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private List<Field> fields;
    private Context context;
    private AppDatabase db;
    private List<View> dotViews;

    public FieldAdapter(Context context, List<Field> fields, AppDatabase db) {
        this.context = context;
        this.fields = fields;
        this.db = db;
        this.dotViews = new ArrayList<>();
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
        notifyDataSetChanged();
    }

//    private List<Integer> getImagesFromCategory(int categoryId) {
//        List<Integer> images = new ArrayList<>();
//        Category category = db.categoryDao().getCategoryById(categoryId);
//        if (category != null) {
//            images.add(category.getImageResId());
//        }
//        return images;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fields, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Field field = fields.get(position);
        holder.description.setText(field.getDescription());
        holder.freeSlots.setText("Free slots: " + field.getFreeSlots());
        holder.time.setText(field.getStartTime() + " - " + field.getEndTime());
        holder.location.setText(field.getLocation());
        holder.duration.setText(String.valueOf(field.getDuration()+ " minutes"));
        holder.fieldName.setText(field.getFieldName());

//        List<Integer> imageResIds = getImagesFromCategory(field.getCategoryId());
//
//        if (imageResIds.isEmpty()) {
//            holder.viewPager.setVisibility(View.GONE);
//            holder.dotsIndicator.setVisibility(View.GONE);
//        } else {
//            holder.viewPager.setVisibility(View.VISIBLE);
//            holder.dotsIndicator.setVisibility(View.VISIBLE);
//
//            ImageAdapter imagePagerAdapter = new ImageAdapter(context, imageResIds);
//            holder.viewPager.setAdapter(imagePagerAdapter);
//
//            setupDots(holder, imageResIds.size());
//            updateDots(holder, 0);
//
//            holder.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                @Override
//                public void onPageSelected(int position) {
//                    updateDots(holder, position);
//                }
//            });
//        }
    }


//    private void setupDots(ViewHolder holder, int size) {
//        holder.dotsIndicator.removeAllViews();
//        dotViews.clear();
//        for (int i = 0; i < size; i++) {
//            View dot = new View(context);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(24, 24);
//            params.setMargins(8, 0, 8, 0);
//            dot.setLayoutParams(params);
//            dot.setBackgroundResource(R.drawable.dot_unselected);
//            holder.dotsIndicator.addView(dot);
//            dotViews.add(dot);
//        }
//    }
//
//    private void updateDots(ViewHolder holder, int selectedPosition) {
//        for (int i = 0; i < dotViews.size(); i++) {
//            dotViews.get(i).setBackgroundResource(i == selectedPosition ? R.drawable.dot_selected : R.drawable.dot_unselected);
//        }
//    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView freeSlots;
        TextView time;
        TextView location;
        TextView duration;
        TextView fieldName;
 //       ViewPager2 viewPager;
        LinearLayout dotsIndicator;

        ViewHolder(View itemView) {
            super(itemView);
 //           viewPager = itemView.findViewById(R.id.viewPagerImages);
            description = itemView.findViewById(R.id.textViewDescription);
            duration = itemView.findViewById(R.id.textViewDuration);
            freeSlots = itemView.findViewById(R.id.textViewFreeSlot);
            time = itemView.findViewById(R.id.textViewTime);
            location = itemView.findViewById(R.id.textViewLocation);
            dotsIndicator = itemView.findViewById(R.id.dotsIndicator);
            fieldName = itemView.findViewById(R.id.txtFieldName);
        }
    }
}
