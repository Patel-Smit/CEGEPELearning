package com.smit.cegepe_learning;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_about extends Fragment {

    TextView tvAbout, tvTandCHeader, termsAndConditions;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        tvAbout = v.findViewById(R.id.about_tv_intro);
        tvTandCHeader = v.findViewById(R.id.about_tv_TandCHeading);
        termsAndConditions = v.findViewById(R.id.about_tv_termsAndCondi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvAbout.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
            termsAndConditions.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        tvTandCHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                termsAndConditions.setVisibility(View.VISIBLE);
            }
        });

        return v;
    }
}
