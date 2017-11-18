package model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Player {
    public String id;
    public Double lat;
    public Double lng;
    public Double index;
    public Double poin;

    public Player(String id, Double lat, Double lng, Double index, Double poin) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.index = index;
        this.poin = poin;
    }

    public Player(String id, Double lat, Double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.index = 0.0;
        this.poin = 0.0;
    }

    public Player(String id) {
        this.id = id;
        this.lat = 0.0;
        this.lng = 0.0;
        this.index = 0.0;
        this.poin = 0.0;
    }

    public Player() {
        this.index = 0.0;
        this.poin = 0.0;
    }

    public Player(String id, LatLng pos, Double index, Double poin){
        this.id = id;
        this.lat = pos.latitude;
        this.lng = pos.longitude;
        this.index = index;
        this.poin = poin;
    }

    public Player(String id, LatLng pos){
        this.id = id;
        this.lat = pos.latitude;
        this.lng = pos.longitude;
        this.index = 0.0;
        this.poin = 0.0;
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

    public Double getPoin() {
        return poin;
    }

    public void setPoin(Double poin) {
        this.poin = poin;
    }
}
