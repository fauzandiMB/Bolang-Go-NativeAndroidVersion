package model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Challenge implements Serializable {

    public Position position;
    public String type;
    public boolean isCleared;
    public String typeQuiz;
    public Quiz quiz;
    public Treasure treasure;

    public Challenge(){}

    public Challenge(Position position, String type, boolean isCleared, String typeQuiz, Quiz quiz, Treasure treasure) {
        this.position = position;
        this.type = type;
        this.isCleared = isCleared;
        this.typeQuiz = typeQuiz;
        this.quiz = quiz;
        this.treasure = treasure;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCleared() {
        return isCleared;
    }

    public void setCleared(boolean cleared) {
        isCleared = cleared;
    }

    public String getTypeQuiz() {
        return typeQuiz;
    }

    public void setTypeQuiz(String typeQuiz) {
        this.typeQuiz = typeQuiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public Double getDistance(Location playerLocation){
        if(playerLocation == null) return Double.MAX_VALUE;
        Location location =  new Location("Challenge-Location");
        Float distance = playerLocation.distanceTo(location);
        return Double.parseDouble(distance.toString());
    }
}
