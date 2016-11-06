package com.bond.iampomodoro.view.fragments;

public interface NightView extends View {

    void showTime(int timeInSeconds);

    void showButtons(String buttonsState);

    void showImage(boolean showImage, int imageNumber); //TODO Change to appropriate data type
}
