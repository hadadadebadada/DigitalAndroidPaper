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
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.digitalpaper.Hauptmenue;
import com.example.digitalpaper.R;
//import com.example.digitalpaper.unterschrift;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class osnatelAnbieterwechsel extends AppCompatActivity {
    EditText et, et2, et3, et4, et5, et6, et7,et8, et9,et10, et11, et12, et13,et14,et15,et16,et17, et18,et19,et20, et21,et_vergessenerOrt;
    Button bt;
    Button bt_Save, bt_Clear;

    private Button btnClear, btnSave,btnSend;
    private File file;
    private LinearLayout signatureLLL;
    private View view;
    private signature mSignature;
    private Bitmap bitmap;
    private Bitmap bmp3, scaledbmp3;
    // Creating Separate Directory for saving Generated Images
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Signature/";
    // String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY  + "unterschrift.png";
    public final static String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osnatel_anbieterwechsel);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Anbieterwechselauftrag");

        Intent intent = getIntent();
        String name = intent.getStringExtra(osnatel1.EXTRA_TEXT);
        String vorname = intent.getStringExtra(osnatel1.EXTRA_TEXT2);
        String hnr = intent.getStringExtra(osnatel1.EXTRA_TEXT4);
        String strasse = intent.getStringExtra(osnatel1.EXTRA_TEXT5);
        String plz = intent.getStringExtra(osnatel1.EXTRA_TEXT6);
        String ort =intent.getStringExtra(osnatel1.EXTRA_TEXT7);




        et = findViewById(R.id.eT_vorherigerAnbieter);
        et2 = findViewById(R.id.eT_NameFirma);
        et2.setText(name);
        et3 = findViewById(R.id.eT_vorname1);
        et3.setText(vorname);
        et4 = findViewById(R.id.eT_Strasse1);
        et4.setText(strasse);
        et5 = findViewById(R.id.eT_Hausnummer1);
        et5.setText(hnr);
        et6 = findViewById(R.id.eT_Plz1);
        et6.setText(plz);

        et_vergessenerOrt= findViewById(R.id.eT_Ort1);
        et_vergessenerOrt.setText(ort);

        et7 = findViewById(R.id.eT_Ortskennzahl);
        et8 = findViewById(R.id.editTextPhone);
        et9 = findViewById(R.id.editTextPhone2);
        et10 = findViewById(R.id.editTextPhone3);
        et11 = findViewById(R.id.editTextPhone4);
        et12 = findViewById(R.id.editTextPhone5);
        et13 = findViewById(R.id.editTextPhone6);
        et14 = findViewById(R.id.editTextPhone7);
        et15 = findViewById(R.id.editTextPhone8);
        et16 = findViewById(R.id.editTextPhone9);
        et17 = findViewById(R.id.editTextPhone10);
        et18 = findViewById(R.id.editTextPhone11);
        et19 = findViewById(R.id.editTextPhone12);
        et20 = findViewById(R.id.editTextPhone13);
        et21= findViewById(R.id.eT_Datum1);

        bt = findViewById(R.id.button17);

        bt_Clear = findViewById(R.id.bt_clear3);
        bt_Save = findViewById(R.id.bt_save3);



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
        String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Signature/";
        // String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String StoredPath = DIRECTORY + "unterschrift.png";





        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createMyPDF();
        openHauptmenue();


    }

    public void openHauptmenue() {

        Intent intent = new Intent(this,Hauptmenue.class);
        startActivity(intent);
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



                Bitmap bm1 = pdfToBitmap(myFile1).get(4);

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

                canvas.drawText(et.getText().toString(), 1400, 470, myPaint); //name

                canvas.drawText(et2.getText().toString(), 600, 675, myPaint); //name
                canvas.drawText(et3.getText().toString(), 1590, 675, myPaint); //name

                canvas.drawText(et4.getText().toString(), 600, 750, myPaint); //str
                canvas.drawText(et5.getText().toString(), 1590, 750, myPaint); //hnr

                canvas.drawText(et6.getText().toString(), 600, 820, myPaint); //plz
                canvas.drawText(et_vergessenerOrt.getText().toString(), 1000, 820, myPaint); // WO IST DER ORT

//------------------------------RUFNUMMERMITNAHME
                canvas.drawText(et7.getText().toString(), 600, 1000, myPaint); //vorwahl

                canvas.drawText(et8.getText().toString(), 1050, 1000, myPaint); //name
                canvas.drawText(et9.getText().toString(), 1450, 1000, myPaint); //name
                canvas.drawText(et10.getText().toString(), 1880, 1000, myPaint); //name

                canvas.drawText(et11.getText().toString(), 1050, 1080, myPaint); //name
                canvas.drawText(et12.getText().toString(), 1450, 1080, myPaint); //name
                canvas.drawText(et13.getText().toString(), 1880, 1080, myPaint); //name

                canvas.drawText(et14.getText().toString(), 1050, 1165, myPaint); //name
                canvas.drawText(et15.getText().toString(), 1450, 1165, myPaint); //name
                canvas.drawText(et16.getText().toString(), 1880, 1165, myPaint); //name




                canvas.drawText(et17.getText().toString(), 600, 1290, myPaint); //name
                canvas.drawText(et18.getText().toString(), 1050, 1290, myPaint); //name
                canvas.drawText(et19.getText().toString(), 1450, 1290, myPaint); //name
                canvas.drawText(et20.getText().toString(), 1880, 1290, myPaint); //name


                //canvas.drawText(et21.getText().toString(), 240, 920, myPaint); //name





                bmp3 = BitmapFactory.decodeFile(StoredPath);
                scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 231, 200, true);


                canvas.drawBitmap(scaledbmp3,1800,1340,myPaint);


                //
                myPdfDocument.finishPage(myPage);


                String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/OsnaTelAnbieterwechsel.pdf";
                File myFile = new File(myFilePath1);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(myFile, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    et.setText("ERROR PDF konnte nicht gespeichert werden");
                }
                myPdfDocument.close();



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
}