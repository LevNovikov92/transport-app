package com.levnovikov.transport_app.di;

import android.content.Context;

import com.levnovikov.transport_app.Application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@Singleton
@Component(modules = AppComponent.AppModule.class)
public interface AppComponent {

    String APP_CONTEXT = "APP_CONTEXT";

    @Named(APP_CONTEXT)
    Context context();

    @Module
    class AppModule {

        @Singleton
        @Provides
        @Named(APP_CONTEXT)
        Context provideContext(Application application) {
            return application;
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance Builder bindApp(Application application);
        AppComponent build();
    }
}
