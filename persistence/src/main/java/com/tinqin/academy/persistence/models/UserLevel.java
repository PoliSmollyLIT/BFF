package com.tinqin.academy.persistence.models;

public enum UserLevel {
    BRONZE(500, 999, 1, 3),
    SILVER(1000, 1499, 2, 5),
    GOLD(1500, 1999, 3, 10),
    PLATINUM(2000, 10000, 4, 15);

    private UserLevel(int min, int max, int levelNumber, int percentOff){

    }

    public int getMin(){
        return this.getMin();
    }

    public int getMax(){
        return this.getMax();
    }

    public int getLevelNumber(){
        return this.getLevelNumber();
    }

    public int getPercentOff(){
        return this.getPercentOff();
    }

    public UserLevel setLevelByLevelNumber(int levelNumber){
        UserLevel level;
        switch (levelNumber){
            case 1: level = BRONZE; break;
            case 2: level = SILVER; break;
            case 3: level = GOLD; break;
            default: level = PLATINUM; break;
        }
        return level;
    }
}
