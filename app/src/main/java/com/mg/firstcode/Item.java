package com.mg.firstcode;

public class Item {

    int accExamCnt; // 누적검사수
    int accExamCompCnt; // 누적검사완료수
    int careCnt; //치료중 환자수
    int clearCnt; //격리해제수
    int deathCnt; // 사망자수
    int decideCnt; // 확진자수
    int examCnt; // 검사진행수
    int resutlNegCnt; // 결과 음성수

    public int getAccExamCnt() {
        return accExamCnt;
    }

    public int getAccExamCompCnt() {
        return accExamCompCnt;
    }

    public int getCareCnt() {
        return careCnt;
    }

    public int getClearCnt() {
        return clearCnt;
    }

    public int getDeathCnt() {
        return deathCnt;
    }

    public int getDecideCnt() {
        return decideCnt;
    }

    public int getExamCnt() {
        return examCnt;
    }

    public int getResutlNegCnt() {
        return resutlNegCnt;
    }
}
