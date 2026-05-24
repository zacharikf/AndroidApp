package com.example.myapplication.models;

public class StudySpot {
    private int id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;

    // Updated Constructor
    public StudySpot(int id, String name, String description, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}