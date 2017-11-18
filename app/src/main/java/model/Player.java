package model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Player implements Serializable {
    public String id;
    public String namePlayer;
    public Double lat;
    public Double lng;
    public Double index;
    public Double collectedCoin;
    public Double answeredQuiz;
    public LatLng position;

    public Player(String id, Double lat, Double lng, Double index, Double collectedCoin, Double answeredQuiz) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.index = index;
        this.collectedCoin = collectedCoin;
        this.answeredQuiz = answeredQuiz;
        this.position = new LatLng(0, 3);
    }

    public Player(String id, Double lat, Double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.index = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
    }

    public Player(String id) {
        this.id = id;
        this.lat = 0.0;
        this.lng = 0.0;
        this.index = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
    }

    public Player() {
        this.index = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
    }

    public Player(String id, LatLng pos, Double index, Double collectedCoin, Double answeredQuiz){
        this.id = id;
        this.lat = pos.latitude;
        this.lng = pos.longitude;
        this.index = index;
        this.collectedCoin = collectedCoin;
        this.answeredQuiz = answeredQuiz;
    }

    public Player(String id, LatLng pos){
        this.id = id;
        this.lat = pos.latitude;
        this.lng = pos.longitude;
        this.index = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPos(LatLng pos){
        this.lat = pos.latitude;
        this.lng = pos.longitude;
    }

    public LatLng getPos(){
        return new LatLng(this.lat, this.lng);
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    public Double getCollectedCoin() {
        return collectedCoin;
    }

    public void setCollectedCoin(Double collectedCoin) {
        this.collectedCoin = collectedCoin;
    }

    public Double getAnsweredQuiz() {return answeredQuiz;}

    public void setAnsweredQuiz(Double answeredQuiz) { this.answeredQuiz = answeredQuiz;}

    public String getNamePlayer() {return namePlayer;}

    public void setNamePlayer(String namePlayer) { this.namePlayer = namePlayer;}

}
