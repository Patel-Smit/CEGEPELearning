package com.smit.cegepe_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_settings extends Fragment {

    private Switch themeSwitch;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        themeSwitch = v.findViewById(R.id.settings_sw_theme);
        inflater.getContext().setTheme(R.style.MainActivityDarkTheme);
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    inflater.getContext().setTheme(R.style.AppTheme);
                } else {
                    inflater.getContext().setTheme(R.style.MainActivityDarkTheme);
                    Intent i = new Intent(getContext(), SplashScreenActivity.class);
                    startActivity(i);
                }
            }
        });

        return v;
    }
}
