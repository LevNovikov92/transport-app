package com.levnovikov.transport_app.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.levnovikov.transport_app.main.di.MainScope;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@MainScope
public class MainNavigator {

    private final ViewGroup container;
    private final LayoutInflater inflater;

    @Inject
    MainNavigator(ViewGroup container, LayoutInflater inflater) {
        this.container = container;
        this.inflater = inflater;
    }

    void attachMap() {

    }

    void attachPrebooking() {

    }

    <V extends View> V createView(Class<V> vClass) {
        inflater.inflate();
    }
}
