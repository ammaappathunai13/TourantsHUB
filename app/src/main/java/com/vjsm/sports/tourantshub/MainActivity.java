package com.vjsm.sports.tourantshub;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity  {
    private static  final String TAG="MainActivity";
    private static final int ERROR_CODE=9001;
    private ImageButton refresh;
    TextView noi, noit, yit, enable, gg, Cs,Ks,Fs,Vs,cv,kv,fv,vv;
    EditText phone;

    private String number;
    Button submit;
    Button Cview,Kview,Fview,Vview;
    private FusedLocationProviderClient client;
    private FirebaseFirestore fb = FirebaseFirestore.getInstance();
    private String Phones;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView marque = (TextView) findViewById(R.id.scroller);
        fb.collection("Jallikattumarque").document("Tourants").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String s = String.valueOf(documentSnapshot.get("marque"));
                marque.setText(s);
                marque.setSelected(true);


            }
        });
        cv=(TextView)findViewById(R.id.cvid);
        kv=(TextView)findViewById(R.id.kvid);
        fv=(TextView)findViewById(R.id.fvid);
        vv=(TextView)findViewById(R.id.vvid);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent s=new Intent(MainActivity.this,Ycshow.class);
startActivity(s);
            }
        });
        kv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent ss=new Intent(MainActivity.this,Ykshow.class);
startActivity(ss);
            }
        });
        fv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent sss=new Intent(MainActivity.this,Yfshow.class);
startActivity(sss);
            }
        });
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent ssss=new Intent(MainActivity.this,Yvshow.class);
startActivity(ssss);
            }
        });
        submit=(Button)findViewById(R.id.click);
        Cview=(Button) findViewById(R.id.Cviewes);
        Fview=(Button) findViewById(R.id.Fviewes);
        Kview=(Button) findViewById(R.id.Kviewes);
        Vview=(Button) findViewById(R.id.Vviewes);

        phone=(EditText)findViewById(R.id.phone);
        Cs=(TextView)findViewById(R.id.Cshow);
        refresh=(ImageButton)findViewById(R.id.refer);
        noi=(TextView)findViewById(R.id.nointer);
        noit=(TextView)findViewById(R.id.nointertamil);
        enable=(TextView)findViewById(R.id.enableinternet);
        yit=(TextView)findViewById(R.id.phonetamil);


        enable=(TextView)findViewById(R.id.enableinternet);
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        Cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this,CShow.class);
                startActivity(a);
            }
        });
        Fview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa=new Intent(MainActivity.this,FShow.class);
                startActivity(aa);
            }
        });
        Kview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aaa=new Intent(MainActivity.this,KShow.class);
                startActivity(aaa);
            }
        });
        Vview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aaaa=new Intent(MainActivity.this,VShow.class);
                startActivity(aaaa);
            }
        });
        if(INconectd()) {
            Toast.makeText(getApplicationContext(), "INTERNET CONNECTED", Toast.LENGTH_SHORT).show();
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    number = phone.getText().toString();
                    if (number.length() < 10) {
                        phone.setError("Fill 10 Digit");
                    } else {
                        progressDialog.show();
                        CollectionReference clf = fb.collection("TourantsAuth");
                        Query q1 = clf.whereEqualTo("PhoneNumber", number);
                        q1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                boolean isExisting = false;
                                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    String Numbers = documentSnapshot.getString("PhoneNumber");
                                    if(number.equals(Numbers)){
                                        isExisting=true;
                                        Intent m = new Intent(MainActivity.this,Home.class);
                                        m.putExtra("phones",number);
                                        startActivity(m);
                                        finish();
                                    }
                                }
                                if(!isExisting){
                                    String
                                            ph = "+91" + number;
                                    Intent send = new Intent(MainActivity.this, CodeVerification.class);
                                    send.putExtra("phone", ph);
                                    send.putExtra("phonenull",number);
                                    startActivity(send);
                                    finish();
                                }
                            }
                        });
                    }
                }
            });
            progressDialog.cancel();
        }
        else
        {
            Cs.setVisibility(View.INVISIBLE);
          //  Ks.setVisibility(View.INVISIBLE);
          //  Fs.setVisibility(View.INVISIBLE);
          //  Vs.setVisibility(View.INVISIBLE);
            Cview.setVisibility(View.INVISIBLE);
            Fview.setVisibility(View.INVISIBLE);
            Kview.setVisibility(View.INVISIBLE);
            Vview.setVisibility(View.INVISIBLE);
            enable.setVisibility(View.VISIBLE);
            submit.setVisibility(View.INVISIBLE);
            phone.setVisibility(View.INVISIBLE);
            cv.setVisibility(View.INVISIBLE);
            kv.setVisibility(View.INVISIBLE);
            fv.setVisibility(View.INVISIBLE);
            yit.setVisibility(View.INVISIBLE);
            vv.setVisibility(View.INVISIBLE);
            noi.setVisibility(View.VISIBLE);
            noit.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.VISIBLE);
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
        }
    }




    private Boolean INconectd() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("TourantsHUB");

        builder.setIcon(R.drawable.tourants);
        builder.setMessage("Do you Want  Exit ...?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        recreate();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



}
