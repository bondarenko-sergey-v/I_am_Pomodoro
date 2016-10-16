package com.bond.iampomodoro.View;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bond.iampomodoro.Model.SettingsHelper;
import com.bond.iampomodoro.R;

public class FragmentSettings extends Fragment {

    View view;
    int[] settings = new int[11];

    boolean firstVisible = false;

    SeekBar seekbarWorkSession;
    SeekBar seekbarBreak;
    SeekBar seekbarLongBreak;
    SeekBar seekbarSessionsBeforeLB;

    TextView textViewWorkSession;
    TextView textViewBreak;
    TextView textViewLongBreak;
    TextView textViewSessionsBeforeLB;

    CheckBox checkBoxDaySound;
    CheckBox checkBoxDayVibration;
    CheckBox checkBoxDayKeepScreen;

    CheckBox checkBoxNightSound;
    CheckBox checkBoxNightVibration;
    CheckBox checkBoxNightKeepScreen;
    CheckBox checkBoxNightPictures;

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        settings = SettingsHelper.getSettings(getActivity());

        view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();

        setupListeners();

        listenersInitialization();

        //TODO Presenter.startSettingsFragment
    }

    @Override
    public void onPause() {

        SettingsHelper.setSettings(getActivity(), settings);
        System.out.println("onPause()");

        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Save settings only when Fragment was really visible at least once
        if(isVisibleToUser) { firstVisible = true; }

        if(!isVisibleToUser && firstVisible) {
            SettingsHelper.setSettings(getActivity(), settings);
        }
    }

    private void initUI() {

        seekbarWorkSession = (SeekBar) view.findViewById(R.id.seekbarWorkSession);
        seekbarBreak = (SeekBar) view.findViewById(R.id.seekbarBreak);
        seekbarLongBreak = (SeekBar) view.findViewById(R.id.seekbarLongBreak);
        seekbarSessionsBeforeLB = (SeekBar) view.findViewById(R.id.seekbarSessionsBeforeLB);

        //  Stylize seekbars
        seekbarWorkSession.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarWorkSession.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarBreak.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarBreak.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarLongBreak.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarLongBreak.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarSessionsBeforeLB.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        seekbarSessionsBeforeLB.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);

        seekbarWorkSession.setMax(35);
        seekbarWorkSession.setProgress(settings[0] - 25);
        seekbarBreak.setMax(15);
        seekbarBreak.setProgress(settings[1] - 5);
        seekbarLongBreak.setMax(30);
        seekbarLongBreak.setProgress(settings[2] - 15);
        seekbarSessionsBeforeLB.setMax(2);
        seekbarSessionsBeforeLB.setProgress(settings[3] - 3);


        textViewWorkSession = (TextView) view.findViewById(R.id.textViewWorkSession);
        textViewBreak = (TextView) view.findViewById(R.id.textViewBreak);
        textViewLongBreak = (TextView) view.findViewById(R.id.textViewLongBreak);
        textViewSessionsBeforeLB = (TextView) view.findViewById(R.id.textViewSessionsBeforeLB);

        textViewWorkSession.setText(String.valueOf(settings[0]));
        textViewBreak.setText(String.valueOf(settings[1]));
        textViewLongBreak.setText(String.valueOf(settings[2]));
        textViewSessionsBeforeLB.setText(String.valueOf(settings[3]));


        checkBoxDaySound = (CheckBox) view.findViewById(R.id.checkBoxDaySound);
        checkBoxDayVibration = (CheckBox) view.findViewById(R.id.checkBoxDayVibration);
        checkBoxDayKeepScreen = (CheckBox) view.findViewById(R.id.checkBoxDayKeepScreen);
        checkBoxNightSound = (CheckBox) view.findViewById(R.id.checkBoxNightSound);
        checkBoxNightVibration = (CheckBox) view.findViewById(R.id.checkBoxNightVibration);
        checkBoxNightKeepScreen = (CheckBox) view.findViewById(R.id.checkBoxNightKeepScreen);
        checkBoxNightPictures = (CheckBox) view.findViewById(R.id.checkBoxNightPictures);

        if(settings[4] == 1) checkBoxDaySound.setChecked(true);
        if(settings[5] == 1) checkBoxDayVibration.setChecked(true);
        if(settings[6] == 1) checkBoxDayKeepScreen.setChecked(true);
        if(settings[7] == 1) checkBoxNightSound.setChecked(true);
        if(settings[8] == 1) checkBoxNightVibration.setChecked(true);
        if(settings[9] == 1) checkBoxNightKeepScreen.setChecked(true);
        if(settings[10] == 1) checkBoxNightPictures.setChecked(true);
    }

    private void setupListeners() {

        onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                switch (compoundButton.getId()) {

                    case R.id.checkBoxDaySound:
                        if (checked) {
                            settings[4] = 1;
                        }
                        else {
                            settings[4] = 0;
                        }
                        break;
                    case R.id.checkBoxDayVibration:
                        if (checked) {
                            settings[5] = 1;
                        }
                        else {
                            settings[5] = 0;
                        }
                        break;
                    case R.id.checkBoxDayKeepScreen:
                        if (checked) {
                            settings[6] = 1;
                        }
                        else {
                            settings[6] = 0;
                        }
                        break;

                    case R.id.checkBoxNightSound:
                        if (checked) {
                            settings[7] = 1;
                        }
                        else {
                            settings[7] = 0;
                        }
                        break;
                    case R.id.checkBoxNightVibration:
                        if (checked) {
                            settings[8] = 1;
                        }
                        else {
                            settings[8] = 0;
                        }
                        break;
                    case R.id.checkBoxNightKeepScreen:
                        if (checked) {
                            settings[9] = 1;
                        }
                        else {
                            settings[9] = 0;
                        }
                        break;
                    case R.id.checkBoxNightPictures:
                        if (checked) {
                            settings[10] = 1;
                        }
                        else {
                            settings[10] = 0;
                        }
                        break;
                }
            }
        };

        onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                switch (seekBar.getId()) {

                    case R.id.seekbarWorkSession:
                        textViewWorkSession.setText(String.valueOf(progress + 25));
                        settings[0] = progress + 25;
                        break;

                    case R.id.seekbarBreak:
                        textViewBreak.setText(String.valueOf(progress + 5));
                        settings[1] = progress + 5;
                        break;

                    case R.id.seekbarLongBreak:
                        textViewLongBreak.setText(String.valueOf(progress + 15));
                        settings[2] = progress + 15;
                        break;

                    case R.id.seekbarSessionsBeforeLB:
                        textViewSessionsBeforeLB.setText(String.valueOf(progress + 3));
                        settings[3] = progress + 3;
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };
    }

    private void listenersInitialization() {

        seekbarWorkSession.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbarBreak.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbarLongBreak.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbarSessionsBeforeLB.setOnSeekBarChangeListener(onSeekBarChangeListener);

        checkBoxDaySound.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxDayVibration.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxDayKeepScreen.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxNightSound.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxNightVibration.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxNightKeepScreen.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxNightPictures.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}