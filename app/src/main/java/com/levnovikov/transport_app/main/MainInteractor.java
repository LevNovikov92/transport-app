package com.levnovikov.transport_app.main;

import com.levnovikov.system_common.Interactor;
import com.levnovikov.transport_app.main.di.MainScope;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@MainScope
public class MainInteractor implements Interactor {

    private MainNavigator navigator;

    @Inject
    MainInteractor(MainNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void onGetActive() {
        navigator.attachMap();
    }
}
