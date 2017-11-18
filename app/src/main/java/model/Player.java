package model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Player implements Serializable {
    public String id;
    public String namePlayer;
    public Position position;
    public Double indexChallenge;
    public Double collectedCoin;
    public Double answeredQuiz;

    public Player(String id, String namePlayer, Double lat, Double lng, Double indexChallenge, Double collectedCoin, Double answeredQuiz) {
        this.id = id;
        this.namePlayer = namePlayer;
        this.indexChallenge = indexChallenge;
        this.collectedCoin = collectedCoin;
        this.answeredQuiz = answeredQuiz;
        this.position = new Position(lat, lng);
    }

    public Player(String id, Double lat, Double lng) {
        this.id = id;
        this.namePlayer = "";
        this.indexChallenge = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
        this.position = new Position(lat, lng);
    }

    public Player(String id) {
        this.id = id;
        this.namePlayer = "";
        this.indexChallenge = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
        this.position = new Position();
    }
    public Player(String id, String namePlayer) {
        this.id = id;
        this.namePlayer = namePlayer;
        this.indexChallenge = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
        this.position = new Position();
    }

    public Player() {}

    public Player(String id, String namePlayer, Position pos, Double indexChallenge, Double collectedCoin, Double answeredQuiz){
        this.id = id;
        this.namePlayer = namePlayer;
        this.indexChallenge = indexChallenge;
        this.collectedCoin = collectedCoin;
        this.answeredQuiz = answeredQuiz;
        this.position = pos;
    }

    public Player(String id, String namePlayer, Position pos){
        this.id = id;
        this.namePlayer = namePlayer;
        this.indexChallenge = 0.0;
        this.collectedCoin = 0.0;
        this.answeredQuiz = 0.0;
        this.position = pos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLat() {
        return position.latitude;
    }

    public void setLat(Double lat) {
        Double lng = this.position.longitude;
        this.position = new Position(lat,lng);
    }

    public Double getLng() {
        return this.position.longitude;
    }

    public void setLng(Double lng) {
        Double lat = this.position.latitude;
        this.position = new Position(lat,lng);
    }

    public void setPos(Position pos){
        this.position = pos;
    }

    public Position getPos(){
        return this.position;
    }

    public Double getIndexChallenge() {
        return indexChallenge;
    }

    public void setIndexChallenge(Double indexChallenge) {
        this.indexChallenge = indexChallenge;
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
