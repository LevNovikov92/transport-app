package com.levnovikov.feature_map.di;

import com.levnovikov.feature_map.MapProvider;
import com.levnovikov.feature_map.MapView;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@MapScope
@Component(dependencies = MapDependency.class, modules = MapComponent.MapModule.class)
public interface MapComponent {

    @Module
    class MapModule {
        private final MapView view;

        MapModule(MapView view) {
            this.view = view;
        }

        @Provides
        MapProvider provideMapProvider() {
            return view;
        }
    }
}
