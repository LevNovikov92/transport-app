package com.levnovikov.transport_app.main;

import android.os.Bundle;
import android.view.ViewGroup;

import com.levnovikov.feature_map.di.MapDependency;
import com.levnovikov.system_common.ComponentProvider;
import com.levnovikov.system_common.Interactor;
import com.levnovikov.system_lifecycle.activity.LifecycleActivity;
import com.levnovikov.transport_app.Application;
import com.levnovikov.transport_app.R;
import com.levnovikov.transport_app.main.di.DaggerMainComponent;
import com.levnovikov.transport_app.main.di.MainComponent;

import javax.inject.Inject;

public class MainActivity extends LifecycleActivity implements ComponentProvider {

    @Inject
    Interactor interactor;

    private MainComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ViewGroup container = findViewById(R.id.container);
        setupDI(container);
        interactor.onGetActive();
        super.onCreate(savedInstanceState);
    }

    private void setupDI(ViewGroup container) {
        component = DaggerMainComponent.builder()
                .appComponent(((Application) getApplication()).component())
                .container(container)
                .bindContext(this)
                .build();
        component.inject(this);
    }

    @Override
    public MainComponent component() {
        return component;
    }
}
