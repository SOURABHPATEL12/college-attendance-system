package com.example.attendify.admain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendify.R;

import java.util.ArrayList;

public class HodAdapter extends RecyclerView.Adapter<HodAdapter.ViewHolder> {

    Context context;
    ArrayList<REcyclervierModelclass> list;
     static OnDeleteClick listener;

    // Constructor
    public HodAdapter(Context context, ArrayList<REcyclervierModelclass> list,OnDeleteClick listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    // ================= VIEW HOLDER =================
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile,DeletMenu;
        TextView name, email, department, branch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            name = itemView.findViewById(R.id.RVName);
            email = itemView.findViewById(R.id.RVEmail);
            department = itemView.findViewById(R.id.RvDepartment);
            branch = itemView.findViewById(R.id.RVBranch);
            DeletMenu = itemView.findViewById(R.id.RVMenu);

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
        holder.DeletMenu.setOnClickListener(v -> {

            PopupMenu popupMenu = new PopupMenu(context, holder.DeletMenu);
            popupMenu.inflate(R.menu.rv_menu); // create this menu

            popupMenu.setOnMenuItemClickListener(item -> {

                if (item.getItemId() == R.id.delete_option) {

                    // call delete listener
                    listener.onDelete(position);

                    return true;
                }

                return false;
            });

            popupMenu.show();
        });
    }

    // ================= ITEM COUNT =================
    @Override
    public int getItemCount() {
        return list.size();
    }
}