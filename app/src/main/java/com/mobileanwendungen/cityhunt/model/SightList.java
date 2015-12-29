package com.mobileanwendungen.cityhunt.model;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by thorben.wilde on 28.12.2015.
 */
public class SightList extends ArrayList<Sight> implements Serializable{

    private static final long serialVersionUID = 42L;

    public static SightList generateDemoData(){
        SightList sightList = new SightList();
        sightList.add(new Sight(UUID.randomUUID().toString(), "Schloss Wolfenbüttel", 52.162906, 10.530108, Sight.Type.MONUMENT, "Wie viele unechte Fenster?"));
        sightList.add(new Sight(UUID.randomUUID().toString(), "Herzog August Bibliothek", 52.164197, 10.530367, Sight.Type.MUSEUM, "Wie viele Bücher in Regal X?"));
        sightList.add(new Sight(UUID.randomUUID().toString(), "St. Ansgar", 52.172997, 10.559461, Sight.Type.CHURCH, "Wie viele Bänke?"));
        sightList.add(new Sight(UUID.randomUUID().toString(), "St. Trinitatis", 52.162123, 10.541219, Sight.Type.CHURCH, "Wie viele Türen?"));
        sightList.add(new Sight(UUID.randomUUID().toString(), "Geek & Cosplay Museum", 52.168451, 10.483701, Sight.Type.MUSEUM, "Wie hoch ist der Coolness-Faktor?"));
        return sightList;
    }



    public static SightList loadFromFile(Context context, String filename){
        FileInputStream inputFile = null;
        ObjectInputStream objectInput = null;
        try {
            inputFile = context.openFileInput(filename);
            objectInput = new ObjectInputStream(inputFile);
            Object readedObject = objectInput.readObject();
            if(readedObject instanceof SightList){
                return (SightList)readedObject;
            }else{
                return generateDemoData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(objectInput != null)
                    objectInput.close();
            } catch(Exception e) {}

            try {
                if(inputFile != null)
                    inputFile.close();
            } catch(Exception e) {}
        }

        return generateDemoData();
    }

    public void saveToFile(Context context, String filename) {
        FileOutputStream outputFile = null;
        ObjectOutputStream objectOutput = null;

        try {
            outputFile = context.openFileOutput(filename, Context.MODE_PRIVATE);
            objectOutput = new ObjectOutputStream(outputFile);
            objectOutput.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save failed!");
        } finally {
            try {
                if (objectOutput != null)
                    objectOutput.close();
            } catch (Exception e) {
            }

            try {
                if (outputFile != null)
                    outputFile.close();
            } catch (Exception e) {
            }
        }

    }

}
