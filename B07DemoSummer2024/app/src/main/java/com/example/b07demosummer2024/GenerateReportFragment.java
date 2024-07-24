package com.example.b07demosummer2024;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class GenerateReportFragment extends Fragment {

    private EditText editTextLotNumber;
    private Button btnGenerateLotReport;
    private EditText editTextName;
    private Button btnGenerateNameReport;
    private EditText editTextCategory;
    private Button btnGenerateCategoryReport;
    private EditText editTextPeriod;
    private Button btnGeneratePeriodReport;
    private EditText editTextCategoryPD;
    private Button btnGenerateCategoryReportPD;
    private EditText editTextPeriodPD;
    private Button btnGeneratePeriodReportPD;
    private Button btnGenerateReport;
    private Button btnGenerateReportPD;

    public static GenerateReportFragment newInstance() {
        return new GenerateReportFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generate_report, container, false);

        editTextLotNumber = view.findViewById(R.id.editTextNumber);
        btnGenerateLotReport = view.findViewById(R.id.button3);
        editTextName = view.findViewById(R.id.editTextText2);
        btnGenerateNameReport = view.findViewById(R.id.button5);
        editTextCategory = view.findViewById(R.id.editTextText3);
        btnGenerateCategoryReport = view.findViewById(R.id.button7);
        editTextCategoryPD = view.findViewById(R.id.editTextText9);
        btnGenerateCategoryReportPD = view.findViewById(R.id.button6);
        editTextPeriod = view.findViewById(R.id.editTextText10);
        btnGeneratePeriodReport = view.findViewById(R.id.button8);
        editTextPeriodPD = view.findViewById(R.id.editTextText11);
        btnGeneratePeriodReportPD = view.findViewById(R.id.button11);
        btnGenerateReport = view.findViewById(R.id.button10);
        btnGenerateReportPD = view.findViewById(R.id.button12);

        btnGenerateLotReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lotNumber = editTextLotNumber.getText().toString();
                generateReportByLotNumber(lotNumber);
            }
        });

        btnGenerateNameReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                generateReportByName(name);
            }
        });

        btnGenerateCategoryReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = editTextCategory.getText().toString();
                generateReportByCategory(category);
            }
        });

        btnGenerateCategoryReportPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = editTextCategoryPD.getText().toString();
                generateReportByCategoryPD(category);
            }
        });

        btnGeneratePeriodReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String period = editTextPeriod.getText().toString();
                generateReportByPeriod(period);
            }
        });

        btnGeneratePeriodReportPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String period = editTextPeriodPD.getText().toString();
                generateReportByPeriodPD(period);
            }
        });

        btnGenerateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateReportAll();
            }
        });

        btnGenerateReportPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateReportAllPD();
            }
        });

        return view;
    }

    private void generateReportByLotNumber(String lotNumber) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByLotNumber(lotNumber);
    }

    private void generateReportByName(String name) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByName(name);
    }

    private void generateReportByCategory(String category) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByCategory(category);
    }

    private void generateReportByCategoryPD(String category) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByCategoryPD(category);
    }

    private void generateReportByPeriod(String period) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByPeriod(period);
    }

    private void generateReportByPeriodPD(String period) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByPeriodPD(period);
    }

    private void generateReportAll() {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportAll();
    }

    private void generateReportAllPD() {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportAllPD();
    }
}

