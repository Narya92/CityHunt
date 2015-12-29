package com.mobileanwendungen.cityhunt.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thorben.wilde on 28.12.2015.
 */
public class Sight implements Serializable{

    private static final long serialVersionUID = 42L;

    public enum Type {
        MONUMENT,
        CHURCH,
        MUSEUM
    }

    private String id = "";
    private double latitude = 52.164041;
    private double longitude = 10.540848;
    private String name = "Unnamed";

    private Type type = Type.MONUMENT;

    private String creationAge = "Unknown";
    private String address = "Unknown";
    private String taskAwnser = "";

    private String taskText = "";
    private Set<String> photos = new HashSet<>(5);

    public Sight(String id, String name, double latitude, double longitude, Type type, String taskText){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.taskText = taskText;
    }

    public String getCreationAge() {
        return creationAge;
    }

    public String getAddress() {
        return address;
    }

    public String getTaskAwnser() {
        return taskAwnser;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getTaskText() {
        return taskText;
    }

    public String[] getPhotos(){
        return photos.toArray(new String[photos.size()]);
    }

    public void setCreationAge(String creationAge) {
        this.creationAge = creationAge;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTaskAwnser(String text) {
        this.taskAwnser = text;
    }

    public void addPhoto(String photoUri){

        photos.add(photoUri);
    }

    public void removePhoto(String photoUri){
        photos.remove(photoUri);
    }
}
