package com.levnovikov.transport_app.main.car_animator;

import com.levnovikov.system_lifecycle.activity.ALifecycle;
import com.levnovikov.transport_app.main.MapProvider;

import javax.inject.Inject;

/**
 * Author: lev.novikov
 * Date: 13/2/18.
 */

public class CarAnimatorInteractor {

    private final MapProvider mapProvider;
    private final ALifecycle lifecycle;

    @Inject
    public CarAnimatorInteractor(MapProvider mapProvider, ALifecycle lifecycle) {
        this.mapProvider = mapProvider;
        this.lifecycle = lifecycle;
    }

    public void animateCar() {
        lifecycle.subscribeUntilDestroy(
                mapProvider
                        .getMap()
                        .map(map -> new CarAnimatorMapWrapper(map))
                        .subscribe(map -> {

                        }, this::handleError));
    }

    private void handleError(Throwable e) {
        //TODO
    }
}
