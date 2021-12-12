package com.example.practicaandroidrecyclerview;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Animation animation;
    private boolean nightMode;
    private boolean showImages;
    private TextView title, nightModeText, imageText, warningText;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        preferences = getSharedPreferences("PreferencesContactApp", MODE_PRIVATE);

        editor = preferences.edit();

        nightMode = preferences.getBoolean("NightMode", false);
        showImages = preferences.getBoolean("ShowImages", false);
        
        layout = findViewById(R.id.layoutSettings);
        title = findViewById(R.id.textViewSettingsTitle);
        nightModeText = findViewById(R.id.textViewNightMode);
        imageText = findViewById(R.id.textViewShowImages);
        warningText = findViewById(R.id.textViewMayReducePerf);
        
        setMode(nightMode);

        Switch switchNight = findViewById(R.id.switchNightMode);
        Switch switchImages = findViewById(R.id.switchShowImages);
        ImageView imageLiv = findViewById(R.id.imageViewLiv);

        switchNight.setChecked(nightMode);

        switchNight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            imageLiv.startAnimation(animation);
            nightMode = isChecked;
            setMode(nightMode);
            editor.putBoolean("NightMode", nightMode);
            editor.commit();
        });

        switchImages.setChecked(showImages);

        switchImages.setOnCheckedChangeListener((buttonView, isChecked) -> {
            showImages = isChecked;
            imageLiv.startAnimation(animation);
            setMode(nightMode);
            editor.putBoolean("ShowImages", showImages);
            editor.commit();
        });
    }

    private void setMode(boolean nightMode) {
        if (nightMode) {
            title.setTextColor(Color.WHITE);
            nightModeText.setTextColor(Color.WHITE);
            imageText.setTextColor(Color.WHITE);
            warningText.setTextColor(Color.WHITE);
            layout.setBackgroundColor(Color.rgb(20,20,20));
            nightModeText.setBackgroundColor(Color.rgb(20,20,20));
            imageText.setBackgroundColor(Color.rgb(20,20,20));
            warningText.setBackgroundColor(Color.rgb(20,20,20));
            title.setBackgroundColor(Color.rgb(20,20,20));
        } else {
            title.setTextColor(Color.rgb(20,20,20));
            nightModeText.setTextColor(Color.rgb(20,20,20));
            imageText.setTextColor(Color.rgb(20,20,20));
            warningText.setTextColor(Color.rgb(20,20,20));
            layout.setBackgroundColor(Color.WHITE);
            nightModeText.setBackgroundColor(Color.WHITE);
            imageText.setBackgroundColor(Color.WHITE);
            warningText.setBackgroundColor(Color.WHITE);
            title.setBackgroundColor(Color.WHITE);
        }
    }
}