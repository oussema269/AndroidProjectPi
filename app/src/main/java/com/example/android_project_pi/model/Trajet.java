package com.example.android_project_pi.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trajet_table")
public class Trajet {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="point_De_Depart")
    private String pointDeDepart;
    private String destination;
    private String date;
    private String heureDepart;
    private int prix;


    // Constructeur par défaut
    public Trajet() {}

    public Trajet(int id, String pointDeDepart, String destination, String date, String heureDepart, int prix) {
        this.id = id;
        this.pointDeDepart = pointDeDepart;
        this.destination = destination;
        this.date = date;
        this.heureDepart = heureDepart;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointDeDepart() {
        return pointDeDepart;
    }

    public void setPointDeDepart(String pointDeDepart) {
        this.pointDeDepart = pointDeDepart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
