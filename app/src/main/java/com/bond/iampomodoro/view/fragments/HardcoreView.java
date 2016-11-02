package com.bond.iampomodoro.view.fragments;

public interface HardcoreView extends View {

    void showTime(int timeInSeconds);

    void showButons(String buttonsState);

    void showState(String currentState);

    void showImage(int imageNumber); //TODO Change to appropriate data type

    //String getUserName();
}
