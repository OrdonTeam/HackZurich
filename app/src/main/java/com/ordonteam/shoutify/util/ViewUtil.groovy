package com.ordonteam.shoutify.util

import android.content.res.Resources
import groovy.transform.CompileStatic

@CompileStatic
class ViewUtil {
    static int dpAsPixels(int dp, Resources resources) {
        float scale = resources.getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }
}
