package com.example.myapplication.models; // Updated to match your project

public class StudySpot {
    private int id;
    private String name;
    private String description;

    // Constructor
    public StudySpot(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}