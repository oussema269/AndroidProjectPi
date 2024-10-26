package com.example.uberproject;

import android.view.ContentInfo;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberproject.model.User;

import java.util.List;

public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView username, role, email;
    Button btn;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        username=itemView.findViewById(R.id.usernameBack);
        role=itemView.findViewById(R.id.roleBack);
        email=itemView.findViewById(R.id.emailBack);
        btn=itemView.findViewById(R.id.user_button);

    }
}