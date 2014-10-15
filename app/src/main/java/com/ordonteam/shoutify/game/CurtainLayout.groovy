package com.ordonteam.shoutify.game
import android.app.Activity
import android.graphics.Color
import android.widget.RelativeLayout
import android.widget.TextView
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ViewUtil.getNewRelativeCenterInParent
import static com.ordonteam.shoutify.util.ViewUtil.getNewRelativeMatchParent

@CompileStatic
class CurtainLayout extends RelativeLayout {
    CurtainLayout(Activity activity) {
        super(activity)
        setLayoutParams(newRelativeMatchParent)
        setBackgroundColor(Color.argb(190,0,0,0))

        TextView waitInfo = new TextView(activity)
        waitInfo.setText('Please wait for your opponent')
        waitInfo.setTextColor(Color.WHITE)
        waitInfo.setLayoutParams(newRelativeCenterInParent)
        addView(waitInfo)
    }
}
