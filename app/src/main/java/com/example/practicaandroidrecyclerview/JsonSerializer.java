package com.example.practicaandroidrecyclerview;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonSerializer {
    private String fileName;
    private Context context;
    private Gson gson;

    public JsonSerializer(String s, Context context) {
        this.fileName = s;
        this.context = context;
    }

    public void save(ArrayList<Contacto> contactos) {
        gson = new Gson();
        String contactJson = gson.toJson(contactos);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(fileName,context.MODE_PRIVATE)));
            bw.write(contactJson);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Contacto> load() {
        ArrayList<Contacto> result = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));
            gson = new Gson();
            Type type = new TypeToken<ArrayList<Contacto>>(){}.getType();
            result = gson.fromJson(br, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (result == null) {
            return fillContacts();
        }
        else return result;
    }

    private ArrayList<Contacto> fillContacts() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Ena Arisu", 620_79_59_55, R.drawable.pfp_transparent, true));
        contactos.add(new Contacto("UwU", 420_69_69_69, R.drawable.pfp_v_sign, null));
        contactos.add(new Contacto("Kyoko Kirigiri", 123_45_67_89, R.drawable.pfp_transparent, true));
        contactos.add(new Contacto(null, 987_65_43_21, null, null));
        contactos.add(new Contacto("Someone", 911_69_69_69, null, false));
        contactos.add(new Contacto("Alfonso", 112_00_00_00, null, true));
        return contactos;
    }
}
