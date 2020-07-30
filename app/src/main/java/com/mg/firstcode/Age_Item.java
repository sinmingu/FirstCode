package com.mg.firstcode;

public class Age_Item {

    String gubun; //연령
    int death; //사망수
    double deathRate ; //사망률
    int confCase; // 확진자;
    double confCaseRate; // 확진률;
    double criticalRate; //치명률

    public String getGubun() {
        return gubun;
    }

    public int getDeath() {
        return death;
    }

    public double getDeathRate() {
        return deathRate;
    }

    public int getConfCase() {
        return confCase;
    }
//
    public double getConfCaseRate() {
        return confCaseRate;
    }

    public double getCriticalRate() {
        return criticalRate;
    }
}
