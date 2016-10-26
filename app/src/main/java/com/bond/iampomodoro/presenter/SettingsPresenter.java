package com.bond.iampomodoro.presenter;

import android.widget.CheckBox;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.databinding.FragmentSettingsBinding;
import com.bond.iampomodoro.model.SettingsHelper;
import com.bond.iampomodoro.model.SettingsObject;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxSeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static rx.Observable.combineLatest;

public class SettingsPresenter {

    @Inject
    SettingsHelper settingsHelper;

    @Inject
    CompositeSubscription subscriptionList;

    private SettingsObject settings;

    public SettingsPresenter() {
        App.getComponent().inject(this);
    }

    private FragmentSettingsBinding binding;

    public void saveSettings() {

        if(settings != null) {
            settingsHelper.setSetings(settings);
        }
    }

    public void notifySettingsFragmentStarts(FragmentSettingsBinding binding) {
        this.binding = binding;

        settings = settingsHelper.getSetings();

        initCheckBoxes();
        initSeekbars();
    }

    private void initCheckBoxes() {
        List<CheckBox> cbList = Arrays.asList( binding.cbxDaySound, binding.cbxDayVibration,
                binding.cbxDayKeepScreen, binding.cbxNightSound, binding.cbxNightVibration,
                binding.cbxNightKeepScreen, binding.cbxNightPictures);

        Subscription s = Observable.from(cbList)
                .doOnNext(v -> v.setChecked(settings.bool[cbList.indexOf(v)])) //TODO Refactor index calculation
                .map(RxCompoundButton::checkedChanges)                         // with .count or something else
                .toList()
                .flatMap(v -> combineLatest(v, args -> {
                    List<Boolean> combine = new ArrayList<>();
                    for(Object c : args) {
                        combine.add((Boolean) c);
                    }
                    return combine;
                }))
                .map(v -> v.toArray(new Boolean[v.size()])) //TODO ?Refactor data casting?
                .subscribe(v -> settings.bool = v);

        subscriptionList.add(s);
    }

    private void initSeekbars() {
        Observable<Integer> sb1 = RxSeekBar.changes(binding.seekbarWorkSession)
                .map(v -> v + 25)
                .doOnSubscribe(() -> {
                    binding.textViewWorkSession.setText(String.valueOf(settings.intr[0]));
                    binding.seekbarWorkSession.setProgress(settings.intr[0] - 25);
                })
                .doOnNext(v -> binding.textViewWorkSession.setText(String.valueOf(v)));

        Observable<Integer> sb2 = RxSeekBar.changes(binding.seekbarBreak)
                .map(v -> v + 5)
                .doOnSubscribe(() -> {
                    binding.textViewBreak.setText(String.valueOf(settings.intr[1]));
                    binding.seekbarBreak.setProgress(settings.intr[1] - 5);
                })
                .doOnNext(v -> binding.textViewBreak.setText(String.valueOf(v)));

        Observable<Integer> sb3 = RxSeekBar.changes(binding.seekbarLongBreak)
                .map(v -> v + 15)
                .doOnSubscribe(() -> {
                    binding.textViewLongBreak.setText(String.valueOf(settings.intr[2]));
                    binding.seekbarLongBreak.setProgress(settings.intr[2] - 15);
                })
                .doOnNext(v -> binding.textViewLongBreak.setText(String.valueOf(v)));

        Observable<Integer> sb4 = RxSeekBar.changes(binding.seekbarSessionsBeforeLB)
                .map(v -> v + 3)
                .doOnSubscribe(() -> {
                    binding.textViewSessionsBeforeLB.setText(String.valueOf(settings.intr[3]));
                    binding.seekbarSessionsBeforeLB.setProgress(settings.intr[3] - 3);
                })
                .doOnNext(v -> binding.textViewSessionsBeforeLB.setText(String.valueOf(v)));

        Subscription s = Observable.combineLatest(sb1,sb2,sb3,sb4, (a1, a2, a3, a4) ->
                Arrays.asList(a1, a2, a3, a4))
                .map(v -> v.toArray(new Integer[v.size()]))
                .subscribe(v -> settings.intr = v);

        //TODO Fix subscriptions lifecycle management
        subscriptionList.add(s);
    }
}