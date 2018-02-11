package com.levnovikov.transport_app;

import com.levnovikov.transport_app.di.AppComponent;
import com.levnovikov.transport_app.di.DaggerAppComponent;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

public class Application extends android.app.Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDI();
    }

    private void setupDI() {
        component = DaggerAppComponent.builder()
                .bindApp(this)
                .build();
    }

    public AppComponent component() {
        return component;
    }
}
