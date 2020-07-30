package com.mg.firstcode;

public class Location_item {

    int defCnt; //확진자수
    String gubun; // 시도명(한글)
    int deathCnt; // 사망자수
    String qurRate; // 10만명당 발생비율
    int isolClearCnt; // 격리해제수
    int isolingCnt; // 격리중환자수
    int localOccCnt; //지역발생수
    int overFlowCnt; // 해외유입수

    public int getDefCnt() {
        return defCnt;
    }

    public String getGubun() {
        return gubun;
    }
//
    public int getDeathCnt() {
        return deathCnt;
    }

    public String getQurRate() {
        return qurRate;
    }

    public int getIsolClearCnt() {
        return isolClearCnt;
    }

    public int getIsolingCnt() {
        return isolingCnt;
    }

    public int getLocalOccCnt() {
        return localOccCnt;
    }

    public int getOverFlowCnt() {
        return overFlowCnt;
    }

}
