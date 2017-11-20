package model;

import java.io.Serializable;

/**
 * Created by arifb on 18-Nov-17.
 */

public class Position implements Serializable {
    public Double latitude;
    public Double longitude;

    public Position(){}

    public Position(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
