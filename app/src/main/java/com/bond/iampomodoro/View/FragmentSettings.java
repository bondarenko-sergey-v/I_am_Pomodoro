package com.bond.iampomodoro.View;

import android.databinding.DataBindingUtil;

import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bond.iampomodoro.Model.SettingsHelper;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentSettingsBinding;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxSeekBar;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;

    FragmentSettingsBinding binding;
    User user = new User();
    ScreenHandler screenHandler = new ScreenHandler();

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        settings = SettingsHelper.getSettings(getActivity());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);


        user.setName("olo");
        binding.setUser(user);

        //final View layout = inflater.inflate(R.layout.fragment_settings,container,false);
        //FragmentSettingsBinding.bind(layout).setScreenHandler(new ScreenHandler(this));

        //binding.setScreenHandler(new FragmentSettings().screenHandler);
        // init screen handler


        binding.setScreenHandler(screenHandler);



        //view = inflater.inflate(R.layout.fragment_settings, container, false);
        view = binding.getRoot();
        return view;
    }

    public static class User {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        //public ObservableBoolean loading = new ObservableBoolean();
        //public ObservableField<String> buttonText = new ObservableField<>("load big data");

    }
    public static class ScreenHandler {

//        public final String firstName;
//        public ScreenHandler (String firstName) {
//            this.firstName = firstName;
//        }

        //public ObservableBoolean loading = new ObservableBoolean();
        public ObservableField<String> buttonText = new ObservableField<>("load big data");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        //binding.checkBoxDaySound.

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
    public void onDestroy() {
        super.onDestroy();

        //if (checkBoxDayVibration1 != null) {
        //    checkBoxDayVibration1.unsubscribe();
        //}
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

    class Indexed <T> {
        final String index;
        final T item;
        Indexed(String index, T item) {
            this.index = index;
            this.item = item;
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



        Observable<Indexed<Boolean>> cb1 = RxCompoundButton
                .checkedChanges(binding.checkBoxDaySound)
                .skip(1)
                .map(v -> new Indexed<>("DaySound", v));

        Observable<Indexed<Boolean>> cb2 = RxCompoundButton
                .checkedChanges(binding.checkBoxDayVibration)
                .skip(1)
                .map(v -> new Indexed<>("DayVibration", v));

        Observable<Indexed<Boolean>> cb3 = RxCompoundButton
                .checkedChanges(binding.checkBoxDayKeepScreen)
                .skip(1)
                .map(v -> new Indexed<>("DayKeepScreen", v));

        Observable<Indexed<Boolean>> cb4 = RxCompoundButton
                .checkedChanges(binding.checkBoxNightSound)
                .skip(1)
                .map(v -> new Indexed<>("NightSound", v));

        Observable<Indexed<Boolean>> cb5 = RxCompoundButton
                .checkedChanges(binding.checkBoxNightVibration)
                .skip(1)
                .map(v -> new Indexed<>("NightVibration", v));

        Observable<Indexed<Boolean>> cb6 = RxCompoundButton
                .checkedChanges(binding.checkBoxNightKeepScreen)
                .skip(1)
                .map(v -> new Indexed<>("NightKeepScreen", v));

        Observable<Indexed<Boolean>> cb7 = RxCompoundButton
                .checkedChanges(binding.checkBoxNightPictures)
                .skip(1)
                .map(v -> new Indexed<>("NightPictures", v));

        Observable<Indexed<Boolean>> values = Observable.merge(cb1,cb2,cb3,cb4,cb5,cb6,cb7);

        values.subscribe(w -> System.out.println("[Rx] " + w.index + " : " + w.item));


        Observable<Indexed<Integer>> sb1 = RxSeekBar.changes(binding.seekbarWorkSession)
                .skip(1)
                //.debounce(150, TimeUnit.MILLISECONDS)
                .map(v -> v + 25)
                .doOnNext(v -> {
                    user.setName(String.valueOf(v));
                    binding.setUser(user);
                })
                .doOnNext(v -> screenHandler.buttonText.set(String.valueOf(v)))
                .map(v -> new Indexed<>("WorkSession", v));

        sb1.subscribe(w -> System.out.println("[Rx] " + w.index + " : " + w.item));

//        Observable<Boolean> values = RxCompoundButton
//                .checkedChanges(binding.checkBoxDayVibration)
//                .skip(1);

//       RxCompoundButton.checkedChanges(binding.checkBoxDayVibration)
//               .skip(1) // Skip the initial value.
//               .map(c -> c + " - DayVibration")
//               .subscribe(c -> Log.d("[Rx]", String.valueOf(c)));

//        if(settings[4] == 1) checkBoxDaySound.setChecked(true);
//        if(settings[5] == 1) checkBoxDayVibration.setChecked(true);
//        if(settings[6] == 1) checkBoxDayKeepScreen.setChecked(true);
//        if(settings[7] == 1) checkBoxNightSound.setChecked(true);
//        if(settings[8] == 1) checkBoxNightVibration.setChecked(true);
//        if(settings[9] == 1) checkBoxNightKeepScreen.setChecked(true);
//        if(settings[10] == 1) checkBoxNightPictures.setChecked(true);
    }

    private void setupListeners() {

        onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                switch (seekBar.getId()) {

//                    case R.id.seekbarWorkSession:
//                        textViewWorkSession.setText(String.valueOf(progress + 25));
//                        settings[0] = progress + 25;
//                        break;

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

//        seekbarWorkSession.setOnSeekBarChangeListener(onSeekBarChangeListener);


        seekbarBreak.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbarLongBreak.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbarSessionsBeforeLB.setOnSeekBarChangeListener(onSeekBarChangeListener);

    }
}