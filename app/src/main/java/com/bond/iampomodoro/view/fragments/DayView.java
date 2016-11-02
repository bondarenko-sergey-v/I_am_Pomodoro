package com.bond.iampomodoro.view.fragments;

public interface DayView extends View {

    void showTime(int timeInSeconds);

    void showButons(String buttonsState);

    void showState(String currentState);

    //String getUserName();
}
