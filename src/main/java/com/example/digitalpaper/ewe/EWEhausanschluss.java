package com.example.digitalpaper.ewe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalpaper.Hauptmenue;
import com.example.digitalpaper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EWEhausanschluss extends AppCompatActivity {
    EditText et1, et2, et3, et4,et5 ,et6,et7,et8,et9,et10,et11, et12,et13, et14,et15,et16,et17,et18,et19,et20,et21;
    CheckBox cb1, cb2,cb3;
    TextView tv_gpslocation;
    Button bt;

    Button bt_Save, bt_Clear;

    //private Button btnClear, btnSave;
    private File file;
    private LinearLayout signatureLLL;
    private View view;
    private signature mSignature;
    private Bitmap bitmap;
    private Bitmap bmp3, scaledbmp3;
    // Creating Separate Directory for saving Generated Images
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Signature/";
    public final static String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());

    // String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY + "unterschrift.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewe_hausanschluss_layout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Hausanschluss");
        Intent intent = getIntent();
        String gpslocation = intent.getStringExtra(com.example.digitalpaper.Hauptmenue.EXTRA_TEXT);

        tv_gpslocation = findViewById(R.id.textView58);
        tv_gpslocation.setText(gpslocation);


        et1 = findViewById(R.id.HA_vorNachname);
        et2 = findViewById(R.id.HA_vertragsnummer);
        et3 = findViewById(R.id.HA_strasse);
        et20 = findViewById(R.id.editTextTextPersonName9);

        et4 = findViewById(R.id.HA_plz);
        et5 = findViewById(R.id.HA_ort);
        et6 = findViewById(R.id.HA_telefon);
        et7 = findViewById(R.id.HA_telefax);
        et8 =findViewById(R.id.HA_email);
        et9 = findViewById(R.id.HA_gebDatum);
        et10 = findViewById(R.id.HA_plz2);
        et11 = findViewById(R.id.HA_ort2);
        et12 = findViewById(R.id.HA_ortsteil);
        et13 = findViewById(R.id.HA_strasse2);
        et21 = findViewById(R.id.editTextTextPersonName10);

        et15 = findViewById(R.id.HA_preis);

        et19 = findViewById(R.id.HA_datum);
        cb1 = findViewById(R.id.checkBox37);
        cb2 = findViewById(R.id.checkBox38);
        cb3 = findViewById(R.id.checkBox39);
        bt = findViewById(R.id.button18);

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et10.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et11.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et13.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });
        et20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et21.setText(s);
            }

            @Override

            public void afterTextChanged(Editable s) {

            }
        });

        bt_Clear = findViewById(R.id.bt_clear4);
        bt_Save = findViewById(R.id.bt_save4);


        signatureLLL = (LinearLayout) findViewById(R.id.unterschrift_LL);
        mSignature = new signature(getApplicationContext(), null);
        mSignature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        signatureLLL.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        view = signatureLLL;

        bt_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignature.clear();
            }
        });

        bt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view.setDrawingCacheEnabled(true);
                mSignature.save(view, StoredPath);
                Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                //  printSignatureOnAllFiles();

            }
        });

        // Method to create Directory, if the Directory doesn't exists
        file = new File(DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }




        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createMyPDF();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ArrayList<Bitmap> pdfToBitmap(File pdfFile) { //ändern für seite2
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
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/eweha.pdf";

                File myFile1 = new File(myFilePath);




                PdfDocument myPdfDocument = new PdfDocument();



                Bitmap bm1 = pdfToBitmap(myFile1).get(0); //--> seite in pdf lesen

                PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(2380, 3364, 1).create();// info erzeugen

                PdfDocument.Page myPage = myPdfDocument.startPage(pi);


                Canvas canvas = myPage.getCanvas();
                Paint myPaint = new Paint();
                bm1 = Bitmap.createScaledBitmap(bm1, 2380, 3364, true);
                myPaint.setColor(Color.BLACK);
                canvas.drawBitmap(bm1, 0, 0, null);



                //ALLES TEXTE HIER


                myPaint.setTextSize(42f);
                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));





                canvas.drawText(et1.getText().toString(), 240, 770, myPaint);
                canvas.drawText(et2.getText().toString(), 240, 870, myPaint);
                canvas.drawText(et3.getText().toString(), 240, 965, myPaint);
                canvas.drawText(et20.getText().toString(), 1000, 965, myPaint); //Hnr

                canvas.drawText(et4.getText().toString(), 240, 1066, myPaint);//plz
                canvas.drawText(et5.getText().toString(), 460, 1066, myPaint);//ort

                canvas.drawText(et6.getText().toString(), 240, 1163, myPaint);//tel
                canvas.drawText(et7.getText().toString(), 740, 1163, myPaint);//fax

                canvas.drawText(et8.getText().toString(), 240, 1260, myPaint);//email
                canvas.drawText(et9.getText().toString(), 910, 1260, myPaint);//geb
//
                canvas.drawText(et9.getText().toString(), 240, 1425, myPaint);//ort
                canvas.drawText(et11.getText().toString(), 460, 1425, myPaint);//plz
                canvas.drawText(et12.getText().toString(), 240, 1525, myPaint);//ortsteul
                canvas.drawText(et13.getText().toString(), 240, 1625, myPaint);//strasse
                canvas.drawText(et21.getText().toString(), 1000, 1625, myPaint);//hnr



                if (cb1.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(240, 1780, 15, myPaint);
                }

                if (cb1.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(520, 1780, 15, myPaint);
                }


                canvas.drawText(et15.getText().toString(), 670, 1780, myPaint);
                canvas.drawText(et19.getText().toString(), 460, 2305, myPaint);







                if (cb3.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1270, 874, 15, myPaint);
                }


                canvas.drawText(tv_gpslocation.getText().toString(), 1270, 2750, myPaint);
                canvas.drawText(date,1270, 2600,myPaint );





                bmp3 = BitmapFactory.decodeFile(StoredPath);
                scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 431, 200, true);

                canvas.drawBitmap(scaledbmp3, 1650, 2550, myPaint);




                //
                myPdfDocument.finishPage(myPage);


                String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/EweHausanschluss.pdf";
                File myFile = new File(myFilePath1);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(myFile, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    et1.setText("ERROR PDF konnte nicht gespeichert werden");
                }
                myPdfDocument.close();
                openHauptmenue();




            }
        });

    }

    public void openHauptmenue() {

        Intent intent = new Intent(this, Hauptmenue.class);
        startActivity(intent);
    }

    public class signature extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();
        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v, String StoredPath) {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(signatureLLL.getWidth(), signatureLLL.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap); //pdf bitmap übergeben

            try {
                // Output the file


                //  String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/signature.pdf";
                // File myFile = new File(myFilePath1);

                FileOutputStream mFileOutStream = new FileOutputStream(StoredPath);
                v.draw(canvas);

                // Convert the output file to Image such as .png
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);

                mFileOutStream.flush();
                mFileOutStream.close();

            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }

        }

        public void clear() {
            path.reset();
            invalidate();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }
}