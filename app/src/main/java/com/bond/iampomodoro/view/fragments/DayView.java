package com.bond.iampomodoro.view.fragments;

public interface DayView extends View {

    void showTime(int timeInSeconds);

    void showButtons(String buttonsState);
}
