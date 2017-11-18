package model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Challenge implements Serializable {

    public LatLng position;
    public String type;
    public boolean isCleared;
    public String typeQuiz;
    public Quiz quiz;
    public Treasure treasure;

    public Challenge(){}
    public Challenge(LatLng position, String type, String typeQuiz, boolean isCleared) {
        this.position = position;
        this.type = type;
        this.isCleared = isCleared;
        this.typeQuiz = typeQuiz;
    }

    public Challenge(Double lat, Double lng, String type, String typeQuiz, boolean isCleared){
        this.position = new LatLng(lat,lng);
        this.type = type;
        this.isCleared = isCleared;
        this.typeQuiz = typeQuiz;
    }

    public Challenge(LatLng pos, String type){
        this.position = pos;
        this.type = type;
        this.isCleared = false;
    }

    public Double getLat() {
        return this.position.latitude;
    }

    public void setLat(Double lat) {
        Double lng = this.position.longitude;
        this.position = new LatLng(lat, lng);
    }

    public Double getLng() {
        return this.position.longitude;
    }

    public void setLng(Double lng) {
        Double lat = this.position.latitude;
        this.position = new LatLng(lat, lng);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(LatLng pos){
        this.position = pos;
    }

    public LatLng getPos(){
        return this.position;
    }

    public Object getChallenge(){
        if(type == Constant.QUIZ_CHALLENGE){
            return quiz;
        }else{
            return treasure;
        }
    }

}
