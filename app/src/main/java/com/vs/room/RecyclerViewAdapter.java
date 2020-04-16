package com.vs.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NameHolder> {
    private List<RoomEntity> names = new ArrayList<>();

    @NonNull
    @Override
    public NameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new NameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NameHolder holder, int position) {
        RoomEntity currentNote = names.get(position);
        holder.textViewTitle.setText(currentNote.getFirstName());
        holder.textViewDescription.setText(currentNote.getLastName());
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public void setNames(List<RoomEntity> names) {
        this.names = names;
        notifyDataSetChanged();
    }

    public RoomEntity getNoteAt(int position) {
        return names.get(position);
    }

    class NameHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;

        public NameHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView);
            textViewDescription = itemView.findViewById(R.id.textView1);
        }
    }
}