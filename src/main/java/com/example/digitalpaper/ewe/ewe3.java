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

public class ewe3 extends AppCompatActivity {
CheckBox cb, cb2;
Button bt;
EditText editText;
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Signature/";
    // String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY  + "unterschrift.png";
    public final static String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());

    TextView tv_email;
    Button bt_Save, bt_Clear;

    private Button btnClear, btnSave,btnSend;
    private File file;
    private LinearLayout signatureLLL;
    private View view;
    private signature mSignature;
    private Bitmap bitmap;
    private Bitmap bmp3, scaledbmp3;
    public static final String EXTRA_TEXT1 = "com.example.digitalpaper.ewe3.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewe3_layout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("EWE Glasfaserauftrag 3");


        Intent intent = getIntent();
        String email = intent.getStringExtra(ewe2.EXTRA_TEXT);
        // tv_email=findViewById(R.id.textView60);
       //
        // tv_email.setText(email);


        cb = (CheckBox) findViewById(R.id.checkBox40);

        bt = (Button) findViewById(R.id.button17);
        editText = (EditText) findViewById(R.id.eT_Datum);

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
                String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/ewe.pdf";

                File myFile1 = new File(myFilePath);




                PdfDocument myPdfDocument = new PdfDocument();



                Bitmap bm1 = pdfToBitmap(myFile1).get(2); //--> seite in pdf lesen
                PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(2380, 3364, 1).create();// info erzeugen

                PdfDocument.Page myPage = myPdfDocument.startPage(pi);


                Canvas canvas = myPage.getCanvas();
                Paint myPaint = new Paint();
                bm1 = Bitmap.createScaledBitmap(bm1, 2380, 3364, true);
                myPaint.setColor(Color.BLACK);
                canvas.drawBitmap(bm1, 0, 0, null);


                //ALLES TEXTE HIER
                if (cb.isChecked()) {
                    myPaint.setColor(Color.BLACK);
                    canvas.drawCircle(240, 1956, 15, myPaint);
                }




                bmp3 = BitmapFactory.decodeFile(StoredPath);
                scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 431, 200, true);

                canvas.drawBitmap(scaledbmp3, 1650, 1850, myPaint);

                myPaint.setTextSize(42f);
                myPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(date, 1330, 1915, myPaint); //plz



                //
                myPdfDocument.finishPage(myPage);


                String myFilePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/EweGlasfaserAuftrag3.pdf";
                File myFile = new File(myFilePath1);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(myFile, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    editText.setText("ERROR PDF konnte nicht gespeichert werden");
                }
                myPdfDocument.close();
                openHauptmenue();



            }
        });

    }

    public void openHauptmenue() {

      //  String email = tv_email.getText().toString();
        Intent intent = new Intent(ewe3.this, Hauptmenue.class);

        Intent resultIntent = new Intent();
       // resultIntent.putExtra("email",email);
        setResult(RESULT_OK,resultIntent);
       // finish();


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