package com.vjsm.sports.tourantshub;

import android.support.annotation.NonNull;

public class Users implements Comparable {
    String Name;
    String Place;
    String District;
    String Startdate;
    String Madtdate;
    String Mantdate;
    String Phone;
    String Imageurl;
    String MapLocationLa;
    String Title;
    String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Users(String name, String place, String district, String startdate, String madtdate, String mantdate, String phone, String imageurl, String mapLocationLa, String title, String id, String mapLocationLo) {
        Name = name;
        Place = place;
        District = district;
        Startdate = startdate;
        Madtdate = madtdate;
        Mantdate = mantdate;
        Phone = phone;
        Imageurl = imageurl;
        MapLocationLa = mapLocationLa;
        Title = title;
        Id = id;
        MapLocationLo = mapLocationLo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Users(String title) {
        Title = title;
    }

    String MapLocationLo;

    public String getMapLocationLa() {
        return MapLocationLa;
    }

    public void setMapLocationLa(String mapLocationLa) {
        MapLocationLa = mapLocationLa;
    }

    public String getMapLocationLo() {
        return MapLocationLo;
    }

    public void setMapLocationLo(String mapLocationLo) {
        MapLocationLo = mapLocationLo;
    }

    public Users() {

    }

    public Users(String name, String place, String district, String startdate, String madtdate, String mantdate, String phone, String imageurl) {
        Name = name;
        Place = place;
        District = district;
        Startdate = startdate;
        Madtdate = madtdate;
        Mantdate = mantdate;
        Phone = phone;
        Imageurl = imageurl;
    }
    public Users(String mapLocationLa, String mapLocationLo) {
        MapLocationLa = mapLocationLa;
        MapLocationLo = mapLocationLo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStartdate() {
        return Startdate;
    }

    public void setStartdate(String startdate) {
        Startdate = startdate;
    }

    public String getMadtdate() {
        return Madtdate;
    }

    public void setMadtdate(String madtdate) {
        Madtdate = madtdate;
    }

    public String getMantdate() {
        return Mantdate;
    }

    public void setMantdate(String mantdate) {
        Mantdate = mantdate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }

    @Override
    public int compareTo(@NonNull Object o) {
o.equals(getStartdate());
        return 0;
    }
}
