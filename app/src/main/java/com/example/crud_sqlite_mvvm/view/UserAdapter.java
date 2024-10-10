package com.example.crud_sqlite_mvvm.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> listUser;
    private IClickItemUser IClickItemUser;

    public interface IClickItemUser {
        void updateUser(User user);
        void DeleteUser(User user);

    }

    public UserAdapter(UserAdapter.IClickItemUser IClickItemUser) {
        this.IClickItemUser = IClickItemUser;
    }

    public void setData(List<User> list) {
        this.listUser = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUser.get(position);
        if (user == null) {
            return;
        }
        holder.tvUsername.setText(user.getUsername());
        holder.tvAddress.setText(user.getAddress());

        //Update database
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IClickItemUser.updateUser(user);
            }
        });

        //Delete 1 user
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IClickItemUser.DeleteUser(user);
            }
        });

        //Delete all user

    }

    @Override
    public int getItemCount() {
        if (listUser != null) {
            return listUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvAddress;
        private Button btnUpdate;
        private Button btnDelete;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
