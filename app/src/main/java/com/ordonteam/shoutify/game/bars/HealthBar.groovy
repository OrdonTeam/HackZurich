package com.ordonteam.shoutify.game.bars
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Point
import android.widget.RelativeLayout
import groovy.transform.CompileStatic

import static com.ordonteam.shoutify.util.ViewUtil.dpAsPixels

@CompileStatic
class HealthBar extends ShoutifyProgressBar {
    HealthBar(Activity activity, Integer color) {
        super(activity, color)
        progress = 100

        size = new Point(dpAsPixels(50, getResources()), dpAsPixels(350, getResources()))

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.x,size.y)
        setLayoutParams(layoutParams)
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawRect(0,0,size.x,size.y,border)
        canvas.drawRect(0,(int)size.y*(1d-(progress/100)),size.x,size.y,paint)
    }
}
