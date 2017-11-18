package model;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvin on 11/18/2017.
 */

public class GameInfo implements Serializable {
    public String gameName;
    public Player player;
    public List<Challenge> challenges =  new ArrayList<Challenge>();

    public GameInfo(){}
    public GameInfo(String gameName) {
        this.gameName = gameName;
    }
    public GameInfo(String gameName, Player player) {
        this.gameName = gameName;
        this.player = player;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void addChallenges(Challenge challenge){
        this.challenges.add(challenge);
    }
}

