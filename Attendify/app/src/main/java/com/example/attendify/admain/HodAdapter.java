package com.example.attendify.admain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendify.R;

import java.util.ArrayList;

public class HodAdapter extends RecyclerView.Adapter<HodAdapter.ViewHolder> {

    Context context;
    ArrayList<REcyclervierModelclass> list;

    // Constructor
    public HodAdapter(Context context, ArrayList<REcyclervierModelclass> list) {
        this.context = context;
        this.list = list;
    }

    // ================= VIEW HOLDER =================
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile;
        TextView name, email, department, branch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            name = itemView.findViewById(R.id.RVName);
            email = itemView.findViewById(R.id.RVEmail);
            department = itemView.findViewById(R.id.RvDepartment);
            branch = itemView.findViewById(R.id.RVBranch);
        }
    }

    // ================= CREATE VIEW =================
    @NonNull
    @Override
    public HodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_designe ,parent, false);
        return new ViewHolder(view);
    }

    // ================= BIND DATA =================
    @Override
    public void onBindViewHolder(@NonNull HodAdapter.ViewHolder holder, int position) {

        REcyclervierModelclass model = list.get(position);

        holder.imgProfile.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.department.setText(model.getDepartment());
        holder.branch.setText(model.getBranch());
    }

    // ================= ITEM COUNT =================
    @Override
    public int getItemCount() {
        return list.size();
    }
}