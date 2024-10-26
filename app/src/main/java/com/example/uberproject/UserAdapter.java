package com.example.uberproject;

import android.content.Context;
import android.text.Layout;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberproject.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context cnx;
    List<User> users;

    public UserAdapter(Context cnx, List<User> users) {
        this.cnx = cnx;
        this.users = users;
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
holder.btn.setOnClickListener(view -> {
    Toast.makeText(cnx,"the user is "+u.getUsername(),Toast.LENGTH_SHORT).show();
});
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

