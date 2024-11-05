package com.example.crud_sqlite_mvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.duration.setText(String.valueOf(field.getDuration() + " minutes"));
        holder.viewImage.setImageResource(field.getImageResId());
        holder.fieldName.setText(String.valueOf(field.getFieldName()));

    }


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
        ImageView viewImage;
        LinearLayout dotsIndicator;
        TextView fieldName;


        ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.textViewDescription);
            duration = itemView.findViewById(R.id.textViewDuration);
            freeSlots = itemView.findViewById(R.id.textViewFreeSlot);
            time = itemView.findViewById(R.id.textViewTime);
            location = itemView.findViewById(R.id.textViewLocation);
            dotsIndicator = itemView.findViewById(R.id.dotsIndicator);
            viewImage = itemView.findViewById(R.id.viewImage);
            fieldName = itemView.findViewById(R.id.textViewName);
        }
    }
}
