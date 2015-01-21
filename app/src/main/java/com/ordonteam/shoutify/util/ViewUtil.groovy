package com.ordonteam.shoutify.util

import android.content.res.Resources
import android.widget.RelativeLayout
import groovy.transform.CompileStatic

@CompileStatic
class ViewUtil {
    static int dpAsPixels(int dp, Resources resources) {
        float scale = resources.getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }

    static RelativeLayout.LayoutParams getNewRelativeMatchParent(){
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
    }

    static RelativeLayout.LayoutParams getNewRelativeCenterInParent(){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        return params
    }
}
