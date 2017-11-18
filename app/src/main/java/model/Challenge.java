package model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Challenge implements Serializable {
    public Double lat;
    public Double lng;
    public String type;
    public String quiz;
    public Double maxPoint;
    public boolean isCleared;

    public Challenge(){}

    public Challenge(Double lat, Double lng, String type, String quiz, Double maxPoint, boolean isCleared){
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.quiz = quiz;
        this.maxPoint = maxPoint;
        this.isCleared = isCleared;
    }

    public Challenge(LatLng pos, String type, String quiz, Double maxPoint){
        this.lat = pos.latitude;
        this.lng = pos.longitude;
        this.type = type;
        this.quiz = quiz;
        this.maxPoint = maxPoint;
        this.isCleared = false;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public Double getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(Double maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void setPosition(LatLng pos){
        this.lat = pos.latitude;
        this.lng = pos.longitude;
    }

    public LatLng getPos(){
        return new LatLng(lat, lng);
    }


}
