package com.ordonteam.hackzurich.game.bars
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.widget.RelativeLayout
import groovy.transform.CompileStatic

import static com.ordonteam.hackzurich.util.ViewUtil.dpAsPixels

@CompileStatic
class UserChargingProgressbar extends ShoutifyProgressBar {
    Paint border

    UserChargingProgressbar(Activity activity) {
        super(activity)
        progress = 0
        size = new Point(dpAsPixels(250, getResources()),dpAsPixels(50,getResources()))
        position = new Point(dpAsPixels(5,getResources()),dpAsPixels(5,getResources()))
        paint = new Paint()
        paint.setColor(Color.BLUE)
        border = new Paint()
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(3)
        border.setColor(Color.BLUE)
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.x,size.y)
        setLayoutParams(layoutParams)
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawRect(position.x+3,position.y+3,(int)position.x+size.x-20,position.y+size.y-20,border)
        canvas.drawRect(position.x,position.y,(int)position.x+size.x*progress/100,position.y+size.y,paint)
    }
}
