package com.ordonteam.shoutify.game.bars
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.widget.RelativeLayout
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ViewUtil.dpAsPixels

@CompileStatic
class UserChargingProgressbar extends ShoutifyProgressBar {
    UserChargingProgressbar(Activity activity) {
        super(activity, Color.BLUE)

        size = new Point(dpAsPixels(250, getResources()),dpAsPixels(50,getResources()))

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.x,size.y)
        setLayoutParams(layoutParams)
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawRect(0,0,size.x,size.y,border)
        canvas.drawRect(0,0,(int)size.x*progress/100,size.y,paint)
    }
}
