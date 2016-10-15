package com.bond.iampomodoro.View;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bond.iampomodoro.R;

public class FragmentSettings extends Fragment implements View.OnClickListener {

    SeekBar seekbarWorkSession;
    SeekBar seekbarBreak;
    SeekBar seekbarLongBreak;
    SeekBar seekbarSessionsBeforeLongBreak;

    TextView minutesTextView;
    TextView secondsTextView;
    Button startBtn;
    Button resetBtn;

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seekbarWorkSession = (SeekBar) view.findViewById(R.id.seekbarWorkSession);
        seekbarBreak = (SeekBar) view.findViewById(R.id.seekbarBreak);
        seekbarLongBreak = (SeekBar) view.findViewById(R.id.seekbarLongBreak);
        seekbarSessionsBeforeLongBreak = (SeekBar) view.findViewById(R.id.seekbarSessionsBeforeLongBreak);

        seekbarWorkSession.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarWorkSession.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarBreak.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarBreak.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarLongBreak.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarLongBreak.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarSessionsBeforeLongBreak.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarSessionsBeforeLongBreak.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);

//        minutesTextView = (TextView) view.findViewById(R.id.minutes);
//        secondsTextView = (TextView) view.findViewById(R.id.seconds);
//
//        startBtn = (Button) view.findViewById(R.id.startBtn);
//        resetBtn = (Button) view.findViewById(R.id.resetBtn);
//
//        startBtn.setOnClickListener(FragmentSettings.this);
//        resetBtn.setOnClickListener(FragmentSettings.this);

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

}