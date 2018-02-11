package com.levnovikov.feature_map;

import com.google.android.gms.maps.GoogleMap;

import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

public interface MapProvider {
    Single<GoogleMap> getMap();
}
