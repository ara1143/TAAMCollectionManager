package com.example.b07demosummer2024;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ReportActivity extends AppCompatActivity {
    //report by lot #
    private EditText editTextLotNumber;
    private Button btnGenerateLotReport;

    //report by name
    private EditText editTextName;
    private Button btnGenerateNameReport;

    //report by category
    private EditText editTextCategory;
    private Button btnGenerateCategoryReport;

    //report by category
    private EditText editTextPeriod;
    private Button btnGeneratePeriodReport;

    //report by category picture and description only
    private EditText editTextCategoryPD;
    private Button btnGenerateCategoryReportPD;

    //report by period picture and description only
    private EditText editTextPeriodPD;
    private Button btnGeneratePeriodReportPD;

    //report for all collections
    private Button btnGenerateReport;

    //report for all collections picture and description only
    private Button btnGenerateReportPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generate_report);

        //report by lot #
        editTextLotNumber = findViewById(R.id.editTextNumber);
        btnGenerateLotReport = findViewById(R.id.button3);

        //report by name
        editTextName = findViewById(R.id.editTextText2);
        btnGenerateNameReport = findViewById(R.id.button5);

        //report by Category
        editTextCategory = findViewById(R.id.editTextText3);
        btnGenerateCategoryReport = findViewById(R.id.button7);

        //report by Category (picture and description only)
        editTextCategoryPD = findViewById(R.id.editTextText9);
        btnGenerateCategoryReportPD = findViewById(R.id.button6);

        //report by Period
        editTextPeriod = findViewById(R.id.editTextText10);
        btnGeneratePeriodReport = findViewById(R.id.button8);

        //report by Category (picture and description only)
        editTextPeriodPD = findViewById(R.id.editTextText11);
        btnGeneratePeriodReportPD = findViewById(R.id.button11);

        //report for all collections
        btnGenerateReport = findViewById(R.id.button10);

        //report for all collections Picture and description only
        btnGenerateReportPD = findViewById(R.id.button12);

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



