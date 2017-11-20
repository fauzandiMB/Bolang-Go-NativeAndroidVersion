package model;

import java.io.Serializable;

/**
 * Created by arifb on 30-Oct-17.
 */

public class Player implements Serializable {
    public String id;
    public String displayName;
    public Position position;
    public Double indexChallenge;
    public Double collectedCoin;
    public Double answeredQuiz;

    public Player(){}

    public Player(String id, String displayName, Position position, Double indexChallenge, Double collectedCoin, Double answeredQuiz) {
        this.id = id;
        this.displayName = displayName;
        this.position = position;
        this.indexChallenge = indexChallenge;
        this.collectedCoin = collectedCoin;
        this.answeredQuiz = answeredQuiz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public Double getAnsweredQuiz() {
        return answeredQuiz;
    }

    public void setAnsweredQuiz(Double answeredQuiz) {
        this.answeredQuiz = answeredQuiz;
    }
}
