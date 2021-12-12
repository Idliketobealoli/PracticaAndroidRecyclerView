package com.example.practicaandroidrecyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class DialogShowContact extends DialogFragment {
    private Contacto contact;

    public DialogShowContact(Contacto contact) {
        this.contact = contact;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_contact_info, null);

        TextView textViewName = dialogView.findViewById(R.id.textViewName);
        TextView textViewPhone = dialogView.findViewById(R.id.textViewPhone);
        ImageView imageViewPFP = dialogView.findViewById(R.id.imageViewPFP);
        ImageView imageViewStar = dialogView.findViewById(R.id.imageViewFavourite);
        Button buttonBack = dialogView.findViewById(R.id.buttonGoBack);

        textViewName.setText(contact.getName());
        textViewPhone.setText(String.valueOf(contact.getPhoneNumber()));
        imageViewPFP.setImageResource(contact.getImageUrl());
        if (!contact.isFavourite()) {
            imageViewStar.setVisibility(View.GONE);
        }

        builder.setView(dialogView).setMessage("Contact information:");

        buttonBack.setOnClickListener(v -> dismiss());

        return builder.create();
    }
}
