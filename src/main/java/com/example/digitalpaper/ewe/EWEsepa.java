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
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class EWEsepa extends AppCompatActivity {
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9;
    Button bt;

    Button bt_Save, bt_Clear;

    TextView tv_gpslocation;

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

    public static final String EXTRA_TEXTT = "com.example.digitalpaper.ewe.EXTRA_TEXT";
    public static final String EXTRA_TEXTT2 = "com.example.digitalpaper.ewe.EXTRA_TEXT2";
    public static final String EXTRA_TEXTT4 = "com.example.digitalpaper.ewe.EXTRA_TEXT4";
    public static final String EXTRA_TEXTT5 = "com.example.digitalpaper.ewe.EXTRA_TEXT5";
    public static final String EXTRA_TEXTT6 = "com.example.digitalpaper.ewe.EXTRA_TEXT6";
    public static final String EXTRA_TEXTT7 = "com.example.digitalpaper.ewe.EXTRA_TEXT7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sepa_layout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("SEPA-Lastschrift");

        Intent intent = getIntent();
        String gpslocation = intent.getStringExtra(com.example.digitalpaper.Hauptmenue.EXTRA_TEXT);
        tv_gpslocation = findViewById(R.id.textView59);
        tv_gpslocation.setText(gpslocation);


        et1 = findViewById(R.id.eT_Name);
        et2 = findViewById(R.id.eT_Vorname);
        et3 = findViewById(R.id.eT_Strasse);
        et4 = findViewById(R.id.eT_Hausnummer);
        et5 = findViewById(R.id.eT_Plz);
        et6 = findViewById(R.id.eT_Ort2);
        et7 = findViewById(R.id.eT_Kreditinstitut);
        et8 = findViewById(R.id.eT_Iban);
        et9 = findViewById(R.id.eT_Datum);

        bt = findViewById(R.id.button16);

        bt_Save = findViewById(R.id.bt_save3);
        bt_Clear = findViewById(R.id.bt_clear3);


        signatureLLL = (LinearLayout) findViewById(R.id.unterschrift_LL1);
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

//        if(file.exists()){
//        bmp3 = BitmapFactory.decodeFile(StoredPath);
//        scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 231, 200, true); }


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
                //unterschrift ----------------------
                view.setDrawingCacheEnabled(true);
                mSignature.save(view, StoredPath);
                Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                //-------------unterschrift fertig
                String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/ewe.pdf";

                File myFile1 = new File(myFilePath);


                PdfDocument myPdfDocument = new PdfDocument();


                Bitmap bm1 = pdfToBitmap(myFile1).get(3); //--> seite in pdf lesen

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




                canvas.drawText(et1.getText().toString(), 200, 1483, myPaint);
                canvas.drawText(et2.getText().toString(), 200, 1593, myPaint);
                canvas.drawText(et3.getText().toString(), 200, 1715, myPaint);
                canvas.drawText(et4.getText().toString(), 980, 1715, myPaint);
                canvas.drawText(et5.getText().toString(), 200, 1830, myPaint);
                canvas.drawText(et6.getText().toString(), 450, 1830, myPaint);


                canvas.drawText(et7.getText().toString(), 740, 2140, myPaint);
                canvas.drawText(et8.getText().toString(), 740, 2330, myPaint);
                canvas.drawText(et9.getText().toString(), 740, 2430, myPaint);





                bmp3 = BitmapFactory.decodeFile(StoredPath);
                scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 431, 200, true);

                canvas.drawBitmap(scaledbmp3, 1400, 2550, myPaint);

                canvas.drawText(date,200,2735,myPaint);
                canvas.drawText(tv_gpslocation.getText().toString(),200,2850,myPaint);


                //
                myPdfDocument.finishPage(myPage);


                String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/EweSepa.pdf";
                File myFile = new File(myFilePath1);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(myFile, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    et1.setText("ERROR PDF konnte nicht gespeichert werden");
                }
                myPdfDocument.close();
                openEWEanbieterwechsel();


            }
        });

    }

    public void openEWEanbieterwechsel() {


        String name = et1.getText().toString();
        String vorname= et2.getText().toString();
        String hnr = et4.getText().toString();
        String strasse = et3.getText().toString();
        String plz = et5.getText().toString();
        String ort = et6.getText().toString();





        Intent intent = new Intent(this, EWEanbieterwechsel.class);


        intent.putExtra(EXTRA_TEXTT,name);
        intent.putExtra(EXTRA_TEXTT2, vorname);
        intent.putExtra(EXTRA_TEXTT4,hnr);
        intent.putExtra(EXTRA_TEXTT5,strasse);
        intent.putExtra(EXTRA_TEXTT6,plz);
        intent.putExtra(EXTRA_TEXTT7,ort);

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