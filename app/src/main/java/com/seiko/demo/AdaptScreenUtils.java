package com.seiko.demo;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class AdaptScreenUtils {

    @NonNull
    public static Resources adaptWidth(@NonNull final Resources resources, final int designWidth) {
        float newXdpi = (resources.getDisplayMetrics().widthPixels * 72f) / designWidth;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    @NonNull
    public static Resources adaptHeight(@NonNull final Resources resources, final int designHeight) {
        return adaptHeight(resources, designHeight, false);
    }

    @NonNull
    public static Resources adaptHeight(@NonNull final Resources resources, final int designHeight, final boolean includeNavBar) {
        float screenHeight = (resources.getDisplayMetrics().heightPixels
                + (includeNavBar ? getNavBarHeight(resources) : 0)) * 72f;
        float newXdpi = screenHeight / designHeight;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    private static int getNavBarHeight(@NonNull final Resources resources) {
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    private static void applyDisplayMetrics(@NonNull final Resources resources, final float newXdpi) {
        resources.getDisplayMetrics().xdpi = newXdpi;
    }

    public static int pt2Px(@NonNull final Resources resource, final float ptValue) {
        DisplayMetrics metrics = resource.getDisplayMetrics();
        return (int) (ptValue * metrics.xdpi / 72f + 0.5);
    }

    public static int px2Pt(@NonNull final Resources resource, final float pxValue) {
        DisplayMetrics metrics = resource.getDisplayMetrics();
        return (int) (pxValue * 72 / metrics.xdpi + 0.5);
    }
}
