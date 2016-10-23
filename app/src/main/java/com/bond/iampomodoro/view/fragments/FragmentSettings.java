package com.bond.iampomodoro.view.fragments;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bond.iampomodoro.App;
import com.bond.iampomodoro.AppComponent;
import com.bond.iampomodoro.model.SettingsObject;
import com.bond.iampomodoro.presenter.Presenter;
import com.bond.iampomodoro.R;
import com.bond.iampomodoro.view.RepoInfoMainView;
import com.bond.iampomodoro.databinding.FragmentSettingsBinding;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxSeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static rx.Observable.combineLatest;

public class FragmentSettings extends Fragment implements RepoInfoMainView {

    @Inject
    Presenter presenter;

    private FragmentSettingsBinding binding;

    //private Presenter mainPresenter;
    private SettingsObject settings;

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent component = App.getComponent();
        App.getComponent().inject(this);

  //      IA.getComponent().inject(this);
  //      presenter.onCreate(this, getRepositoryVO());

        //App.getComponent().inject(this);
        //presenter.onCreate(this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mainPresenter = new Presenter();
        //settings = mainPresenter.notifySettingsFragmentStart(getActivity());
        settings = presenter.notifySettingsFragmentStart(getActivity());

        InitCheckBoxes();
        InitSeekbars();
    }

    @Override
    public void onPause() {

        presenter.saveSetings(getActivity(), settings);

        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (!subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(!isVisibleToUser && settings != null) {
           presenter.saveSetings(getActivity(), settings);
        }
    }

    private void InitCheckBoxes() {
  //      Observable<Long> observable = Observable.create(subscriber -> {
  //          long n = 0;
  //          while (true) {
  //              sleep(10);
  //              subscriber.onNext(n++);
  //          }
  //      });

        List<CheckBox> cbList = Arrays.asList( binding.cbxDaySound, binding.cbxDayVibration,
                binding.cbxDayKeepScreen, binding.cbxNightSound, binding.cbxNightVibration,
                binding.cbxNightKeepScreen, binding.cbxNightPictures);

        subscriptions.add( Observable.from(cbList)
                .doOnNext(v -> v.setChecked(settings.bool[cbList.indexOf(v)])) //TODO Refactor string
                .map(RxCompoundButton::checkedChanges)
                .toList()
                .flatMap(v -> combineLatest(v, args -> {
                    List<Boolean> combine = new ArrayList<>();
                    for(Object c : args) {
                        combine.add((Boolean) c);
                    }
                    return combine;
                }))
                .map(v -> v.toArray(new Boolean[v.size()])) //TODO Delete this
                .subscribe(v -> settings.bool = v));
    }

    private void InitSeekbars() {
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

        subscriptions.add( Observable.combineLatest(sb1,sb2,sb3,sb4, (a1, a2, a3, a4) ->
                Arrays.asList(a1, a2, a3, a4))
                .map(v -> v.toArray(new Integer[v.size()]))
                .subscribe(v -> settings.intr = v));
    }
}