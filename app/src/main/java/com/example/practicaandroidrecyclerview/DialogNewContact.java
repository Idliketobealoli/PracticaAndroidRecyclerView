package com.example.practicaandroidrecyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNewContact extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_contact, null);
        EditText editName = dialogView.findViewById(R.id.ContactNameEditText);
        EditText editNumber = dialogView.findViewById(R.id.PhoneNumberEditText);
        CheckBox checkFavourite = dialogView.findViewById(R.id.checkBoxFavourites);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        Button buttonConfirm = dialogView.findViewById(R.id.buttonConfirm);

        builder.setView(dialogView).setMessage("Add contact:");

        buttonCancel.setOnClickListener(v -> dismiss());
        buttonConfirm.setOnClickListener(v -> {
            Contacto contact = new Contacto();

            if(!editNumber.getText().toString().isEmpty()) {
                contact.setPhoneNumber(Integer.parseInt(editNumber.getText().toString()));
            }
            else contact.setPhoneNumber(0);
            if (!editName.getText().toString().isEmpty()) {
                contact.setName(editName.getText().toString());
            }
            else contact.setName(String.valueOf(contact.getPhoneNumber()));

            contact.setFavourite(checkFavourite.isChecked());

            MainActivity callMain = (MainActivity) getActivity();

            callMain.createNewContact(contact);
            dismiss();
        });

        return builder.create();
    }
}
