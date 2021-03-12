package com.example.digitalpaper.osnatel;

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

import com.example.digitalpaper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class osnatel2 extends AppCompatActivity {

    EditText et1, et2, et3, et4,et5 ,et6,et7,et8,et9,et10,et11, et12,et13, et14,et15,et16,et17,et18,et19,et20,et21,et22, et23,et24,et25,et26,et27,et28,et_vorname,et_nachname,eT_hnr,eT_tel, eT_fax;
    CheckBox cb1, cb2, cb3, cb4, cb5,cb6,cb7,cb8,cb9,cb10, cb11, cb12,cb13,cb14,cb15,cb16,cb17,cb18,cb19,cb20,cb21;
    TextView tv_datum;
    Button bt;
    Button bt_Save, bt_Clear;
    private Button btnClear, btnSave,btnSend;
    private File file;
    private LinearLayout signatureLLL;
    private View view;
    private signature mSignature;
    private Bitmap bitmap;
    private Bitmap bmp3, scaledbmp3;

    public final static String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());

    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Signature/";

    // String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY  + "unterschrift.png";
    public static final String EXTRA_TEXT = "com.example.digitalpaper.osnatel2.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.osnatel2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("OsnaTel Glasfaserauftrag 2");
        Intent intent = getIntent();
        String name = intent.getStringExtra(osnatel1.EXTRA_TEXT);
        String vorname = intent.getStringExtra(osnatel1.EXTRA_TEXT2);
        String email = intent.getStringExtra(osnatel1.EXTRA_TEXT3);
        String hnr = intent.getStringExtra(osnatel1.EXTRA_TEXT4);
        String strasse = intent.getStringExtra(osnatel1.EXTRA_TEXT5);
        String plz = intent.getStringExtra(osnatel1.EXTRA_TEXT6);
        String ort =intent.getStringExtra(osnatel1.EXTRA_TEXT7);

        et_vorname = findViewById(R.id.editTextTextPersonName13);
        et_nachname = findViewById(R.id.editTextTextPersonName12);

        et_vorname.setText(vorname);
        et_nachname.setText(name);
        et1 = findViewById(R.id.eT_strasse);
        et1.setText(strasse);
        eT_hnr = findViewById(R.id.eT_hnr_ewe1);
        eT_hnr.setText(hnr);

        et2 = (EditText) findViewById( R.id.eT_plz);
        et2.setText(plz);
        et3 = (EditText) findViewById( R.id.eT_ort);
        et3.setText(ort);
        et4 = (EditText) findViewById( R.id.eT_eineLeitung);
        et5 = (EditText) findViewById( R.id.et_zweiLeitungen);
        et6 = (EditText) findViewById( R.id.eT_dsl);
        et7 = (EditText) findViewById( R.id.et_andere);

        et8 = (EditText) findViewById( R.id.et_Vorwahl);
        et9 = (EditText) findViewById( R.id.eT_nr1);
        et10 = (EditText) findViewById( R.id.eT_nr2);
        et11= (EditText) findViewById( R.id.eT_nr3);
        et12 = (EditText) findViewById( R.id.eT_nr4);
        et13 = (EditText) findViewById( R.id.et_nr5);
        et14 = (EditText) findViewById( R.id.eT_nr6);
        et15 = (EditText) findViewById( R.id.eT_nr7);
        et16 = (EditText) findViewById( R.id.eT_nr8);
        et17 = (EditText) findViewById( R.id.eT_nr9);
        et18 = (EditText) findViewById( R.id.eT_nr10);
        et19 = (EditText) findViewById( R.id.eT_weitereNrn);
        et20 = (EditText) findViewById( R.id.eT_email);
        et21 = (EditText) findViewById( R.id.eT_rechnungsEmail);
        et21.setText(email);
        et22 = (EditText) findViewById( R.id.eT_abwEintrag);
        eT_tel = findViewById(R.id.editTextPhone14);
        eT_fax = findViewById(R.id.editTextPhone15);
        et23 = (EditText) findViewById( R.id.eT_vorNachName);
        et25 = (EditText) findViewById( R.id.eT_faxWerber);
        et26 = (EditText) findViewById( R.id.eT_kontoInhaber);
        et27 = (EditText) findViewById( R.id.eT_iban);
        et28 = (EditText) findViewById( R.id.eT_kreditinstitut);

        cb1 = (CheckBox) findViewById(R.id.checkBox14);
        cb2 = (CheckBox) findViewById(R.id.checkBox15);
        cb3 = (CheckBox) findViewById(R.id.checkBox16);
        cb4 = (CheckBox) findViewById(R.id.checkBox17);
        cb5 = (CheckBox) findViewById(R.id.checkBox18);
        cb6 = (CheckBox) findViewById(R.id.checkBox19);
        cb7 = (CheckBox) findViewById(R.id.checkBox20);
        cb8 = (CheckBox) findViewById(R.id.checkBox21);
        cb9 = (CheckBox) findViewById(R.id.checkBox22);
        cb10 = (CheckBox) findViewById(R.id.checkBox23);
        cb11 = (CheckBox) findViewById(R.id.checkBox24);
        cb12 = (CheckBox) findViewById(R.id.checkBox25);
        cb13 = (CheckBox) findViewById(R.id.checkBox26);
        cb14 = (CheckBox) findViewById(R.id.checkBox27);
        cb15 = (CheckBox) findViewById(R.id.checkBox28);
        cb16 = (CheckBox) findViewById(R.id.checkBox29);
        cb17 = (CheckBox) findViewById(R.id.checkBox30);
        cb18 = (CheckBox) findViewById(R.id.checkBox31);
        cb19 = (CheckBox) findViewById(R.id.checkBox32);
        cb20 = (CheckBox) findViewById(R.id.checkBox33);
        cb21 = (CheckBox) findViewById(R.id.checkBox34);

        tv_datum = findViewById(R.id.textView39);
        tv_datum.setText(date);

        bt = (Button) findViewById(R.id.button8);




        et21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //    editText31.setText(s); //intent -->
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bt_Save = findViewById(R.id.bt_save3);
        bt_Clear = findViewById(R.id.bt_clear3);

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
                String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/osnatel.pdf";

                File myFile1 = new File(myFilePath);




                PdfDocument myPdfDocument = new PdfDocument();



                Bitmap bm1 = pdfToBitmap(myFile1).get(1);

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


                bmp3 = BitmapFactory.decodeFile(StoredPath);
                scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 431, 200, true);

                canvas.drawBitmap(scaledbmp3, 600, 1050, myPaint);






                canvas.drawText(et_nachname.getText().toString(), 240, 920, myPaint); //name
                canvas.drawText(et_vorname.getText().toString(), 680, 920, myPaint); //vorname

                canvas.drawText(et1.getText().toString(), 240, 1305, myPaint); //strasse
                canvas.drawText(eT_hnr.getText().toString(), 960, 1305, myPaint); //hnr

                canvas.drawText(et2.getText().toString(), 240, 1385, myPaint); //plz
                canvas.drawText(et3.getText().toString(), 480, 1385, myPaint); //ort

                canvas.drawText(et4.getText().toString(), 240, 1540, myPaint); //eineleitung
                canvas.drawText(et5.getText().toString(), 240, 1590, myPaint); //zweileitung
                canvas.drawText(et6.getText().toString(), 240, 1640, myPaint); //dsl
                canvas.drawText(et7.getText().toString(), 460, 1702, myPaint); //adnere


                if (cb1.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 1780, 15, myPaint);
                }

                canvas.drawText(et8.getText().toString(), 350, 1850, myPaint); //vorwahl

                canvas.drawText(et9.getText().toString(), 290, 1950, myPaint); //adnere
                canvas.drawText(et10.getText().toString(), 290, 2002, myPaint); //adnere
                canvas.drawText(et11.getText().toString(), 290, 2055, myPaint); //adnere
                canvas.drawText(et12.getText().toString(), 290, 2107, myPaint); //adnere
                canvas.drawText(et13.getText().toString(), 290, 2160, myPaint); //adnere

                canvas.drawText(et14.getText().toString(), 800, 1950, myPaint); //adnere
                canvas.drawText(et15.getText().toString(), 800, 2002, myPaint); //adnere
                canvas.drawText(et16.getText().toString(), 800, 2055, myPaint); //adnere
                canvas.drawText(et17.getText().toString(), 800, 2107, myPaint); //adnere
                canvas.drawText(et18.getText().toString(), 800, 2160, myPaint); //adnere

                canvas.drawText(et19.getText().toString(), 1000, 2227, myPaint); //adnere

                //EMAIL

                canvas.drawText(et20.getText().toString(), 240, 2345, myPaint); //wunschemail



                if (cb2.isChecked()) {
                    myPaint.setColor(Color.BLACK); //weitere wunschemail
                    canvas.drawCircle(238, 2412, 15, myPaint);
                }



                if (cb3.isChecked()) {//kostenlose
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 2566, 15, myPaint);
                }
                canvas.drawText(et21.getText().toString(), 440, 2629, myPaint); //rechnugnsemail



                if (cb4.isChecked()) { //post
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 2662, 15, myPaint);
                }
                if (cb5.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 2705, 15, myPaint);
                }

                if (cb6.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 2815, 15, myPaint);
                }
                if (cb7.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 2850, 15, myPaint);
                }
                if (cb8.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(238, 2893, 15, myPaint);
                }

