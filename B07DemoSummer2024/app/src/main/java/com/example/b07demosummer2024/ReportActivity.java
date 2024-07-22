package com.example.b07demosummer2024;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    private EditText editTextLotNumber;
    private Button btnGenerateLotReport;

    private EditText editTextName;
    private Button btnGenerateNameReport;

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
    }

    private void generateReportByLotNumber(String lotNumber) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByLotNumber(lotNumber);
    }

    private void generateReportByName(String name) {
        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByName(name);
    }
}



