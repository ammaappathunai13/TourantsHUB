package com.vjsm.sports.tourantshub;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import static com.vjsm.sports.tourantshub.CShow.DISTRICT;
import static com.vjsm.sports.tourantshub.CShow.IMAGEU;
import static com.vjsm.sports.tourantshub.CShow.LocationLa;
import static com.vjsm.sports.tourantshub.CShow.LocationLo;
import static com.vjsm.sports.tourantshub.CShow.MADTDATE;
import static com.vjsm.sports.tourantshub.CShow.MANTDATE;
import static com.vjsm.sports.tourantshub.CShow.NAMESS;
import static com.vjsm.sports.tourantshub.CShow.PHONE;
import static com.vjsm.sports.tourantshub.CShow.PLACE;
import static com.vjsm.sports.tourantshub.CShow.STARTDAATE;

public class Details extends AppCompatActivity {
    TextView ns, ps, ds, ss, mds, mns, phs;
    PhotoView photoView;
    Button location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        final String imageurl = intent.getStringExtra(IMAGEU);
        String names = intent.getStringExtra(NAMESS);
        String place = intent.getStringExtra(PLACE);
        String district = intent.getStringExtra(DISTRICT);
        String startdate = intent.getStringExtra(STARTDAATE);
        String mantdate = intent.getStringExtra(MANTDATE);
        String madtdate = intent.getStringExtra(MADTDATE);
        String Locationla=intent.getStringExtra(LocationLa);
        String Locationlo=intent.getStringExtra(LocationLo);
        final String phone = intent.getStringExtra(PHONE);
        //double  la=Double.parseDouble(Locationla.toString().trim());
        // double  ls=Double.parseDouble(Locationlo.toString().trim());

        // Toast.makeText(getApplicationContext(), (int) la,Toast.LENGTH_LONG).show();
        ns = (TextView) findViewById(R.id.nametext);
        ps = (TextView) findViewById(R.id.places);
        ds = (TextView) findViewById(R.id.district);
        ss = (TextView) findViewById(R.id.stdate);
        mns = (TextView) findViewById(R.id.manTdate);
        mds = (TextView) findViewById(R.id.madTdate);
        phs = (TextView) findViewById(R.id.phonenumber);
        location=(Button)findViewById(R.id.locations);


        photoView=(PhotoView)findViewById(R.id.photo_view);
        ns.setText(names);
        ps.setText(place);
        ds.setText(district);
        ss.setText(startdate);
        if(mantdate.isEmpty()){
            mns.setText("ஏதும் இல்லை");
        }
        else {
            mns.setText(mantdate);
        }
        if( madtdate.isEmpty())
        {
            mds.setText("ஏதும் இல்லை");
        }
        else
        {
            mds.setText(madtdate);
        }
        phs.setText(phone + "      (Click to call...)");
        if (TextUtils.isEmpty(imageurl)) {
            photoView.setVisibility(View.VISIBLE);

            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/socialwork-a0eb5.appspot.com/o/noimage%2Fnoimage.jpg?alt=media&token=eb353e78-3cb1-4d29-bf9c-360c472b81c6").into(photoView);
        } else {

            photoView.setVisibility(View.VISIBLE);
            Picasso.get().load(imageurl).into(photoView);

        }
        phs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Locationla==null ||Locationlo==null){
                    Toast.makeText(getApplicationContext(),"Location-ஜ பதிவு செய்யவில்லை.",Toast.LENGTH_LONG).show();
                }

                else {

                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Double.parseDouble(Locationla),Double.parseDouble(Locationlo));
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    Details.this.startActivity(intent);

                }
            }
        });
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

}