//--------------------------------------SEITE 2------------------------------------------------------------------


                if (cb18.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 985, 15, myPaint);
                }     if (cb13.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 1025, 15, myPaint);
                }


                if (cb14.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1512, 1027, 15, myPaint);
                }     if (cb12.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1512, 1060, 15, myPaint);
                }     if (cb15.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1512, 1090, 15, myPaint);
                }     if (cb17.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1512, 1130, 15, myPaint);
                }     if (cb16.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1512, 1160, 15, myPaint);
                }     if (cb11.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1512, 1193, 15, myPaint);
                }

                canvas.drawText(et22.getText().toString(), 1290, 1259, myPaint); //abweichender eintrag
                canvas.drawText(eT_tel.getText().toString(), 1290, 1352, myPaint); //abweichender eintrag
                canvas.drawText(eT_fax.getText().toString(), 1740, 1352, myPaint); //abweichender eintrag



                if (cb19.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 1420, 15, myPaint);
                }

                if (cb20.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 1514, 15, myPaint);
                }

                if (cb21.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 1560, 15, myPaint);
                }


///-------------------------KUNDENWERBENKUNDEN-------------------------------------------



                canvas.drawText(et23.getText().toString(), 1290, 1725, myPaint); //abweichender eintrag

                canvas.drawText(et25.getText().toString(), 1290, 1815, myPaint); //abweichender eintr


                ///--------------BANKVERBINDUNG-----------
                canvas.drawText(et26.getText().toString(), 1290, 2080, myPaint); //abweichender eintrag
                canvas.drawText(et27.getText().toString(), 1290, 2165, myPaint); //abweichender eintrag
                canvas.drawText(et28.getText().toString(), 1290, 2250, myPaint); //abweichender eintrag


                //---------------------------------Datum-----------

                canvas.drawText(tv_datum.getText().toString(), 260, 1165, myPaint); //abweichender eintrag



///--------------------DATENSCHUTZ---------------------------------------------


                if (cb10.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 2522, 15, myPaint);
                }

                if (cb9.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(1290, 2822, 15, myPaint);
                }
                //
                myPdfDocument.finishPage(myPage);


                String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/OsnaTelGlasfaserauftrag2.pdf";
                File myFile = new File(myFilePath1);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(myFile, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    et1.setText("ERROR PDF konnte nicht gespeichert werden");
                }
                myPdfDocument.close();
                openEwe3();




            }
        });



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

    public void openEwe3() {
        String email = et21.getText().toString();
        Intent intent = new Intent(osnatel2.this, osnatel3.class);
        intent.putExtra(EXTRA_TEXT,email);
        startActivity(intent);
    }

}