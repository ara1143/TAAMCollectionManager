package com.example.b07demosummer2024;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class GenerateReport {

    private FirebaseDatabase db;
    private DatabaseReference collectionsRef;

    public GenerateReport() {
        db = FirebaseDatabase.getInstance("https://taamcollectionmanager-default-rtdb.firebaseio.com/");
        collectionsRef = db.getReference("collections");
    }

    public void generateReportByLotNumber(String lotNumber) {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        if (collection.lotNumber.equals(lotNumber)) {
                            collections.add(collection);
                        }
                    }
                }
                createPdf(lotNumber, collections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportByName(String name) {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        if (collection.name.equals(name)) {
                            collections.add(collection);
                        }
                    }
                }
                createPdf(name, collections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportByCategory(String category) {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        if (collection.category.equals(category)) {
                            collections.add(collection);
                        }
                    }
                }
                createPdf(category, collections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportByCategoryPD(String category) {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        if (collection.category.equals(category)) {
                            collections.add(collection);
                        }
                    }
                }
                createPdfPD(category, collections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportByPeriod(String period) {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        if (collection.period.equals(period)) {
                            collections.add(collection);
                        }
                    }
                }
                createPdf(period, collections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportByPeriodPD(String period) {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        if (collection.period.equals(period)) {
                            collections.add(collection);
                        }
                    }
                }
                createPdfPD(period, collections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportAll() {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        collections.add(collection);
                    }
                }
                if (!collections.isEmpty()) {
                    createPdf("All collections", collections);
                } else {
                    Log.d("GenerateReport", "No collections found to generate the report.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }

    public void generateReportAllPD() {
        collectionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Collection> collections = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Collection collection = snapshot.getValue(Collection.class);
                    if (collection != null) {
                        Log.d("GenerateReport", "Fetched collection: " + collection.name);
                        collections.add(collection);
                    }
                }
                if (!collections.isEmpty()) {
                    createPdfPD("All collections", collections);
                } else {
                    Log.d("GenerateReport", "No collections found to generate the report.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("GenerateReport", "Error fetching data", databaseError.toException());
            }
        });
    }


    private void createPdf(String lotNumber, List<Collection> collections) {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File pdfFile = new File(pdfPath, "report.pdf");

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Report for (Lot/name/period/category): " + lotNumber));
            document.add(new Paragraph(" "));

            for (Collection collection : collections) {
                Log.d("GenerateReport", "Adding collection to PDF: " + collection.name);
                document.add(new Paragraph("Name: " + collection.name));
                document.add(new Paragraph("Lot Number: " + collection.lotNumber));
                document.add(new Paragraph("Category: " + collection.category));
                document.add(new Paragraph("Period: " + collection.period));
                document.add(new Paragraph("Description: " + collection.description));
                document.add(new Paragraph(" "));

                if (collection.mediaUrl != null && !collection.mediaUrl.isEmpty()) {
                    Future<Bitmap> future = executorService.submit(new Callable<Bitmap>() {
                        @Override
                        public Bitmap call() {
                            try {
                                return Picasso.get().load(collection.mediaUrl).get();
                            } catch (IOException e) {
                                Log.e("GenerateReport", "Error loading image from URL", e);
                                return null;
                            }
                        }
                    });

                    try {
                        Bitmap bitmap = future.get();
                        if (bitmap != null) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            Image img = new Image(ImageDataFactory.create(baos.toByteArray()));
                            document.add(img);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        Log.e("GenerateReport", "Error processing image future", e);
                    }
                }
            }

            document.close();
            pdfDocument.close();
            writer.close();
            Log.d("GenerateReport", "PDF generated successfully");

        } catch (IOException e) {
            Log.e("GenerateReport", "Error generating PDF", e);
        } finally {
            executorService.shutdown();
        }
    }

    private void createPdfPD(String lotNumber, List<Collection> collections) {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File pdfFile = new File(pdfPath, "report.pdf");

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Report for (Lot/name/period/category): " + lotNumber + " (picture and description only)"));
            document.add(new Paragraph(" "));

            for (Collection collection : collections) {
                Log.d("GenerateReport", "Adding collection to PDF: " + collection.name);
                document.add(new Paragraph("Description: " + collection.description));
                document.add(new Paragraph(" "));

                if (collection.mediaUrl != null && !collection.mediaUrl.isEmpty()) {
                    Future<Bitmap> future = executorService.submit(new Callable<Bitmap>() {
                        @Override
                        public Bitmap call() {
                            try {
                                return Picasso.get().load(collection.mediaUrl).get();
                            } catch (IOException e) {
                                Log.e("GenerateReport", "Error loading image from URL", e);
                                return null;
                            }
                        }
                    });

                    try {
                        Bitmap bitmap = future.get();
                        if (bitmap != null) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            Image img = new Image(ImageDataFactory.create(baos.toByteArray()));
                            document.add(img);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        Log.e("GenerateReport", "Error processing image future", e);
                    }
                }
            }

            document.close();
            pdfDocument.close();
            writer.close();
            Log.d("GenerateReport", "PDF generated successfully");

        } catch (IOException e) {
            Log.e("GenerateReport", "Error generating PDF", e);
        } finally {
            executorService.shutdown();
        }
    }
}

