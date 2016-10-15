package com.bond.iampomodoro.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bond.iampomodoro.R;

public class FragmentHardcore extends Fragment implements View.OnClickListener {

    TextView minutesTextView;
    TextView secondsTextView;
    Button startBtn;
    Button resetBtn;

    public static FragmentHardcore newInstance() {
        return new FragmentHardcore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hardcore, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        minutesTextView = (TextView) view.findViewById(R.id.minutes);
        secondsTextView = (TextView) view.findViewById(R.id.seconds);

        startBtn = (Button) view.findViewById(R.id.startBtn);
        resetBtn = (Button) view.findViewById(R.id.resetBtn);

        startBtn.setOnClickListener(FragmentHardcore.this);
        resetBtn.setOnClickListener(FragmentHardcore.this);

        //TODO Presenter.startNewFragment
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn:
                //TODO Presenter.pause
                break;

            case R.id.resetBtn:
                //TODO Presenter.reset
                break;
        }
    }

    public void showTime(int minutes, int seconds) {

        minutesTextView.setText(String.valueOf(minutes));
        secondsTextView.setText(String.valueOf(seconds));

    }

    public void setButtonsText(String startBtnText, String resetBtnText) {

        startBtn.setText(startBtnText);
        resetBtn.setText(resetBtnText);

    }
}