package com.example.digitalpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.digitalpaper.ewe.EWEanbieterwechsel;
import com.example.digitalpaper.ewe.EWEhausanschluss;
import com.example.digitalpaper.ewe.EWEsepa;
import com.example.digitalpaper.ewe.ewe1;
import com.example.digitalpaper.ewe.ewe3;
import com.example.digitalpaper.osnatel.osnatel1;
import com.example.digitalpaper.osnatel.osnatelAnbieterwechsel;
import com.example.digitalpaper.osnatel.osnatelHausanschluss;
import com.example.digitalpaper.osnatel.osnatelSepa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Hauptmenue extends AppCompatActivity {
    public Button button, button1, button2, button3, button12, button5, button6, button7, button9, button10, button11, button13, button14, button19, button21;
    public TextView tv, tv2, tv3;
    public EditText eT_gps, eT_email,eT_email2;
    LocationManager locationManager;
    LocationListener locationListener;
    public static final String EXTRA_TEXT = "com.example.digitalpaper.hauptmenue.EXTRA_TEXT";

  //  String email = (( this.getApplication()).getEmail();
    public final static String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptmenue_layout);

        //initialisation
        button = findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button5);
       button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button9 = (Button) findViewById(R.id.button9);

        button10 = findViewById(R.id.button10);

        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        tv = findViewById(R.id.textView37);
        tv.setText(date);
        tv2 = findViewById(R.id.textView38);

        eT_email = findViewById(R.id.editTextTextPersonName15);
        eT_email2 = findViewById(R.id.editTextTextPersonName14);


        Intent intent = getIntent();
        String email3 = intent.getStringExtra(ewe3.EXTRA_TEXT1);


        eT_gps = findViewById(R.id.eT_gps);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Geocoder geocoder = new Geocoder(Hauptmenue.this);

                try {
                    List<Address> adresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    eT_gps.setText(adresses.get(0).getAddressLine(0));
                } catch (Exception e) {
                    eT_gps.setText("FEHLER");

                }



            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        {
            configureButton();
        }
        locationManager.requestLocationUpdates("gps", 500000, 50, locationListener);


//EWE
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEWE_Controller();
            }
        }); // ewe1

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEWEsepa();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEWEanbieterwechsel();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEWEhausanschluss();
            }
        });


        ///--------------------------------------------------EMAIL-------------------------------------------------------///

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String filename = "EweGlasfaserauftrag1.pdf";
                String filename2 = "EweGlasfaserauftrag2.pdf";
                String filename3 = "EweGlasfaserauftrag3.pdf";
                String filename4 = "EweAnbieterwechsel.pdf";
                String filename5 = "EweSepa.pdf";
                String filename6 = "EweHausanschluss.pdf";



                File testFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename);
                File testFile2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename2);
                File testFile3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename3);
                File testFile4 = new  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename4);
                File testFile5 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename5);
                File testFile6 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename6);




                // The second parameter is the name of authorities.
//                Uri path = FileProvider.getUriForFile(Hauptmenue.this,
//                        "com.example.digitalpaper.fileprovider", testpdf);

                Uri path = Uri.fromFile(testFile);
                Uri path2 = Uri.fromFile(testFile2);
                Uri path3 = Uri.fromFile(testFile3);
                Uri path4 = Uri.fromFile(testFile4);
                Uri path5 = Uri.fromFile(testFile5);
                Uri path6 = Uri.fromFile(testFile6);



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //path = FileProvider.getUriForFile(Hauptmenue.this, BuildConfig.APPLICATION_ID + ".provider",testFile);
                    path = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile);
                    path2 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile2);
                    path3 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile3);
                    path4 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile4);
                    path5 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile5);
                    path6 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile6);



                }


                // Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                emailIntent.setType("application/pdf");
                String to[] = {"artur_pfeifer1@gmx.de", eT_email.getText().toString()};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EWE-Auftragsformulare");

                ArrayList<Uri> uris = new ArrayList<Uri>();
                uris.add(path);
                uris.add(path2);
                uris.add(path3);
                uris.add(path4);
                uris.add(path5);
                uris.add(path6);

                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);


                // emailIntent.putExtra(Intent.EXTRA_STREAM, path);


                startActivity(Intent.createChooser(emailIntent, "Send email..."));


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        deleteFiles();

                    }
                }, 500000);


            }
        });


        //----------------------------------------------------OSNATEL SENDEN -----------------------------------------------------------//


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = "OsnaGlasfaserauftrag1.pdf";
                String filename2 = "OsnaTelGlasfaserauftrag2.pdf";
                String filename3 = "OsnaTelGlasfaserauftrag3.pdf";
                String filename4 = "OsnaTelAnbieterwechsel.pdf";
                String filename5 = "OsnaTelSepa.pdf";
                String filename6 = "OsnaTelHausanschluss.pdf";



                File testFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename);
                File testFile2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename2);
                File testFile3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename3);
                File testFile4 = new  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename4);
                File testFile5 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename5);
                File testFile6 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), filename6);




                // The second parameter is the name of authorities.
