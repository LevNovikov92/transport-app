package com.levnovikov.transport_app.main.car_animator;

import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.levnovikov.transport_app.main.di.MainScope;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 13/2/18.
 */

@MainScope
class CarAnimatorMapWrapper {

    private static final float DEFAULT_ZOOM = 18f;

    private final GoogleMap map;

    CarAnimatorMapWrapper(GoogleMap map) {
        this.map = map;
    }

    Marker setMarker(MarkerOptions markerOptions) {
        return map.addMarker(markerOptions);
    }

    void focusOnPosition(LatLng position) {
        CameraPosition cameraPosition = CameraPosition.builder().zoom(DEFAULT_ZOOM)
                .target(position)
                .build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    void animateMarkerToPosition(Marker marker, LatLng position) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(getMovementAnimation(marker, position));
        set.setInterpolator(getSinInterpolator());
        set.setDuration(1000);
        set.start();
    }

    private ValueAnimator getMovementAnimation(Marker marker, LatLng position) {
        ValueAnimator animator = ValueAnimator.ofObject(getTypeEvaluator(), marker.getPosition(), position);
        animator.addUpdateListener(valueAnimator -> {
            LatLng newPosition = (LatLng) valueAnimator.getAnimatedValue();
            marker.setPosition(newPosition);
        });
        return animator;
    }

    private Interpolator getSinInterpolator() {
        return v -> (float) Math.sin(v);
    }

    private TypeEvaluator<LatLng> getTypeEvaluator() {
        return (v, from, to) -> {
//            double lat = from.latitude + (to.latitude - from.latitude) * speed;
//            double lon = from.longitude + (to.longitude - from.longitude) * speed;
            double lat = from.latitude + (to.latitude - from.latitude) * v;
            double lon = from.longitude + (to.longitude - from.longitude) * v;
            return new LatLng(lat, lon);
        };
    }
}
