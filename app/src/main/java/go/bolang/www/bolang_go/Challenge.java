package go.bolang.www.bolang_go;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Challenge {
    public Double lat;
    public Double lng;
    public String type;
    public String quiz;
    public Double coin;

    public Challenge(){}

    public Challenge(Double lat, Double lng, String type, String quiz, Double coin){
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.quiz = quiz;
        this.coin = coin;
    }

    public Challenge(LatLng pos, String type, String quiz, Double coin){
        this.lat = pos.latitude;
        this.lng = pos.longitude;
        this.type = type;
        this.quiz = quiz;
        this.coin = coin;
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

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public void setPosition(LatLng pos){
        this.lat = pos.latitude;
        this.lng = pos.longitude;
    }

    public LatLng getPos(){
        return new LatLng(lat, lng);
    }
}
