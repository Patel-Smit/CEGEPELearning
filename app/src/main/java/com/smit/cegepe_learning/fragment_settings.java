package com.smit.cegepe_learning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_settings extends Fragment {

    private Switch themeSwitch, notificationsSwitch;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        themeSwitch = v.findViewById(R.id.settings_sw_theme);
        notificationsSwitch = v.findViewById(R.id.settings_sw_notifications);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", getContext().MODE_PRIVATE);
        notificationsSwitch.setChecked(sharedPreferences.getBoolean("value", true));

        notificationsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notificationsSwitch.isChecked()) {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save", getContext().MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    notificationsSwitch.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("save", getContext().MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    notificationsSwitch.setChecked(false);
                }
            }
        });

        SharedPreferences sharedPreferencesTheme = getContext().getSharedPreferences("saveTheme", getContext().MODE_PRIVATE);
        themeSwitch.setChecked(sharedPreferencesTheme.getBoolean("valueTheme", true));

        themeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeSwitch.isChecked()) {
                    SharedPreferences.Editor editor1 = getContext().getSharedPreferences("saveTheme", getContext().MODE_PRIVATE).edit();
                    editor1.putBoolean("valueTheme", true);
                    editor1.apply();
                    themeSwitch.setChecked(true);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("refresh", true);
                    startActivity(intent);
                } else {
                    SharedPreferences.Editor editor1 = getContext().getSharedPreferences("saveTheme", getContext().MODE_PRIVATE).edit();
                    editor1.putBoolean("valueTheme", false);
                    editor1.apply();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("refresh", true);
                    startActivity(intent);
                    themeSwitch.setChecked(false);
                }
            }
        });
        return v;
    }
}
