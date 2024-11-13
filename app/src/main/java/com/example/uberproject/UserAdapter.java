package com.example.uberproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberproject.database.AppDataBase;
import com.example.uberproject.modelUser.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context cnx;
    List<User> users;
    AppDataBase appDataBase;

    public UserAdapter(Context cnx, List<User> users , AppDataBase appDataBase) {
        this.cnx = cnx;
        this.users = users;
        this.appDataBase=appDataBase;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(cnx).inflate(R.layout.activity_single_user_card,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
User u=users.get(position);
holder.username.setText(u.getUsername());
holder.role.setText(u.getRole());
holder.email.setText(u.getEmail());
holder.btn.setOnClickListener(view -> {
    Toast.makeText(cnx,"the user is "+u.getUsername(),Toast.LENGTH_SHORT).show();
    appDataBase.userDao().deleteUser(u.getUsername());
    // Supprimer l'utilisateur de la liste locale
    users.remove(position);

    // Notifier l'adaptateur de la suppression
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, users.size());

});
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