//                Uri path = FileProvider.getUriForFile(Hauptmenue.this,
//                        "com.example.digitalpaper.fileprovider", testpdf);

                Uri path = Uri.fromFile(testFile);
                Uri path2 = Uri.fromFile(testFile2);
                Uri path3 = Uri.fromFile(testFile3);
                Uri path4 = Uri.fromFile(testFile4);
                Uri path5 = Uri.fromFile(testFile5);
                Uri path6 = Uri.fromFile(testFile6);



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //path = FileProvider.getUriForFile(Hauptmenue.this, BuildConfig.APPLICATION_ID + ".provider",testFile);
                    path = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile);
                    path2 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile2);
                    path3 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile3);
                    path4 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile4);
                    path5 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile5);
                    path6 = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()), BuildConfig.APPLICATION_ID + ".fileprovider", testFile6);



                }


                // Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                emailIntent.setType("application/pdf");
                String to[] = {"artur_pfeifer1@gmx.de", eT_email2.getText().toString()};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "OsnaTel-Auftragsformulare");

                ArrayList<Uri> uris = new ArrayList<Uri>();
                uris.add(path);
                uris.add(path2);
                uris.add(path3);
                uris.add(path4);
                uris.add(path5);
                uris.add(path6);

                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);


                // emailIntent.putExtra(Intent.EXTRA_STREAM, path);


                startActivity(Intent.createChooser(emailIntent, "Send email..."));


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        deleteFiles2();

                    }
                }, 500000);



            }
        });

///----------------------------------------------------_GPS-Tracking---------------------------------------------------------------//
        if (ActivityCompat.checkSelfPermission(Hauptmenue.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Hauptmenue.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 10);
            {
                configureButton();
            }
            return;
        }


        locationManager.requestLocationUpdates("gps", 6000000, 0, locationListener);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (ActivityCompat.checkSelfPermission(Hauptmenue.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Hauptmenue.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                    }, 10);
                    return;
                }
                openGPSTrackingDemo();


            }

        });


//-------------------------------------------------------OSNATEL--------------------------------------------------///


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOsnaTel_Controller();
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOsnaTel_Sepa();
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOsnaTel_Anbieterwechsel();
            }
        });


        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOsnaTel_Hausanschluss();
            }
        });


    }   ///-------------------------------------------------------------------------------------------- END OF ON CREATE



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    public void configureButton() {

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 600000, 0, locationListener);

            }
        });

    }

//--------------------------OPEN EWE-------------------------------------------------------------------------------
    public void openEWE_Controller() {

        String gpslocation = eT_gps.getText().toString();
        Intent intent = new Intent(this, ewe1.class);
        intent.putExtra(EXTRA_TEXT, gpslocation);
        startActivityForResult(intent, 1);

    }

    public void openGPSTrackingDemo() {
        Intent intent = new Intent(this, GPSTrackingDemo.class);
        startActivity(intent);
    }


    public void openEWEsepa() {
        String gpslocation = eT_gps.getText().toString();

        Intent intent = new Intent(this, EWEsepa.class);
        intent.putExtra(EXTRA_TEXT, gpslocation);


        startActivity(intent);
    }

    public void openEWEanbieterwechsel() {

        Intent intent = new Intent(this, EWEanbieterwechsel.class);
        startActivity(intent);
    }

    public void openEWEhausanschluss() {
        String gpslocation = eT_gps.getText().toString();

        Intent intent = new Intent(this, EWEhausanschluss.class);
        intent.putExtra(EXTRA_TEXT, gpslocation);

        startActivity(intent);
    }

//----------------------------------------------OPEN OSNATEL---------------------------------------------------------------

    public void openOsnaTel_Controller() {
        Intent intent1 = new Intent(this, osnatel1.class);
        startActivity(intent1);
    }

    public void openOsnaTel_Sepa(){
        Intent intent = new Intent(this, osnatelSepa.class);
        startActivity(intent);
    }

    public void openOsnaTel_Anbieterwechsel(){
        Intent intent = new Intent (this, osnatelAnbieterwechsel.class);
        startActivity(intent);


    }

    public void openOsnaTel_Hausanschluss(){
        Intent intent = new Intent (this, osnatelHausanschluss.class);

        startActivity(intent);


    }


    public void deleteFiles() {


        String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        File myFile1 = new File(myFilePath+"/EweGlasfaserauftrag1.pdf");
        File myFile2 = new File(myFilePath+"/EweGlasfaserauftrag2.pdf");
        File myFile3 = new File(myFilePath+"/EweGlasfaserauftrag3.pdf");
        File myFile4 = new File(myFilePath+"/EweSepa.pdf");
        File myFile5 = new File(myFilePath+"/EweAnbieterwechsel.pdf");
        File myFile6 = new File(myFilePath+"/EweHausanschluss.pdf");

        myFile1.delete();
        myFile2.delete();
        myFile3.delete();
        myFile4.delete();
        myFile5.delete();
        myFile6.delete();


    }

    public void deleteFiles2() {


        String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        File myFile1 = new File(myFilePath+"/OsnatelGlasfaserauftrag1.pdf");
        File myFile2 = new File(myFilePath+"/OsnatelGlasfaserauftrag2.pdf");
        File myFile3 = new File(myFilePath+"/OsnatelGlasfaserauftrag3.pdf");
        File myFile4 = new File(myFilePath+"//OsnatelSepa.pdf");
        File myFile5 = new File(myFilePath+"/OsnatelAnbieterwechsel.pdf");
        File myFile6 = new File(myFilePath+"/OsnatelHausanschluss.pdf");

        myFile1.delete();
        myFile2.delete();
        myFile3.delete();
        myFile4.delete();
        myFile5.delete();
        myFile6.delete();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("email");
                tv3.setText(result);
            }
            if (resultCode == RESULT_CANCELED) {
                tv3.setText("FEHLER");
            }
        }
    }
}