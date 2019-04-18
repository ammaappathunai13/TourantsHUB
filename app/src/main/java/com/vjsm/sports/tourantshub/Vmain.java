package com.vjsm.sports.tourantshub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Vmain extends AppCompatActivity {
    private Button up,vie;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    ImageView VideoIcons,HomeIcons,LogoutIcons;
    TextView VideoText,Hometext,Logouttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vmain);
        up=(Button)findViewById(R.id.upload);
        vie=(Button)findViewById(R.id.view);
        builder=new AlertDialog.Builder(this);
        VideoIcons=(ImageView) findViewById(R.id.videoIcon);
        HomeIcons=(ImageView)findViewById(R.id.HomeIcon);
        LogoutIcons=(ImageView)findViewById(R.id.LogoutIcon);

        VideoText=(TextView)findViewById(R.id.VideoText);
        Hometext=(TextView)findViewById(R.id.HomeText);
        Logouttext=(TextView)findViewById(R.id.LogoutText);


        HomeIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss=new Intent(Vmain.this,HomePage_Loader_TourantsHub.class);
                startActivity(ss);
                ActivityCompat.finishAffinity(Vmain.this);            }
        });
        Hometext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss=new Intent(Vmain.this,HomePage_Loader_TourantsHub.class);
                startActivity(ss);
                ActivityCompat.finishAffinity(Vmain.this);            }
        });

        VideoIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vs=new Intent(Vmain.this,Yvshow.class);
                startActivity(vs);
            }
        });
        VideoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sm=new Intent(Vmain.this,Yvshow.class);
                startActivity(sm);
            }
        });

        LogoutIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Dou want Logout ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        sharedPreferences=getSharedPreferences("com.vjsm.sports.tourantshub",Context.MODE_PRIVATE);
                        editor=sharedPreferences.edit();
                        editor.clear().commit();
                        Intent s=new Intent(Vmain.this,LoginPage.class);
                        startActivity(s);
                        Toast.makeText(getApplicationContext(),"Logout Successfully",Toast.LENGTH_LONG).show();
                        ActivityCompat.finishAffinity(Vmain.this);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }

        });
        Logouttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Dou want Logout ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        sharedPreferences=getSharedPreferences("com.vjsm.sports.tourantshub",Context.MODE_PRIVATE);
                        editor=sharedPreferences.edit();
                        editor.clear().commit();
                        Intent s=new Intent(Vmain.this,LoginPage.class);
                        startActivity(s);
                        Toast.makeText(getApplicationContext(),"Logout Successfully",Toast.LENGTH_LONG).show();
                        ActivityCompat.finishAffinity(Vmain.this);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gets=getIntent().getStringExtra("send");
                Intent intent2=new Intent(Vmain.this, VUpload.class);
                intent2.putExtra("send",gets);
                startActivity(intent2);

            }
        });
        vie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gets=getIntent().getStringExtra("phones");
                Intent intent2=new Intent(Vmain.this, VShow.class);
                intent2.putExtra("send",gets);
                startActivity(intent2);
            }
        });
    }


}
