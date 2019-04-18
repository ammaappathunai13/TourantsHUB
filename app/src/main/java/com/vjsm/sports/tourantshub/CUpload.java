package com.vjsm.sports.tourantshub;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import static android.app.Activity.RESULT_OK;

public class CUpload extends AppCompatActivity {
    public static final String Name = "name";
    public static final String Place = "place";
    public static final String District = "district";
    public static final String StDate = "startdate";
    public static final String ManDate = "mandate";
    public static final String MadDate = "maddate";
    public static final String PhoneNumber = "phoneNumber";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String phones;

    private static final String TAG = "MainActivity";
    private static  String fulllenphone;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int PICK_IMAGE_REQUEST = 1;
    private FirebaseFirestore fb = FirebaseFirestore.getInstance();
    EditText nn, pl, dt, sdate, matdate, mandate, mn;
    private AlertDialog.Builder builder;
    private Uri filepath;
    TextView image,maplocation;
    private ImageView maps;
    TextView anotherimage,success;
    private Button submit;
    private String phoned,clat,clon,slat,slon;
    private  String latitude,longitude;
    private ImageView tick,mapiocn,linttick;
    private ProgressBar progressBar;
    private Spinner sdistrict;
    private FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog,ss;
    private String name, place, district, startdate, madstartdate, manstartdate, imageloc,UserId;
    private StorageReference StorageRef;
    private String names,places,distritcs,startdates,madtdates,mantdates,phonenumbers;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupload);


        // shared preference
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences = getApplicationContext().getSharedPreferences("com.vjsm.sports.tourantshub", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        UserId=sharedPreferences.getString(getString(R.string.UserId),"");
        phoned = sharedPreferences.getString(getString(R.string.LoginUser_PhoneNumber), "");


        maps = (ImageView) findViewById(R.id.map);
        success=(TextView)findViewById(R.id.complete);
        linttick = (ImageView) findViewById(R.id.linktick);
        firebaseDatabase = FirebaseDatabase.getInstance();
        StorageRef = FirebaseStorage.getInstance().getReference();
        builder = new AlertDialog.Builder(this);
        tick = (ImageView) findViewById(R.id.tickk);
        nn = (EditText) findViewById(R.id.name);
        pl = (EditText) findViewById(R.id.place);
        sdate = (EditText) findViewById(R.id.datestart);
        matdate = (EditText) findViewById(R.id.madate);
        mandate = (EditText) findViewById(R.id.vedate);
        image = (TextView) findViewById(R.id.imgup);
        mn = (EditText) findViewById(R.id.mobile);
        anotherimage=(TextView)findViewById(R.id.another);
        submit = (Button) findViewById(R.id.upload);
        maplocation = (TextView) findViewById(R.id.maplink);
        sdistrict = (Spinner) findViewById(R.id.spinnner);
        ss = new ProgressDialog(CUpload.this);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CUpload.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.district_list));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // sdistrict.setBackgroundColor(getColor(R.color.black));
        sdistrict.setAdapter(myAdapter);
        progressDialog = new ProgressDialog(CUpload.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        clat = getIntent().getStringExtra("latitude");
        clon = getIntent().getStringExtra("langitude");
        slat = getIntent().getStringExtra("alatitude");
        slon = getIntent().getStringExtra("alangitude");

        names=getIntent().getStringExtra(Maped.SName);
        distritcs=getIntent().getStringExtra(Maped.SDistrict);
        places=getIntent().getStringExtra(Maped.SPlace);
        startdate=getIntent().getStringExtra(Maped.SSTDate);
        manstartdate=getIntent().getStringExtra(Maped.SMANDate);
        madstartdate=getIntent().getStringExtra(Maped.SMADDate);
        phonenumbers=getIntent().getStringExtra(Maped.PHONE);
        mn.setText(phoned);
        if (phoned != null && !phoned.isEmpty())   {
            fulllenphone=phoned;
            mn.setText(fulllenphone);
        }else  if(phonenumbers != null && !phonenumbers.isEmpty()){
            {
                fulllenphone=phonenumbers;
                mn.setText(fulllenphone);
            }
        }
        if (isServicesOK()) {
            init();
        }
        nn.setText(names);
        pl.setText(places);
        sdate.setText(startdate);
        matdate.setText(madstartdate);
        mandate.setText(manstartdate);
        if(clat==null||clon==null){
            if(slat==null||slon==null){
            }else{
                maplocation.setVisibility(View.INVISIBLE);
                success.setVisibility( View.VISIBLE);
                linttick.setVisibility(View.VISIBLE);
                latitude=slat;
                longitude=slon;
            }
        }else {
            maplocation.setVisibility(View.INVISIBLE);
            success.setVisibility(View.VISIBLE);
            linttick.setVisibility(View.VISIBLE);
            latitude=clat;
            longitude=clon;
        }
        anotherimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(CUpload.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        final String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        String date2 = (selectedday) + "/" + (selectedmonth + 1) + "/" + selectedyear;
                        sdate.setText(selectedday + "/" + (selectedmonth + 1) + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("போட்டி  தொடங்கும் நாள்");
                mDatePicker.show();
                matdate.requestFocus();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              String id=  generateString(6);


                name = nn.getText().toString().trim();
                place = pl.getText().toString().trim();
                startdate = sdate.getText().toString().trim();
                madstartdate = matdate.getText().toString().trim();
                manstartdate = mandate.getText().toString().trim();
                district = String.valueOf(sdistrict.getSelectedItem());

                if (TextUtils.isEmpty(name)) {
                    nn.setError("Fill your name");
                } else if (TextUtils.isEmpty(place)) {
                    pl.setError("Fill your Place");
                } else if (district.equals("போட்டி நடைபெறும் மாவட்டம்") || (district.equals("----தமிழில் தேர்வு செய்ய----"))) {
                    {
                        Toast.makeText(getApplicationContext(), "மாவட்டத்தை தேர்வு செய்", Toast.LENGTH_LONG).show();
                    }
                } else if (TextUtils.isEmpty(startdate)) {
                    sdate.setError("Fill Your StartDate");
                }
                else if(TextUtils.isEmpty(manstartdate)){
                    mandate.setError("Fill Entry Fees");
                }
                else {
                    progressDialog.show();
                    {
                        progressDialog.show();
                        Map<String, Object> user = new HashMap<>();
                        user.put("Name", name);
                        user.put("Place", place);
                        user.put("District", district);
                        user.put("Startdate", startdate);
                        user.put("Madtdate", madstartdate);
                        user.put("Mantdate", manstartdate);
                        user.put("Phone", fulllenphone);
                        user.put("Imageurl", imageloc);
                        user.put("MapLocationLa", latitude);
                        user.put("MapLocationLo", longitude);
                        user.put("UserId",UserId);
                        user.put("Id",id);
                        user.put("Title","கிரிக்கெட் போட்டி திருவிழா");
                        // Add a new document with a generated ID
                        fb.collection("TCRICKET").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.cancel();
                                display("success");
                            }
                        });

                    }

                }
            }
        });
    }

    private String generateString(int i) {

        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        Random random=new Random();
        for(int s=0;s<i;s++){
            char c=chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }


        return stringBuilder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        phoned = getIntent().getStringExtra("send");
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            // Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);

            StorageReference filepat = StorageRef.child("CricketPhotos").child(fulllenphone);
            ss = new ProgressDialog(CUpload.this);
            ss.setMessage("please wait Up loading...");
            ss.setCancelable(false);
            ss.show();
            filepat.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    image.setVisibility(View.INVISIBLE);
                    anotherimage.setVisibility(View.VISIBLE);
                    tick.setVisibility(View.VISIBLE);
                    ss.cancel();
                    Toast.makeText(getApplicationContext(), "image Uploded...", Toast.LENGTH_LONG).show();
                    Uri uri = taskSnapshot.getMetadata().getDownloadUrl();
                    imageloc = uri.toString();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ss.cancel();
                    Toast.makeText(getApplicationContext(), "upload failed..." + e.getMessage(), Toast.LENGTH_LONG).show();
                    tick.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void init() {
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nn.getText().toString().trim();
                place = pl.getText().toString().trim();
                startdate = sdate.getText().toString().trim();
                phoned = getIntent().getStringExtra("send");
                madstartdate = matdate.getText().toString().trim();
                manstartdate = mandate.getText().toString().trim();
                district = String.valueOf(sdistrict.getSelectedItem());
                Intent intent = new Intent(CUpload.this, Maped.class);
                intent.putExtra(Name,name);
                intent.putExtra(Place,place);
                intent.putExtra(StDate,startdate);
                intent.putExtra("Checker","cupload");
                intent.putExtra(District,district);
                intent.putExtra(ManDate,manstartdate);
                intent.putExtra(MadDate,madstartdate);
                intent.putExtra(PhoneNumber,phoned);
                startActivity(intent);
                maps.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(CUpload.this);
        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(CUpload.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    public void display(String code) {
        if (code.equals("error")) {
            builder.setTitle("Error");
            builder.setMessage(" நீங்கள் தேர்வு செய்த நாள் முடிந்துவிட்டது சரியான நாளை தேர்வு செய்க...");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    return;
                }
            });
        }
        else if(code.equals("same")) {
            builder.setTitle("Alert...!");
            builder.setMessage("இந்த மொபைல் எண்ணிற்கான கிரிக்கெட் போட்டி ஏற்கனவே பதிவு செய்யப்பட்டுள்ளது.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
        if(code.equals("correct")){
            builder.setTitle("Alert...!");
            builder.setMessage("நீங்கள் போட்டி நடக்கும் இடம்(Location)-ஜ தேர்வு செய்யவில்லை.");
            builder.setPositiveButton("Ok",null);
        }
        if(code.equals("success")){
            builder.setTitle("நன்றி!!!");
            builder.setMessage("உங்களுடைய கிரிக்கெட் வெற்றிகரமாக பதிவாகிவிட்டது.");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent s=new Intent(CUpload.this,HomePage_Loader_TourantsHub.class);


                    startActivity(s);
                    finish();

                }
            });


        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

