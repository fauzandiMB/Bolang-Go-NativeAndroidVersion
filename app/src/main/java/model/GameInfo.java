package model;

import java.io.Serializable;

/**
 * Created by Alvin on 11/18/2017.
 */

public class GameInfo implements Serializable {
    public String gameName;
    public Player player;
    public Challenge challenge;

    public GameInfo(String gameName, Player player, Challenge challenge) {
        this.gameName = gameName;
        this.player = player;
        this.challenge = challenge;
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

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }


}

