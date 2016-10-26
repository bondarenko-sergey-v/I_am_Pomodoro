package com.bond.iampomodoro.presenter;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.databinding.FragmentDayBinding;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.SettingsObject;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

    @Inject
    SettingsHelper settingsHelper;

    //@Inject
    CompositeSubscription compositeSubscription = new CompositeSubscription();

    private SettingsObject settings;
    private FragmentDayBinding binding;

    private Boolean isWorkTime = true;
    private int breaksCount = 0;

    private int workSessionMin;
    private int breakMin;
    private int longBreakMin;
    private int sessionsBeforeLB;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    abstract void showTime(int minutes, int seconds);

    public void notifyDayFragmentStarts(FragmentDayBinding binding) {
        this.binding = binding;

        settings = settingsHelper.getSetings();
        workSessionMin = settings.intr[0];
        breakMin = settings.intr[1];
        longBreakMin = settings.intr[2];
        sessionsBeforeLB = settings.intr[3];

        initUI();
    }

    public void timerStart() {

        if (isWorkTime) {
            countDownTimer(0, 10);
            //countDownTimer(workSessionMin, 0);
            isWorkTime = false;
        } else if (breaksCount < sessionsBeforeLB) {
            countDownTimer(0, 3);
            //countDownTimer(breakMin, 0);
            breaksCount++;
            isWorkTime = true;
        } else {
            countDownTimer(0, 7);
            //countDownTimer(longBreakMin, 0);
            breaksCount = 0;
            isWorkTime = true;
        }
    }

    private  void timerResume() {

        countDownTimer(Integer.parseInt(binding.minutes.getText().toString()),
                Integer.parseInt(binding.seconds.getText().toString()));
    }

    private void countDownTimer(int minutes, int seconds) {
        int intervalInSeconds = minutes * 60 + seconds;

        clearCompositeSubscription();

        Subscription s =
                Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(v -> v +1)
                        .take(intervalInSeconds)
                        .doAfterTerminate(() -> timerStart())
                        .subscribe(v -> {
                            showTime((int) (intervalInSeconds - v) / 60,
                                     (int) (intervalInSeconds - v) % 60);
                            //                       binding.minutes.setText(String.valueOf(
                            //                               (int) (intervalInSeconds - v) / 60));
                            //                       binding.seconds.setText(String.format("%02d", //TODO Check warning
                            //                               (intervalInSeconds - v) % 60));
                        });

        compositeSubscription.add(s);
    }

    public void clearCompositeSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
            compositeSubscription = new CompositeSubscription();
        }
    }
}
