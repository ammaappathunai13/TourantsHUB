package com.vjsm.sports.tourantshub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Ykshow extends AppCompatActivity implements CustomAdapter.OnItemClickListener{
    public static final String IMAGEU = "IMAGEURL";
    public static final String NAMESS = "NAME";
    public static final String  PLACE= "PLACE";
    public static final String DISTRICT = "DISTRICT";
    public static final String STARTDAATE = "STARTDATE";
    public static final String MANTDATE = "MANTDATE";
    public static final String MADTDATE = "MADTDATE";
    public static final String PHONE = "PHONE";
    public static final String LocationLa = "LocationLa";

    public static final String LocationLo = "LocationLo";

    ImageView count;
    private ImageView calender;
    private YKCustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private FirebaseFirestore fb;
    private ArrayList<YUsers> personNames;
    private ProgressBar pro;
    ProgressDialog progressDialog;
    private EditText searchView;
    String TAG = "hot";
    private RecyclerView mrecyclerView;
    String date;
    FirebaseDatabase firebaseDatabase;
    TextView vi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ykshow);
        fb = FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        calender=(ImageView)findViewById(R.id.cal);
        recyclerView = findViewById(R.id.recyclerView);
        personNames = new ArrayList<YUsers>();
        searchView = findViewById(R.id.search);
        vi=(TextView)findViewById(R.id.nodata);
        customAdapter = new YKCustomAdapter( personNames,this);
        final String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        final String date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        customAdapter.setOnItemClickListener(Ykshow.this);
        recyclerView.setAdapter(customAdapter);
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH-1);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        //   dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        fb.collection("KabaddiVideosTHUB").orderBy("no",Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        YUsers Yus = documentSnapshot.toObject(YUsers.class);
                        personNames.add(Yus);
                        if (personNames.size() > 0) {
                        }
                        progressDialog.cancel();
                        customAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }





    @Override
    public void onItemClick(int position) {
        Intent s=new Intent(Ykshow.this,YoutubeApi.class);
        s.putExtra("video",personNames.get(position).getVideoUrl());
        startActivity(s);
    }
}
