package com.smit.cegepe_learning;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fragment_about extends Fragment {

    TextView tvAbout, tvTandCHeader, termsAndConditions, reportsComments;
    EditText etBug;
    Button sumitReport;
    DatabaseReference repReference;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        tvAbout = v.findViewById(R.id.about_tv_intro);
        tvTandCHeader = v.findViewById(R.id.about_tv_TandCHeading);
        termsAndConditions = v.findViewById(R.id.about_tv_termsAndCondi);
        reportsComments = v.findViewById(R.id.about_tv_repComm);

        etBug = v.findViewById(R.id.about_et_repComm);

        sumitReport = v.findViewById(R.id.about_btn_submitReport);

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

        reportsComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBug.setVisibility(View.VISIBLE);
                sumitReport.setVisibility(View.VISIBLE);
            }
        });

        sumitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etBug.getText().toString().equals("")) {
                    DateFormat format = new SimpleDateFormat("YYYYMMdd_hhmm a");
                    String date = format.format(new Date());
                    long time = System.currentTimeMillis();
                    repReference = FirebaseDatabase.getInstance().getReference("reportsComments").child(MainActivity.userId).child(date + time).child("report");
                    repReference.setValue(etBug.getText().toString());
                    Toast.makeText(getContext(), "Report Submitted, Thank you !", Toast.LENGTH_SHORT).show();
                    etBug.setText("");
                    etBug.setVisibility(View.GONE);
                    sumitReport.setVisibility(View.GONE);
                } else {
                    etBug.setError("Empty Field");
                    etBug.requestFocus();
                }
            }
        });

        return v;
    }
}
