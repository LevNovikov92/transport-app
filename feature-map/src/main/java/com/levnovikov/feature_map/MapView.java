package com.levnovikov.feature_map;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.levnovikov.feature_map.di.DaggerMapComponent;
import com.levnovikov.feature_map.di.MapComponent;
import com.levnovikov.feature_map.di.MapDependency;
import com.levnovikov.system_common.ComponentProvider;
import com.levnovikov.system_lifecycle.activity.ALifecycle;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

public class MapView extends com.google.android.gms.maps.MapView implements OnMapReadyCallback {

    public MapView(Context context) {
        super(context);
        setupView();
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupView();
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setupView();
    }

    @Inject
    ALifecycle lifecycle;

    @Inject
    MapSetter mapSetter;

    public static final int layout = R.layout.map_view;

    void setupView() {
        setupDI();
        initMap();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void setupDI() {
        MapComponent component = DaggerMapComponent.builder()
                .mapDependency((MapDependency) ((ComponentProvider) getContext()).component())
                .build();
        component.inject(this);
    }

    private void initMap() {
        getMapAsync(this);
        lifecycle.subscribeUntilDestroy(lifecycle.getStream()
                .subscribe(event -> {
                    switch (event) {
                        case CREATE:
                            onCreate(event.getBundle());
                        case RESUME:
                            onResume();
                            break;
                        case PAUSE:
                            onPause();
                            break;
                        case DESTROY:
                            onDestroy();
                            break;
                    }
                }, e -> { })
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapSetter.setMap(googleMap);
    }

}
