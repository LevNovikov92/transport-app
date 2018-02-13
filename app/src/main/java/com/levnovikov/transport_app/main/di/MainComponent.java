package com.levnovikov.transport_app.main.di;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.levnovikov.feature_map.MapSetter;
import com.levnovikov.feature_map.di.MapDependency;
import com.levnovikov.system_lifecycle.activity.ALifecycle;
import com.levnovikov.system_lifecycle.activity.LifecycleActivity;
import com.levnovikov.transport_app.di.AppComponent;
import com.levnovikov.system_common.Interactor;
import com.levnovikov.transport_app.main.MainActivity;
import com.levnovikov.transport_app.main.MainInteractor;
import com.levnovikov.transport_app.main.MapProvider;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@MainScope
@Component(dependencies = AppComponent.class, modules = MainComponent.MainModule.class)
public interface MainComponent extends MapDependency {

    void inject(MainActivity mainActivity);

    @Module(includes = MainBinders.class)
    class MainModule {

        @MainScope
        @Provides
        LayoutInflater provideInflater(LifecycleActivity activity) {
            return activity.getLayoutInflater();
        }
    }

    @Module
    interface MainBinders {
        @Binds
        Context provideContext(LifecycleActivity activity);

        @Binds
        ALifecycle provideActivityLifecycle(LifecycleActivity activity);

        @Binds
        Interactor provideInteractor(MainInteractor interactor);

        @Binds
        MapSetter provideMapSetter(MainInteractor interactor);

        @Binds
        MapProvider provideMapProvider(MainInteractor interactor);
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindContext(LifecycleActivity activity);
        @BindsInstance
        Builder container(ViewGroup container);
        Builder appComponent(AppComponent component);
        MainComponent build();
    }
}
