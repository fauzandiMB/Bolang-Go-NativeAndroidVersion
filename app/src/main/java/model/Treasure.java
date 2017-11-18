package model;

import java.io.Serializable;

/**
 * Created by arifb on 18-Nov-17.
 */

public class Treasure implements Serializable {

    public Double maxCoin;
    public Double timeCount = 60.0;

    public Treasure(Double maxCoin) {
        this.maxCoin = maxCoin;
    }

    public Treasure(Double maxCoin, Double timeCount) {
        this.maxCoin = maxCoin;
        this.timeCount = timeCount;
    }

    public Double getMaxCoin() {
        return maxCoin;
    }

    public void setMaxCoin(Double maxCoin) {
        this.maxCoin = maxCoin;
    }

    public Double getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Double timeCount) {
        this.timeCount = timeCount;
    }

}
