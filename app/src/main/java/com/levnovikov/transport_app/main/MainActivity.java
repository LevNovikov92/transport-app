package com.levnovikov.transport_app.main;

import android.os.Bundle;
import android.view.ViewGroup;

import com.levnovikov.system_lifecycle.activity.LifecycleActivity;
import com.levnovikov.transport_app.Application;
import com.levnovikov.transport_app.R;
import com.levnovikov.transport_app.main.di.DaggerMainComponent;
import com.levnovikov.transport_app.main.di.MainComponent;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup container = findViewById(R.id.container);
        setupDI(container);
    }

    private void setupDI(ViewGroup container) {
        MainComponent component = DaggerMainComponent.builder()
                .appComponent(((Application) getApplication()).component())
                .container(container)
                .bindContext(this)
                .build();
    }
}
