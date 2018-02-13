package com.levnovikov.feature_map.di;

import com.levnovikov.feature_map.MapSetter;
import com.levnovikov.system_lifecycle.activity.ALifecycle;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

public interface MapDependency {

    MapSetter mapSetter();
    ALifecycle lifecycle();
}
