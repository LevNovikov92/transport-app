package com.levnovikov.transport_app.main;

import com.google.android.gms.maps.GoogleMap;
import com.levnovikov.feature_map.MapSetter;
import com.levnovikov.system_common.Interactor;
import com.levnovikov.transport_app.main.car_animator.CarAnimatorInteractor;
import com.levnovikov.transport_app.main.di.MainScope;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.subjects.SingleSubject;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@MainScope
public class MainInteractor implements Interactor, MapProvider, MapSetter {

    private MainNavigator navigator;
    private final CarAnimatorInteractor animatorInteractor;

    @Inject
    MainInteractor(MainNavigator navigator, CarAnimatorInteractor animatorInteractor) {
        this.navigator = navigator;
        this.animatorInteractor = animatorInteractor;
    }

    @Override
    public void onGetActive() {
        navigator.attachMap();
        animatorInteractor.animateCar();
    }

    private SingleSubject<GoogleMap> subject = SingleSubject.create();

    @Override
    public Single<GoogleMap> getMap() {
        return subject;
    }

    @Override
    public void setMap(GoogleMap map) {
        subject.onSuccess(map);
    }
}
