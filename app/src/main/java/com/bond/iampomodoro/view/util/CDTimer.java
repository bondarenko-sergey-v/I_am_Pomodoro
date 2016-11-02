package com.bond.iampomodoro.view.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CDTimer {

    public CDTimer() {}

    public Observable<?> countDownTimer(int intervalInSeconds){
        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(v -> v + 1)
                        .take(intervalInSeconds)
                        .map(v -> (int) (intervalInSeconds - v));
                      //  .doAfterTerminate(() -> {timerStart(); notifyUser(notifyUser);})
                      //  .subscribe(v -> {
                      //      ob.intervalInSeconds = (int) (intervalInSeconds - v);
                      //      showTime(ob.intervalInSeconds);
                      //  });
    }
}