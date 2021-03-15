package visionapp.models;

import com.google.cloud.vision.v1.BoundingPoly;

//Criado pelo professor
public class DetectedText {
    public String text;
    public String language;
    public BoundingPoly poly;
}
