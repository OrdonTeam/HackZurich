package com.ordonteam.hackzurich.mode

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.ordonteam.hackzurich.util.ViewUtil
import groovy.transform.CompileStatic

@CompileStatic
class UserChargingProgressbar extends ShoutifyProgressBar {
    UserChargingProgressbar(Activity activity) {
        super(activity)
        size = new Point(ViewUtil.dpAsPixels(250, getResources()),ViewUtil.dpAsPixels(50,getResources()))
        position = new Point(ViewUtil.dpAsPixels(5,getResources()),ViewUtil.dpAsPixels(5,getResources()))
        paint = new Paint()
        paint.setColor(Color.BLUE)
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.x,size.y)
        setLayoutParams(layoutParams)
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawRect(position.x,position.y,position.x+size.x,position.y+size.y,paint)
    }
}
