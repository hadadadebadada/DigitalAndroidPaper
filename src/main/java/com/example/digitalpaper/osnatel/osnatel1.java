package com.example.digitalpaper.osnatel;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.digitalpaper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SerializablePermission;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class osnatel1 extends AppCompatActivity {
    private EditText editText, editText8, editText9, editText16, editText10, editText17, editText18, editText19, editText20, editText21, editText22, editText26, editText24, editText25, editText28, editText29, editText30, editText31, editText23, editText27, editText32, editText33, editText35, editText36, anzVoiceboxen, msnNr, netzbetreiberName, abweichenderAnschlussinh, eT_datum1, eT_datum2, eT_datum3, eT_eigen1, eT_eigen2, eT_eigen3, eT_eigen4, eT_eigen5, eT_eigen6, eT_eigen7, eT_eigen8, eT_eigen9, eT_hnr, eT_hnr2, eT_hnr3;
    private Spinner spinner1, spinner2, spinner, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9;
    private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12, checkBox13, checkBox14, checkBox35, checkBox36, checkBox41, checkBox42, checkBox43, checkBox44, checkBox45, checkBox47, checkBox48;
    private Button myButton;
    private TextView tv_gpslocation;
    private static final String FILENAME = "sample.pdf";
    public static final String EXTRA_TEXT = "com.example.digitalpaper.osnatel.EXTRA_TEXT";
    public static final String EXTRA_TEXT2 = "com.example.digitalpaper.osnatel.EXTRA_TEXT2";
    public static final String EXTRA_TEXT3 = "com.example.digitalpaper.osnatel.EXTRA_TEXT3";
    public static final String EXTRA_TEXT4 = "com.example.digitalpaper.osnatel.EXTRA_TEXT4";
    public static final String EXTRA_TEXT5 = "com.example.digitalpaper.osnatel.EXTRA_TEXT5";
    public static final String EXTRA_TEXT6 = "com.example.digitalpaper.osnatel.EXTRA_TEXT6";
    public static final String EXTRA_TEXT7 = "com.example.digitalpaper.osnatel.EXTRA_TEXT7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.osnatel1_layout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("OsnaTel Glasfaserauftrag");


        Intent intent = getIntent();
        String gpslocation = intent.getStringExtra(com.example.digitalpaper.Hauptmenue.EXTRA_TEXT);
        tv_gpslocation = findViewById(R.id.textView54);
        tv_gpslocation.setText(gpslocation);

        editText = findViewById(R.id.editText);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);
        //editText16 = findViewById(R.id.editText16);
        editText10 = findViewById(R.id.editText10);
        editText17 = findViewById(R.id.editText17);
        editText18 = findViewById(R.id.editText18);
        editText19 = findViewById(R.id.editText19);
        editText20 = findViewById(R.id.editText20);
        editText21 = findViewById(R.id.editText21);
        editText22 = findViewById(R.id.editText22);
        editText26 = findViewById(R.id.editText26);

        editText24 = findViewById(R.id.editText24);
        editText25 = findViewById(R.id.editText25);
        editText28 = findViewById(R.id.editText28);
        editText29 = findViewById(R.id.editText29);
        editText30 = findViewById(R.id.editText30);
        editText31 = findViewById(R.id.editText31);

        editText23 = findViewById(R.id.editText23);
        editText27 = findViewById(R.id.editText27);
        editText32 = findViewById(R.id.editText32);
        editText33 = findViewById(R.id.editText33);

        editText35 = findViewById(R.id.editText35);

        editText36 = findViewById(R.id.editText36);

        netzbetreiberName = findViewById(R.id.netzbetreiberName);

        eT_datum1 = findViewById(R.id.editTextDate2);
        eT_datum2 = findViewById(R.id.editTextDate3);
        eT_datum3 = findViewById(R.id.editTextDate4);

        eT_eigen1 = findViewById(R.id.editTextTextPersonName);
        eT_eigen2 = findViewById(R.id.editTextTextPersonName2);
        eT_eigen3 = findViewById(R.id.editTextTextPersonName3);
        eT_eigen4 = findViewById(R.id.editTextTextPersonName4);
        eT_eigen5 = findViewById(R.id.editTextTextPersonName5);
        eT_eigen6 = findViewById(R.id.editTextTextPersonName6);
        eT_eigen7 = findViewById(R.id.editTextTextPersonName7);
        eT_eigen8 = findViewById(R.id.editTextTextPersonName8);
        eT_eigen9 = findViewById(R.id.editTextTextPersonName11);
        eT_hnr = findViewById(R.id.eT_hnr);
        eT_hnr2 = findViewById(R.id.eT_hnr2);


        myButton = (Button) findViewById(R.id.button4);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner = findViewById(R.id.spinner);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner8 = findViewById(R.id.spinner8);
        spinner9 = findViewById(R.id.spinner9);


        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);
        checkBox11 = findViewById(R.id.checkBox11);
        checkBox12 = findViewById(R.id.checkBox12);
        checkBox13 = findViewById(R.id.checkBox13);
        checkBox14 = findViewById(R.id.checkBox46);
        checkBox35 = findViewById(R.id.checkBox35);
        checkBox36 = findViewById(R.id.checkBox36);
        checkBox41 = findViewById(R.id.checkBox41);
        checkBox42 = findViewById(R.id.checkBox42);
        checkBox43 = findViewById(R.id.checkBox43);
        checkBox44 = findViewById(R.id.checkBox44);
        checkBox45 = findViewById(R.id.checkBox45);
        checkBox47 = findViewById(R.id.checkBox47);
        checkBox48 = findViewById(R.id.checkBox48);


        editText9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText22.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen1.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });
        editText24.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen2.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText25.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen7.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen3.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        eT_hnr2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen4.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText30.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen5.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen6.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });
        editText28.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen8.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });
        eT_hnr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_hnr2.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText26.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eT_eigen9.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        editText10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText26.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText29.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText19.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText30.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText31.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createMyPDF();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ArrayList<Bitmap> pdfToBitmap(File pdfFile) {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        try {
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));

            Bitmap bitmap;
            final int pageCount = renderer.getPageCount();
            for (int i = 0; i < pageCount; i++) {
                PdfRenderer.Page page = renderer.openPage(i);

                int width = getResources().getDisplayMetrics().densityDpi / 72 * page.getWidth();
                int height = getResources().getDisplayMetrics().densityDpi / 72 * page.getHeight();
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                bitmaps.add(bitmap);

                // close the page
                page.close();

            }

            // close the renderer
            renderer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bitmaps;

    }


    public void createMyPDF() {

        myButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                if (editText9.getText().toString().isEmpty()) {
                    editText9.setError("Namen einfügen!");
                } else if (editText10.getText().toString().isEmpty()) {
                    editText10.setError("Nachnamen einfügen");
                } else if (editText17.getText().toString().isEmpty()) {
                    editText17.setError("Straße einfügen!");
                } else if (eT_hnr.getText().toString().isEmpty()) {
                    eT_hnr.setError("Hausnummer einfügen");
                } else if (editText18.getText().toString().isEmpty()) {
                    editText18.setError("Geburtsdatum einfügen");
                } else if (editText19.getText().toString().isEmpty()) {
                    editText19.setError("PLZ einfügen");
                } else {

                    String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/osnatel.pdf";

                    File myFile1 = new File(myFilePath);


                    PdfDocument myPdfDocument = new PdfDocument();
                    Bitmap bm1 = pdfToBitmap(myFile1).get(0); //--> seite abfragen

                    PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(2380, 3364, 1).create();// info erzeugen

                    PdfDocument.Page myPage = myPdfDocument.startPage(pi);


                    Canvas canvas = myPage.getCanvas();
                    Paint myPaint = new Paint();
                    bm1 = Bitmap.createScaledBitmap(bm1, 2380, 3364, true);
                    myPaint.setColor(Color.BLACK);
                    canvas.drawBitmap(bm1, 0, 0, null);


//----------------------------------------------------------------PDF-Bearbeitung-------------------------------------------------------------------------------------------

                    myPaint.setTextSize(42f);
                    myPaint.setTextAlign(Paint.Align.LEFT);
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

                    canvas.drawText(tv_gpslocation.getText().toString(), 240, 3500, myPaint); //Vertragsnummer


                    if (spinner1.getSelectedItem().toString().contains("Neuauftrag")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(243, 740, 15, myPaint);

                    } else if (spinner1.getSelectedItem().toString().contains("Änderungsauftrag")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(457, 740, 15, myPaint);
                    } else {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(775, 740, 15, myPaint);
                    }

                    if (spinner2.getSelectedItem().toString().contains("schnellstmoeglich")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(240, 897, 15, myPaint);

                    }

                    canvas.drawText(editText.getText().toString(), 800, 820, myPaint); //Vertragsnummer
                    canvas.drawText(editText8.getText().toString(), 925, 905, myPaint); //DATUM
                    canvas.drawText(eT_datum1.getText().toString(), 270, 985, myPaint); //umzugsdatum
                    canvas.drawText(eT_datum2.getText().toString(), 580, 985, myPaint); //sperrung alteranschluss
                    canvas.drawText(eT_datum3.getText().toString(), 920, 985, myPaint); //kündigungsfrist alter anbieter


                    if (spinner.getSelectedItem().toString().contains("Frau")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(470, 1167, 15, myPaint);

                    } else if (spinner.getSelectedItem().toString().contains("Herr")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(350, 1170, 15, myPaint);
                    }


                    canvas.drawText(editText10.getText().toString(), 240, 1240, myPaint); //nachname

                    canvas.drawText(editText9.getText().toString(), 240, 1330, myPaint);//vorname //WTF

                    canvas.drawText(editText17.getText().toString(), 240, 1417, myPaint); //straße
                    canvas.drawText(editText18.getText().toString(), 900, 1330, myPaint);  //GEBDATUM
                    canvas.drawText(editText19.getText().toString(), 240, 1505, myPaint); //PLZ
                    canvas.drawText(editText20.getText().toString(), 480, 1505, myPaint); //ORT
                    canvas.drawText(editText21.getText().toString(), 240, 1592, myPaint); //KENNWORT
                    canvas.drawText(editText22.getText().toString(), 240, 1746, myPaint); //name
                    canvas.drawText(editText26.getText().toString(), 670, 1746, myPaint); //vorname
                    canvas.drawText(editText24.getText().toString(), 240, 1832, myPaint); //tel
                    canvas.drawText(editText25.getText().toString(), 680, 1832, myPaint);//handy
                    canvas.drawText(editText28.getText().toString(), 240, 1920, myPaint); //email
                    canvas.drawText(editText29.getText().toString(), 240, 2110, myPaint); //strr
                    canvas.drawText(editText30.getText().toString(), 240, 2200, myPaint); //plz
                    canvas.drawText(editText31.getText().toString(), 480, 2200, myPaint); //stadt
///----------------------------------------------Eigentümer

                    canvas.drawText(eT_eigen1.getText().toString(), 240, 2440, myPaint); //name
                    canvas.drawText(eT_eigen2.getText().toString(), 240, 2705, myPaint);//telnummer
                    canvas.drawText(eT_eigen3.getText().toString(), 240, 2531, myPaint); //straße
                    canvas.drawText(eT_eigen4.getText().toString(), 1000, 2531, myPaint); //hnr
                    canvas.drawText(eT_eigen5.getText().toString(), 240, 2610, myPaint); //PLZ
                    canvas.drawText(eT_eigen6.getText().toString(), 450, 2610, myPaint); //ORT
                    canvas.drawText(eT_eigen7.getText().toString(), 680, 2705, myPaint);//mobil
                    canvas.drawText(eT_eigen8.getText().toString(), 240, 2795, myPaint); //email
                    canvas.drawText(eT_eigen9.getText().toString(), 670, 2440, myPaint);//vorname


                    //----------------------------------------Seite2

                    if (spinner3.getSelectedItem().toString().contains("ist bereits vorhanden")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 805, 15, myPaint);
                    } else {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 957, 15, myPaint);
                    }

                    canvas.drawText(editText23.getText().toString(), 1550, 870, myPaint); //lageont
                    canvas.drawText(editText27.getText().toString(), 1630, 925, myPaint); //homeID

                    if (checkBox14.isChecked()) {
                        canvas.drawCircle(1290, 1417, 15, myPaint);

                    }
                    canvas.drawText(editText32.getText().toString(), 1290, 1278, myPaint); //name
                    canvas.drawText(editText33.getText().toString(), 1290, 1368, myPaint); //tel


                    canvas.drawText(editText35.getText().toString(), 1410, 1600, myPaint); //aktion
                    canvas.drawText(editText36.getText().toString(), 1430, 1660, myPaint); //bonuscode


                    //GIGAGLAS - Stärke
                    if (spinner4.getSelectedItem().toString().contains("75")) {
                        myPaint.setColor(Color.BLACK);

                        canvas.drawCircle(1290, 1750, 15, myPaint);

                    } else if (spinner4.getSelectedItem().toString().contains("150")) {
                        myPaint.setColor(Color.BLACK);

                        canvas.drawCircle(1513, 1750, 15, myPaint);
                    } else if (spinner4.getSelectedItem().toString().contains("300")) {
                        myPaint.setColor(Color.BLACK);

                        canvas.drawCircle(1290, 1800, 15, myPaint);

                    } else if (spinner4.getSelectedItem().toString().contains("500")) {
                        myPaint.setColor(Color.BLACK);

                        canvas.drawCircle(1513, 1800, 15, myPaint);
                    } else {
                        myPaint.setColor(Color.BLACK);

                        canvas.drawCircle(1746, 1800, 15, myPaint);
                    }

                    //TV-Optionen
                    if (checkBox35.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 1940, 15, myPaint);
                    }
                    if (checkBox36.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1680, 1940, 15, myPaint);

                    }
                    if (checkBox41.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1680, 1985, 15, myPaint);

                    }
                    if (checkBox42.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 1985, 15, myPaint);

                    }
                    //TELEFON OPTIONEN
                    if (checkBox43.isChecked()) {
                        myPaint.setColor(Color.BLACK);


                        canvas.drawCircle(1943, 2075, 15, myPaint);

                    }
                    if (checkBox44.isChecked()) {
                        myPaint.setColor(Color.BLACK);


                        canvas.drawCircle(1623, 2075, 15, myPaint);

                    }
                    if (checkBox45.isChecked()) {
                        myPaint.setColor(Color.BLACK);


                        canvas.drawCircle(1290, 2075, 15, myPaint);

                    }

                    //SICHERHEITSOPTIONEN
                    if (checkBox47.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2165, 15, myPaint);

                    }
                    if (checkBox48.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1622, 2165, 15, myPaint);

                    }


                    // HARDWARE
                    if (spinner8.getSelectedItem().toString().contains("Eigenes")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1568, 2250, 15, myPaint);

                    } else if (spinner8.getSelectedItem().toString().contains("Glasfaser")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2250, 15, myPaint);
                    }

                    if (checkBox12.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2300, 15, myPaint);
                    }

                    if (checkBox13.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1568, 2300, 15, myPaint);
                    }

                    //SPERREN
                    if (checkBox3.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1933, 2616, 15, myPaint);
                    }
                    if (checkBox4.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1623, 2616, 15, myPaint);
                    }
                    if (checkBox5.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1455, 2616, 15, myPaint);
                    }
                    if (checkBox6.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2616, 15, myPaint); //019
                    }
                    if (checkBox7.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1805, 2616, 15, myPaint); //012
                    }
                    if (checkBox8.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(2075, 2616, 15, myPaint); //115
                    }
                    if (checkBox9.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2658, 15, myPaint); //ausland
                    }
                    if (checkBox10.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1950, 2658, 15, myPaint);//R-gespärche
                    }
                    if (checkBox11.isChecked()) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2703, 15, myPaint); //sonstige
                    }

                    if (spinner9.getSelectedItem().toString().contains("neuen")) {

                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2850, 15, myPaint);
                    } else if (spinner9.getSelectedItem().toString().contains("Netzbetreiber")) {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1290, 2998, 15, myPaint);
                    } else {
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1330, 2960, 15, myPaint);
                        myPaint.setColor(Color.BLACK);
                        canvas.drawCircle(1585, 1265, 15, myPaint);
                    }

                    canvas.drawText(netzbetreiberName.getText().toString(), 1600, 2990, myPaint);

                    //  myPaint.setColor(Color.BLACK);


                    myPdfDocument.finishPage(myPage);


                    String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/OsnaTelGlasfaserauftrag1.pdf";
                    File myFile = new File(myFilePath1);

                    try {
                        myPdfDocument.writeTo(new FileOutputStream(myFile, false));
                    } catch (Exception e) {
                        e.printStackTrace();
                        editText.setText("ERROR PDF konnte nicht gespeichert werden");
                    }
                    myPdfDocument.close();
                    openAuftragGigaglas2();


                }
            }

        });


    }


    public void openAuftragGigaglas2() {
//17,19,20

        //falls user input nicht stimmt ==> if mit toast
        // Toast.makeText(this, " Plichtfelder müssen ausgefüllt sein",Toast.LENGTH_SHORT).show();
        EditText editText9 = findViewById(R.id.editText9);
        EditText editText10 = findViewById(R.id.editText10);
        EditText editText28 = findViewById(R.id.editText28);

        String name = editText9.getText().toString();
        String vorname = editText10.getText().toString();
        String email = editText28.getText().toString();
        String hnr = eT_hnr.getText().toString();
        String strasse = editText17.getText().toString();
        String plz = editText19.getText().toString();
        String ort = editText20.getText().toString();

        Intent intent = new Intent(osnatel1.this, osnatel2.class);
        intent.putExtra(EXTRA_TEXT, name);
        intent.putExtra(EXTRA_TEXT2, vorname);
        intent.putExtra(EXTRA_TEXT3, email);
        intent.putExtra(EXTRA_TEXT4, hnr);
        intent.putExtra(EXTRA_TEXT5, strasse);
        intent.putExtra(EXTRA_TEXT6, plz);
        intent.putExtra(EXTRA_TEXT7, ort);


//        Intent intent1 = new Intent(this, EWEsepa.class);
//        intent1.putExtra(EXTRA_TEXT,name);
//        intent1.putExtra(EXTRA_TEXT2, vorname);
//        intent1.putExtra(EXTRA_TEXT3, email);
//        intent1.putExtra(EXTRA_TEXT4,hnr);
//        intent1.putExtra(EXTRA_TEXT5,strasse);
//        intent1.putExtra(EXTRA_TEXT6,plz);
//        intent1.putExtra(EXTRA_TEXT7,ort);

        startActivity(intent);
    }


}
