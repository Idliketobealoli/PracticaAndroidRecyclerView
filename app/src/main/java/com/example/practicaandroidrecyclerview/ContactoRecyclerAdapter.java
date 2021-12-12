package com.example.practicaandroidrecyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactoRecyclerAdapter extends RecyclerView.Adapter<ContactoRecyclerAdapter.ViewHolder> {
    final private ContactItemClickListener clickListener;
    List<Contacto> contacts;
    boolean showImages = false;
    boolean nightMode = false;

    public ContactoRecyclerAdapter(ContactItemClickListener clickListener, List<Contacto> contactos) {
        this.clickListener = clickListener;
        this.contacts = contactos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoRecyclerAdapter.ViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.phoneNumber.setText(String.valueOf(contacts.get(position).getPhoneNumber()));
        holder.imageURL.setImageResource(contacts.get(position).getImageUrl());
        if (!showImages) {
            holder.imageURL.setVisibility(View.GONE);
        }
        if (!contacts.get(position).isFavourite()) {
            holder.favourite.setVisibility(View.GONE);
        } else holder.favourite.setVisibility(View.VISIBLE);

        if (nightMode) {
            holder.layout.setBackgroundColor(Color.rgb(20,20,20));
            holder.name.setBackgroundColor(Color.rgb(20,20,20));
            holder.phoneNumber.setBackgroundColor(Color.rgb(20,20,20));
            holder.name.setTextColor(Color.WHITE);
            holder.phoneNumber.setTextColor(Color.WHITE);
        } else {
            holder.layout.setBackgroundColor(Color.WHITE);
            holder.name.setBackgroundColor(Color.WHITE);
            holder.phoneNumber.setBackgroundColor(Color.WHITE);
            holder.name.setTextColor(Color.rgb(20,20,20));
            holder.phoneNumber.setTextColor(Color.rgb(20,20,20));
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void addNewContact(Contacto contact) {
        contacts.add(contact);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name, phoneNumber;
        ImageView imageURL, favourite;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.cLayoutItem);
            name = itemView.findViewById(R.id.contact_name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            imageURL = itemView.findViewById(R.id.contact_pfp);
            favourite = itemView.findViewById(R.id.star_contact_icon);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickListener.onListItemClick(position);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            clickListener.onLongClick(position);
            return true;
        }
    }
}
