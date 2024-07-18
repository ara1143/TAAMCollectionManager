package com.example.b07demosummer2024;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private EditText editTextLotNumber;
    private Button btnGenerateReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generate_report);

        editTextLotNumber = findViewById(R.id.editTextNumber);
        btnGenerateReport = findViewById(R.id.button3);

        btnGenerateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lotNumber = editTextLotNumber.getText().toString();
                generateReportByLotNumber(lotNumber);
            }
        });
    }

    private void generateReportByLotNumber(String lotNumber) {
        // Mock data for testing
        List<Collection> collections = new ArrayList<>();
        collections.add(new Collection("Collection 1", "123", "Art", "Renaissance", "A beautiful piece of art from the Renaissance period.", "https://example.com/pic1.jpg"));
        collections.add(new Collection("Collection 2", "456", "Sculpture", "Modern", "A modern sculpture made from recycled materials.", "https://example.com/pic2.jpg"));

        GenerateReport reportGenerator = new GenerateReport();
        reportGenerator.generateReportByLotNumber(lotNumber, collections);
    }
}

