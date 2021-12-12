package com.example.practicaandroidrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactItemClickListener {
    private SharedPreferences preferences;
    private boolean nightMode;
    private boolean showImages;
    private ArrayList<Contacto> contactos;
    private RecyclerView recyclerview;
    private ConstraintLayout layoutMain;
    private ConstraintLayout layoutRecycler;
    private TextView textViewMain;
    private LinearLayoutManager layoutManager;
    private ContactoRecyclerAdapter adapter;
    private JsonSerializer jsS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recycler);
        layoutMain = findViewById(R.id.cLayoutMain);
        layoutRecycler = findViewById(R.id.cLayoutRecycler);
        textViewMain = findViewById(R.id.contactos);
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        contactos = readJSON();

        adapter = new ContactoRecyclerAdapter(this, contactos);
        configurePreferences();
        recyclerview.setAdapter(adapter);
    }

    private ArrayList<Contacto> readJSON() {
        jsS = new JsonSerializer("contacts.json", this);
        return jsS.load();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveContacts();
    }

    private void saveContacts() {
        jsS = new JsonSerializer("contacts.json", this);
        jsS.save(contactos);
        recyclerview.setAdapter(adapter);
    }

    private void determineMode(boolean nightMode) {
        if (nightMode) {
            layoutMain.setBackgroundColor(Color.rgb(20,20,20));
            layoutRecycler.setBackgroundColor(Color.rgb(20,20,20));
            textViewMain.setBackgroundColor(Color.rgb(20,20,20));
            recyclerview.setBackgroundColor(Color.rgb(20,20,20));
            textViewMain.setTextColor(Color.rgb(255,255,255));
        } else {
            layoutMain.setBackgroundColor(Color.rgb(255,255,255));
            layoutRecycler.setBackgroundColor(Color.rgb(255,255,255));
            textViewMain.setBackgroundColor(Color.rgb(255,255,255));
            recyclerview.setBackgroundColor(Color.rgb(255,255,255));
            textViewMain.setTextColor(Color.rgb(20,20,20));
        }
    }

    private void configurePreferences() {
        preferences = getSharedPreferences("PreferencesContactApp", MODE_PRIVATE);
        nightMode = preferences.getBoolean("NightMode", false);
        showImages = preferences.getBoolean("ShowImages", false);
        updateNightMode(nightMode);
        updateShowImages(showImages);
        determineMode(nightMode);
    }

    public void createNewContact(Contacto contact) {
        adapter.addNewContact(contact);
        recyclerview.setAdapter(adapter);
    }

    public void updateNightMode(boolean nightMode) { adapter.nightMode = nightMode; }

    public void updateShowImages(boolean showImages) { adapter.showImages = showImages; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            DialogNewContact newContact = new DialogNewContact();
            newContact.show(getSupportFragmentManager(), "new_contact");
        }
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onListItemClick(int position) {
        DialogShowContact showContact = new DialogShowContact(contactos.get(position));
        showContact.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onLongClick(int position) {
        //Si solo hay un contacto, este no se podrá borrar, pues no tiene sentido una app de contactos sin contactos.
        if (contactos.size() > 1) {
            contactos.remove(position);
            adapter.notifyDataSetChanged();
        }
    }
}
