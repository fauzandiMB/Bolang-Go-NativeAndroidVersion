package model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by arifb on 18-Nov-17.
 */

public class Quiz implements Serializable {

    public String question;
    public String[] choices = new String[4];
    public Double rightIndex;

    public Quiz(String question, String[] choices, Double rightIndex) {
        this.question = question;
        this.choices = choices;
        this.rightIndex = rightIndex;
    }

    public Quiz(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoice(String choice, int index){
        try {
            this.choices[index] = choice;
        }catch (Exception e){
            Log.e(this.getClass().getName(), "error idk cari sendiri ya");
        }
    }

    public String getChoice(int index){
        try {
            String choice = this.choices[index];
            return choice;
        }catch (Exception e){
            Log.e(this.getClass().getName(), "error idk cari sendiri ya");
        }
        return null;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public Double getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(Double rightIndex) {
        this.rightIndex = rightIndex;
    }

}
